package org.study.jpa.space.highlevelmapping.oneToOneJoinTable;

import javax.persistence.*;

@Entity
public class OTOChild {

    @Id @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "child")
    private OTOParent parent;
}
