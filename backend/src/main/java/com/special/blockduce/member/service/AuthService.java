package com.special.blockduce.member.service;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.Salt;
import com.special.blockduce.member.repository.MemberRepository;
import com.special.blockduce.utils.RedisUtil;
import com.special.blockduce.utils.SaltUtil;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    final String REDIS_CHANGE_PASSWORD_PREFIX="CPW";
    private MemberRepository memberRepository;
    private EmailService emailService;
    private SaltUtil saltUtil;
    private RedisUtil redisUtil;

    public AuthService (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public void signUpMember(Member member){
        String password = member.getPassword();
        String salt = SaltUtil.genSalt();
        member.setSalt(new Salt(salt));
        member.setPassword(SaltUtil.encodePassword(salt,password));
        memberRepository.save(member);
    }

    public Member loginMember(String id, String password)throws Exception{
        Member memeber = memberRepository.findMemberByname(id);
        if(memeber == null) throw new Exception("멤버가 조회 되지 않습니다.");
        String salt = memeber.getSalt().getSalt();
        password = SaltUtil.encodePassword(salt,password);
        if(!memeber.getPassword().equals(password)){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return memeber;
    }

    public void sendVerificationMail(Member member) throws NotFoundException {
        String VERIFICATION_LINK = "http://localhost:8080/user/verify/";
        if(member==null) throw new NotFoundException("멤버가 조회되지 않음");
        UUID uuid = UUID.randomUUID();
        redisUtil.setDataExpire(uuid.toString(),member.getName(), 60 * 30L);
        emailService.sendMail(member.getEmail(),"[BLOCKDUCE] 회원가입 인증메일입니다.",VERIFICATION_LINK+uuid.toString());
    }

    public void verifyEmail(String key) throws NotFoundException {
        String memberId = redisUtil.getData(key);
        Member member = memberRepository.findMemberByname(memberId);
        if(member==null) throw new NotFoundException("멤버가 조회되지않음");
        modifyUserRole(member, UserRole.USER);
        redisUtil.deleteData(key);
    }

    public void modifyUserRole(Member member, UserRole userRole){
        member.setRole(userRole);
        memberRepository.save(member);
    }

    public Member findMemberByname(String username) throws NotFoundException {
        Member member = memberRepository.findMemberByname(username);
        if(member == null) throw new NotFoundException("멤버가 조회되지 않음");
        return member;
    }

    public boolean isPasswordUuidValidate(String key){
        String memberId = redisUtil.getData(key);
        return !memberId.equals("");
    }

    public void changePassword(Member member,String password) throws NotFoundException{
        if(member == null) throw new NotFoundException("changePassword(),멤버가 조회되지 않음");
        String salt = saltUtil.genSalt();
        member.setSalt(new Salt(salt));
        member.setPassword(saltUtil.encodePassword(salt,password));
        memberRepository.save(member);
    }


    public void requestChangePassword(Member member) throws NotFoundException{
        String CHANGE_PASSWORD_LINK = "http://localhost:8080/auth/password/";
        if(member == null) throw new NotFoundException("멤버가 조회되지 않음.");
        String key = REDIS_CHANGE_PASSWORD_PREFIX+UUID.randomUUID();
        redisUtil.setDataExpire(key,member.getName(),60 * 30L);
        emailService.sendMail(member.getEmail(),"[BLOCKDUCE] 사용자 비밀번호 안내 메일",CHANGE_PASSWORD_LINK+key);
    }
}
