package org.study.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(CategoryItemId.class)
public class CategoryItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

}
