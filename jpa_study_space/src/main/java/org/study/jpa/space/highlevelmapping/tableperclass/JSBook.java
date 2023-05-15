package org.study.jpa.space.highlevelmapping.tableperclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class JSBook extends JSItem {

    private String author;

    private String isbn;
}
