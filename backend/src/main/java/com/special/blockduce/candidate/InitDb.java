package com.special.blockduce.candidate;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.member.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitServie initServie;

    /**
     * spring bean이 다 올라온 후 호출
     */
//    @PostConstruct
//    public void init() {
//        initServie.dbInit();
//    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitServie {

        private final EntityManager em;

        public void dbInit() {
//            Candidate candidate0 = createCandidate("관리자", 0, "관리자", "관리자", "관리자");
//            em.persist(candidate0);
            Account account = createAccount("0x9DFD19acAc0c523D19F9B50B4640a0dD74E092E6", Double.MAX_VALUE, Double.MAX_VALUE, "91b40449775898b8c31c8cb914f5408bc4e2a619cab888fcf1b0f823b8905ffd");
            Candidate candidate = createCandidate("관리자", 0, "관리자", "관리자", "관리자", account);
            em.persist(candidate);


            Account account1 = createAccount("0xD9Cba24c647faE6fB0b971487F75d78dCBB6FBb3", 0.0, 0.0, "0xf4d421861b505d304dde698ce9ee1b3a5c02c618f5cbe66a62a86d051dbfecac");
            Candidate candidate1 = createCandidate("황호연", 10, "가족기획", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_11.png", "안녕하세요.", account1);
            em.persist(candidate1);

            Account account2 = createAccount("0x54F342fC111FA8309450C07A2Ad060117A9e1423", 0.0, 0.0, "0xc6b0f8a3d40b25a4392626fe191f865d8cab6c48c1050e926717b897f5f85d04");
            Candidate candidate2 = createCandidate("황영준", 28, "싸피", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_12.png", "안녕하세요", account2);
            em.persist(candidate2);

            Account account3 = createAccount("0x7a0684c2f46600edC209f2026Abec462a5b941E1", 0.0, 0.0, "0x89eda53d33a7313676b691bf5c9b57166c3908b8b0e7950b2b05288137d73b37");
            Candidate candidate3 = createCandidate("최주아", 18, "YG", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_1.png", "안녕하세요", account3);
            em.persist(candidate3);

            Account account4 = createAccount("0xAAC5B427875411c7b515871BD0b87A8d5e460fC6", 0.0, 0.0, "0x179045cbef52e8c51c7b04b45966c0fb318995dde05725f1ebce987be85a3ea7");
            Candidate candidate4 = createCandidate("류건희", 21, "JYP", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_2.png", "안녕하세요", account4);
            em.persist(candidate4);

            Account account5 = createAccount("0x0A964Cf54e1eD5c5f5a11766ea6295ac89FD1bCF", 0.0, 0.0, "0x406665c59016e8e19affb13691ff426ea1e61bf2dc462ae862e0e0eaf84ac263");
            Candidate candidate5 = createCandidate("박상우", 19, "SM", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_3.png", "안녕하세요", account5);
            em.persist(candidate5);

            Account account6 = createAccount("0x84Bb3E270FC7Df63785A123daD0201fb3bd1A2Fa", 0.0, 0.0, "0xea505f805c86b67fcf61af53fd9e4e300dc582d39998cff06cd79a1cd392c7a9");
            Candidate candidate6 = createCandidate("이아영", 15, "CUBE", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_4.png", "안녕하세요", account6);
            em.persist(candidate6);

            Account account7 = createAccount("0x3Ca6a1B5A4687ED55EE15081d53724Be36709C75", 0.0, 0.0, "0x4d492a4b7ff76a36b155f4699529087fc7b6d4eff4db5d4f01d28b2e9c46d29c");
            Candidate candidate7 = createCandidate("윤은철", 24, "위", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_5.png", "안녕하세요", account7);
            em.persist(candidate7);

            Account account8 = createAccount("0xdAAd6E06AA139BaD34D92bCAd9250183A36979B4", 0.0, 0.0, "0x2d661d2dcd2d6c737ba1f7af61e335e357ef8d14e72724a942794934baa0e1eb");
            Candidate candidate8 = createCandidate("박성준", 19, "마루기획", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_6.png", "안녕하세요", account8);
            em.persist(candidate8);

            Account account9 = createAccount("0xbF63C702d47041D4e487060A6869218F6Be32636", 0.0, 0.0, "0x9e3dcda4000e0c9185f01d2dbd6556f640128461a739f3433b85677d28714581");
            Candidate candidate9 = createCandidate("서범석", 22, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_7.png", "안녕하세요", account9);
            em.persist(candidate9);

            Account account10 = createAccount("0x9f4adb2b6089128A857e38C6aAa23b93bbcc418d", 0.0, 0.0, "0xc43a02a686f3063c45193c2b9caa1ee4073a0c8b18199891c7d6cf65b57efe4a");
            Candidate candidate10 = createCandidate("오현정", 18, "플레디스", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_8.png", "안녕하세요", account10);
            em.persist(candidate10);

            Account account11 = createAccount("0xe56eFd504496BE8e318b9A9d9c33Aa468A4F5882", 0.0, 0.0, "0x5f05633dda073ccb324686adf8431d71e509ef238a1a4b47c1c33a0ffcc2fa65");
            Candidate candidate11 = createCandidate("이수민", 20, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_9.png", "안녕하세요", account11);
            em.persist(candidate11);

            Account account12 = createAccount("0x2dbe277A87c114D75A78c40802A500630a5da874", 0.0, 0.0, "0xc49a52a98c0125691a1e22a7620b1c936898723502ce25206e59937d833f4b27");
            Candidate candidate12 = createCandidate("김예슬", 17, "울림", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/candidate_10.png", "안녕하세요", account12);
            em.persist(candidate12);

        }

        private Candidate createCandidate(String name, int age, String agency, String img, String intro, Account account) {
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setAge(age);
            candidate.setAgency(agency);
            candidate.setImg(img);
            candidate.setIntro(intro);
            candidate.setAccount(account);
            return candidate;
        }

        private Account createAccount(String memberAccount, Double dbc, Double eth, String privateKey) {
            Account account = new Account();
            account.setAccount(memberAccount);
            account.setDbc(dbc);
            account.setEth(eth);
            account.setKey(privateKey);
            return account;
        }

    }

}

