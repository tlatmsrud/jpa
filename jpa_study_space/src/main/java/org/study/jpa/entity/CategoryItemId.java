package org.study.jpa.entity;

import java.io.Serializable;
import java.util.Objects;

public class CategoryItemId implements Serializable {

    private Long category;

    private Long item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryItemId that = (CategoryItemId) o;
        return Objects.equals(category, that.category) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, item);
    }
}
