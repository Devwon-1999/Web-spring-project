package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 다 : 1 연관관계 어노테이션
    @JoinColumn(name = "member_id") //매핑을 member_id로 한다. 외래키의 이름이 member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id") // 주문에서 배송을 조회할 경우가 많기때문에 주문이 주가된다.
    private Delivery delivery;

    private LocalDateTime orderDate; //hibernate가 자동으로 지원해주기때문에 시간관련 어노테이션이 필요가 없다

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태

    //연관관계 편의 매서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
