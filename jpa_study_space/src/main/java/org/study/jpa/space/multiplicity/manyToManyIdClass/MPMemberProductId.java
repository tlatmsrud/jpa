package org.study.jpa.space.multiplicity.manyToManyIdClass;

import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
public class MPMemberProductId implements Serializable {

    private String member;
    private String product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MPMemberProductId that = (MPMemberProductId) o;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}
