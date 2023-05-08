package org.study.jpa.space.relationmapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TMember {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TTeam team;

    public void setTeam(TTeam team){
        this.team = team;
        team.getMembers().add(this);
    }

    public void standardSetTeam(TTeam team){
        if(this.team != null){
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }
}
