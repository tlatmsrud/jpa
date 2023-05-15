package org.study.jpa.space.highlevelmapping.oneToManyJoinTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OTMChild {

    @Id
    @GeneratedValue
    @Column(name = "CHLID_ID")
    private Long id;

    private String name;
}
