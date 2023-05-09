package org.study.jpa.space.multiplicity.manyToOneTwoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MPTeam {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<MPMember> members = new ArrayList<>();

    public void addMember(MPMember member){

        // 순수한 객체 관계 구현을 위한 추가구문
        this.members.add(member);

        // 실질적인 팀 수정
        if(member.getTeam() != this){ // 무한루프에 빠지지 않도록?
            member.setTeam(this);
        }
    }
}
