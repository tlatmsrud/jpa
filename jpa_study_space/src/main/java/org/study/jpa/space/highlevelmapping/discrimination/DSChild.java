package org.study.jpa.space.highlevelmapping.discrimination;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(DSChildId.class)
@Getter
@Setter
public class DSChild {

    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private DSParent parent;

    @Id @Column(name = "CHILD_ID")
    private String childId;

    private String name;

}
