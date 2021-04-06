package com.special.blockduce.member.service;

import com.special.blockduce.config.UserRole;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.domain.request.SignupMemberRequest;
import javassist.NotFoundException;

public interface AuthService {
    final String REDIS_CHANGE_PASSWORD_PREFIX="CPW";

    Member signUpMember(SignupMemberRequest signupMemberRequest);

    Member loginMember(String id, String password) throws Exception;

    boolean existsByEmail(String email);

    void verifyEmail(String key) throws NotFoundException;

    void sendVerificationMail(Member member) throws NotFoundException;

    void resendVerificationMail(Member member) throws NotFoundException;

    void modifyUserRole(Member member, UserRole userRole);

    boolean isPasswordUuidValidate(String key);

    void changePassword(Member member, String password) throws NotFoundException;

    void requestChangePassword(Member member) throws NotFoundException;

    Member findMemberByName(String name);

    Member findMemberByEmail(String email);


}
