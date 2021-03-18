package com.special.blockduce.vote.domain;

import com.special.blockduce.candidate.domain.Candidate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    // user, 다대일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "vote_point")
    private Long point;

    @Column(name = "vote_date")
    private LocalDateTime date;

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
        candidate.getVotes().add(this);
    }
}
