package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // 다 : 1 연관관계 어노테이션
    @JoinColumn(name = "member_id") //매핑을 member_id로 한다. 외래키의 이름이 member_id
    private Member member;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Delivery delivery;

    private LocalDateTime orderDate; //hibernate가 자동으로 지원해주기때문에 시간관련 어노테이션이 필요가 없다

    private OrderStatus status; // 주문 상태
}
