package com.special.blockduce.member.service.impl;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.exceptions.EntityNotFoundException;
import com.special.blockduce.exceptions.ErrorCode;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.Salt;
import com.special.blockduce.member.domain.request.SignupMemberRequest;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.member.service.AuthService;
import com.special.blockduce.member.service.EmailService;
import com.special.blockduce.utils.RedisUtil;
import com.special.blockduce.utils.SaltUtil;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    final String REDIS_CHANGE_PASSWORD_PREFIX="CPW";
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final SaltUtil saltUtil;
    private final RedisUtil redisUtil;

    public Member findMemberByName(String name){
        return memberRepository.findMemberByName(name).orElseThrow(() ->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByEmail(String email){
        Member member = memberRepository.findMemberByEmail(email).orElseThrow(() -> new EntityNotFoundException(ErrorCode.EMAIL_NOT_FOUND));
        return member;
    }

    Member getMemberEmail(String email){return null;}

    /*회원가입*/
    @Override
    @Transactional
    public Member signUpMember(SignupMemberRequest signupMemberRequest){

//        if (existsByEmail(signupMemberRequest.getEmail())){
//            return false;
//        }

        String password = signupMemberRequest.getPassword();
        String salt = SaltUtil.genSalt();
        if(signupMemberRequest.getImageUrl() == "" || signupMemberRequest.getImageUrl()==null){
            signupMemberRequest.setImageUrl("https://blockduce-image.s3.ap-northeast-2.amazonaws.com/default-img.png");
        }

        Member member = Member.builder()
                .email(signupMemberRequest.getEmail())
                .img(signupMemberRequest.getImageUrl())
                .name(signupMemberRequest.getName())
                .salt(new Salt(salt))
                .password(SaltUtil.encodePassword(salt,password))
                .intro(signupMemberRequest.getIntro())
                .nickname(signupMemberRequest.getNickname())
                .build();
        memberRepository.save(member);

        return member;
    }

    /*이메일 중복 검사*/
    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    /*로그인*/
    @Override
    public Member loginMember(String id, String password)throws Exception{
        Member memeber = findMemberByEmail(id);
        if(memeber == null) throw new Exception("멤버가 조회 되지 않습니다.");
        String salt = memeber.getSalt().getSalt();
        password = SaltUtil.encodePassword(salt,password);
        if(!memeber.getPassword().equals(password)){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return memeber;
    }


    /*이메일 인증*/
    @Override
    public void sendVerificationMail(Member member) throws NotFoundException {
        String VERIFICATION_LINK = "http://j4b107.p.ssafy.io/api/auth/verify/";
        if(member==null) throw new NotFoundException("멤버가 조회되지 않음");
        UUID uuid = UUID.randomUUID();
        redisUtil.setDataExpire(uuid.toString(),member.getEmail(), 60 * 30L);
        emailService.sendMail(member.getEmail(),"[BLOCKDUCE] 회원가입 인증메일입니다.",VERIFICATION_LINK+uuid.toString());
    }


    /*이메일 다시 보내기*/
    public void resendVerificationMail(Member member) throws NotFoundException {
        String VERIFICATION_LINK = "http://j4b107.p.ssafy.io/api/user/verify/";
        if(member==null) throw new NotFoundException("멤버가 조회되지 않음");
        UUID uuid = UUID.randomUUID();
        redisUtil.setDataExpire(uuid.toString(),member.getEmail(), 60 * 30L);
        emailService.sendMail(member.getEmail(),"[BLOCKDUCE] 회원가입 인증메일입니다.",VERIFICATION_LINK+uuid.toString());
    }

    /*이메일 가져오기*/
    @Override
    public void verifyEmail(String key) throws NotFoundException {
        String email = redisUtil.getData(key);
        Member member = findMemberByEmail(email);
        if(member==null) throw new NotFoundException("멤버가 조회되지않음");
        modifyUserRole(member, UserRole.USER);
        redisUtil.deleteData(key);
    }

    /*Role 설정*/
    @Override
    public void modifyUserRole(Member member, UserRole userRole){
        member.setRole(userRole);
        memberRepository.save(member);
    }


    /*비밀번호 가져오기*/
    @Override
    public boolean isPasswordUuidValidate(String key){
        String memberId = redisUtil.getData(key);
        return !memberId.equals("");
    }

    /*비밀번호 변경*/
    @Override
    public void changePassword(Member member,String password) throws NotFoundException{
        if(member == null) throw new NotFoundException("changePassword(),멤버가 조회되지 않음");
        String salt = saltUtil.genSalt();
        member.setSalt(new Salt(salt));
        member.setPassword(saltUtil.encodePassword(salt,password));
        memberRepository.save(member);
    }

    /*비밀번호 관련 이메일 확인*/
    @Override
    public void requestChangePassword(Member member) throws NotFoundException{
        String CHANGE_PASSWORD_LINK = "http://j4b107.p.ssafy.io/api/auth/password/";
        if(member == null) throw new NotFoundException("멤버가 조회되지 않음.");
        String key = REDIS_CHANGE_PASSWORD_PREFIX+ UUID.randomUUID();
        redisUtil.setDataExpire(key,member.getName(),60 * 30L);
        emailService.sendMail(member.getEmail(),"[BLOCKDUCE] 사용자 비밀번호 안내 메일",CHANGE_PASSWORD_LINK+key);
    }
}
