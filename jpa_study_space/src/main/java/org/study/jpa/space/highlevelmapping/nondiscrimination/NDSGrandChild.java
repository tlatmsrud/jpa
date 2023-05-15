package org.study.jpa.space.highlevelmapping.nondiscrimination;

import javax.persistence.*;

@Entity
public class NDSGrandChild {

    @Id
    @GeneratedValue
    @Column(name = "GRANDCHILD_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private NDSChild child;

}
