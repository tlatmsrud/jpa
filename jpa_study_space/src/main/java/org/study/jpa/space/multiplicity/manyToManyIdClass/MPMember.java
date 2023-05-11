package org.study.jpa.space.multiplicity.manyToManyIdClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private List<MPMemberProduct> memberProductList = new ArrayList<>();
}
