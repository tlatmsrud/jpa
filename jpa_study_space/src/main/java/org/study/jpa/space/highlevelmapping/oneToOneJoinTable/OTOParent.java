package org.study.jpa.space.highlevelmapping.oneToOneJoinTable;

import javax.persistence.*;

@Entity
public class OTOParent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
        joinColumns = @JoinColumn(name = "PARENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private OTOChild child;
}
