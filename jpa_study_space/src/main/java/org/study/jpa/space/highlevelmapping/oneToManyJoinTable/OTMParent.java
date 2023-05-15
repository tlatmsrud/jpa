package org.study.jpa.space.highlevelmapping.oneToManyJoinTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OTMParent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")

    private String name;

    @OneToMany
    @JoinTable(name = "PARENT_CHILD",
        joinColumns = @JoinColumn(name = "PARENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private List<OTMChild> childList= new ArrayList<>();

}
