package org.study.jpa.space.highlevelmapping.nondiscrimination;

import javax.persistence.*;

@Entity
public class NDSChild {

    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private NDSParent parent;
}
