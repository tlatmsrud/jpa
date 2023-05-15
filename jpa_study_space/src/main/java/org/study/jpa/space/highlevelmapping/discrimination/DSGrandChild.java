package org.study.jpa.space.highlevelmapping.discrimination;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(DSGrandChildId.class)
public class DSGrandChild {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private DSChild child;

    @Id @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;


}
