package org.study.jpa.space.valueType;

import javax.persistence.Embeddable;

@Embeddable
public class VAddress {

    private String city;
    private String street;
    private String zipcode;
}
