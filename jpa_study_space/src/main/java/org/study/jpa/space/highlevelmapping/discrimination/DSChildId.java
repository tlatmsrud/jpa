package org.study.jpa.space.highlevelmapping.discrimination;

import java.io.Serializable;
import java.util.Objects;

public class DSChildId implements Serializable {

    private String parent;
    private String childId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DSChildId dsChildId = (DSChildId) o;
        return Objects.equals(parent, dsChildId.parent) && Objects.equals(childId, dsChildId.childId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, childId);
    }
}
