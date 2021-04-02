package com.special.blockduce.candidate.service;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.candidate.dto.CandidateDto;
import com.special.blockduce.candidate.repository.CandidateRepository;
import com.special.blockduce.member.domain.Member;
import com.special.blockduce.member.dto.MemberForm;
import com.special.blockduce.utils.SaltUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

//    public List<ReadCandidatesResponse> findCandidates(){
//        return candidateRepository.findReadCandidatesResponse();
//    }

    public List<CandidateDto> findAll() {
        return (List) candidateRepository.findAll();
    }

    public void join(CandidateDto form) {

        String key = form.getKey();
        String salt = SaltUtil.genSalt();

        Candidate candidate = Candidate.builder().
                age(form.getAge()).
                agency(form.getAgency()).
                img(form.getImg()).
                name(form.getName()).
                account(form.getAccount()).
                dbc(form.getDbc()).
                intro(form.getIntro()).
                key(SaltUtil.encodePassword(salt,key)).
                build();

        candidateRepository.save(candidate);
    }
}
