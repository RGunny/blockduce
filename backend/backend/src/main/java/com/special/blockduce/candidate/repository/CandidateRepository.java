package com.special.blockduce.candidate.repository;

import com.special.blockduce.candidate.domain.Candidate;
import com.special.blockduce.candidate.dto.ReadCandidatesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select new com.special.blockduce.candidate.dto.ReadCandidatesResponse(c.id, c.name, c.age, c.agency, c.img) " +
            " from Candidate c")
    List<ReadCandidatesResponse> findReadCandidatesResponse();

}
