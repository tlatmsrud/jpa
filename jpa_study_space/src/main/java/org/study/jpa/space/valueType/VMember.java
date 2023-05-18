package org.study.jpa.space.valueType;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VMember {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    VPeriod workPeriod;

    @Embedded
    VAddress homeAddress;

    @ElementCollection
    @CollectionTable(name = "ADDRESS"
                    ,joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<VAddress> addressHistory = new ArrayList<>();

}
