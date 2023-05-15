package org.study.jpa.space.highlevelmapping.discrimination;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DSParent {

    @Id @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
