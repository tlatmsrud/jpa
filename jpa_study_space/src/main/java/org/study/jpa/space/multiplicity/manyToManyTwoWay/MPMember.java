package org.study.jpa.space.multiplicity.manyToManyTwoWay;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MPMember {


    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToMany
    @JoinTable(
            name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<MPProduct> products = new ArrayList<>();

}
