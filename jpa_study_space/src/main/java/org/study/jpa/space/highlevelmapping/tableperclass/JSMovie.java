package org.study.jpa.space.highlevelmapping.tableperclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class JSMovie extends JSItem {

    private String director;

    private String actor;
}
