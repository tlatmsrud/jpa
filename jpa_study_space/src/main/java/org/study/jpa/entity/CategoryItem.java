package org.study.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.study.jpa.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(CategoryItemId.class)
public class CategoryItem extends BaseEntity{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

}
