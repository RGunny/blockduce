package com.special.blockduce.candidate;

import com.special.blockduce.candidate.domain.Candidate;
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
            Candidate candidate1 = createCandidate("최주아", 18, "YG", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/1.jpg");
            em.persist(candidate1);
            Candidate candidate2 = createCandidate("류건희", 21, "JYP", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/2.jpg");
            em.persist(candidate2);
            Candidate candidate3 = createCandidate("박상우", 19, "SM", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/3.jpg");
            em.persist(candidate3);
            Candidate candidate4 = createCandidate("이아영", 15, "CUBE", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/4.jpg");
            em.persist(candidate4);
            Candidate candidate5 = createCandidate("윤은철", 24, "위", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/5.jpg");
            em.persist(candidate5);
            Candidate candidate6 = createCandidate("박성준", 19, "마루기획", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/6.jpg");
            em.persist(candidate6);
            Candidate candidate7 = createCandidate("서범석", 22, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/7.jpg");
            em.persist(candidate7);
            Candidate candidate8 = createCandidate("오현정", 18, "플레디스", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/8.jpg");
            em.persist(candidate8);
            Candidate candidate9 = createCandidate("이수민", 20, "개인연습생", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/9.jpg");
            em.persist(candidate9);
            Candidate candidate10 = createCandidate("김예슬", 17, "울림", "https://blockduce-image.s3.ap-northeast-2.amazonaws.com/10.jpg");
            em.persist(candidate10);
        }

        private Candidate createCandidate(String name, int age, String agency, String img) {
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setAge(age);
            candidate.setAgency(agency);
            candidate.setImg(img);
            return candidate;
        }

    }

}

