package org.study.jpa.space.multiplicity.oneToOneTwoWayM;

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

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private MPLocker locker;

    public void setLocker(MPLocker locker){
        if(this.locker != null){
            this.setLocker(null);
        }
        this.locker = locker;
        locker.setMember(this);

    }

}
