package org.study.jpa.space.multiplicity.manyToOneTwoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MPMember {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private MPTeam team;

    public void setTeam(MPTeam team){
        if(this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;

        if(!team.getMembers().contains(this)){ // 무한루프에 빠지지 않도록 체크
            team.getMembers().add(this);
        }
    }
}

