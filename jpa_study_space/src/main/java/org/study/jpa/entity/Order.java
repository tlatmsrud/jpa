package org.study.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

}
