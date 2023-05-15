package org.study.jpa.space.highlevelmapping.manyToOneJoinTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MTOParent {

    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<MTOChild> childList = new ArrayList<>();
}
