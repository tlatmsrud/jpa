package org.study.jpa.space.multiplicity.manyToManyNoIdClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MPMember {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToMany(mappedBy = "member")
    private List<MPOrder> orders = new ArrayList<>();
}
