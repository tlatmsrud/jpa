package org.study.jpa.space.multiplicity.manyToManyIdClass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(MPMemberProductId.class)
@Getter
@Setter
public class MPMemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MPMember member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private MPProduct product;

    private int orderAmount;
}
