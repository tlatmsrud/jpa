package org.study.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member){
        /*
            기존 연관관계 제거를 위한 로직
            - 현재 주문건(this)이 A Member 에서 B Member로 수정한다고 가정.
              1) this.member는 A Member 되며, A Member getOrderList()에 현재 주문건이 들어있다는 뜻이다.
              2) A Member의 주문 건을 B Member로 수정하기 위해서는 A Member의 OrderList에 들어있는 주문건을 제거해준 후 B Member의 OrderList에 넣어야한다.
              3) 이를 위해 this.member.getOrderList().remove(this) 후 this.member = member, member.getOrderList().add(this) 구문을 넣는다.
            - 이러한 편의 메서드를 사용하면 순수 객체 형태의 연관관계를 올바르게 가져갈 수 있다.
         */
        if(this.member != null){

            this.member.getOrderList().remove(this);
        }
        this.member = member;
        member.getOrderList().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
