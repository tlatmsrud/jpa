package org.study.jpa.space.highlevelmapping.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class EBParent {

    @EmbeddedId
    private EBParentId id;

    private String name;
}
