package com.special.blockduce.member.service;

public interface EmailService {
    void sendMail(String to, String sub, String text);
}
