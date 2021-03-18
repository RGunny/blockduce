package com.special.blockduce.candidate.domain;

import com.special.blockduce.vote.domain.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    @Column(name = "candidate_name")
    private String name;

    private int age;

    private String agency;

    @Column(name = "candidate_img")
    private String img;

    @Column(name = "candidate_point")
    private Long point;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<Vote> votes = new ArrayList<>();


}
