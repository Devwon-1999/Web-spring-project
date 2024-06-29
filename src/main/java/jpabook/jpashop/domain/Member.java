package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 1 : 다 연관관계 어노테이션 mappedBy는 연관관계의 주인이 아님을 명시
    //Order 클래스의 member 필드에 의해 매핑된다는 의미
    private List<Order> orders = new ArrayList<>();
    //Entity는 최대한 연관성이 없게 끔 개발해야 유지보수에 좋다.
}
