package com.special.blockduce.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.special.blockduce.Response;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.dto.MemberForm;
import com.special.blockduce.member.dto.ProfileDto;
import com.special.blockduce.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=360325f103f39664cd6c418590ff659c");
            sb.append("&redirect_uri=http://localhost:3000/kakaologin");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public ProfileDto getUserInfo(String access_Token) {
        ProfileDto userInfo = new ProfileDto();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            System.out.println(access_Token);
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            JsonObject propertie = element.getAsJsonObject().get("properties").getAsJsonObject();

            String id = element.getAsJsonObject().get("id").getAsString();
            String name = propertie.getAsJsonObject().get("nickname").getAsString();
            String img = propertie.getAsJsonObject().get("profile_image").getAsString();
            String email = null;
            if (kakao_account.getAsJsonObject().get("email") != null) {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
                userInfo = new ProfileDto(id,email,name,img);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public void join(MemberForm form) {
        Member member = Member.builder().email(form.getEmail()).name(form.getName()).password(form.getPassword()).kid(form.getKid()).build();

        System.out.println("member = " + member);
        System.out.println("member = " + member.getClass());
        System.out.println("member = " + member.getClass().getName());

        memberRepository.save(member);
    }

    @Transactional
    public MemberForm findById(ProfileDto userinfo) throws Exception {

        Optional<Member> member = memberRepository.findOptionalByKid(userinfo.getId());

        MemberForm memf;
        if(member.isPresent()){ //계정이 있을 경우
            memf = new MemberForm(userinfo.getId(),userinfo.getEmail(),userinfo.getName(),member.get().getPassword());
        }else{
            memf = new MemberForm(userinfo.getId(),userinfo.getEmail(),userinfo.getName()); //계정이 없을 경우
        }

        return memf;
    }
}
