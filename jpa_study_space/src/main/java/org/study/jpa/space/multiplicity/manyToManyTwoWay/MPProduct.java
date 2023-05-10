package org.study.jpa.space.multiplicity.manyToManyTwoWay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MPProduct {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;
}
