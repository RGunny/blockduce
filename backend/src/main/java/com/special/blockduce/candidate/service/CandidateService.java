package com.special.blockduce.candidate.service;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.candidate.dto.ReadCandidatesResponse;
import com.special.blockduce.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public List<ReadCandidatesResponse> findCandidates(){
        return candidateRepository.findReadCandidatesResponse();
    }
}
