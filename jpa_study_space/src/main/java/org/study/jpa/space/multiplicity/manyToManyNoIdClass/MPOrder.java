package org.study.jpa.space.multiplicity.manyToManyNoIdClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MPOrder {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MPMember member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private MPProduct product;

    private int orderAmount;

}
