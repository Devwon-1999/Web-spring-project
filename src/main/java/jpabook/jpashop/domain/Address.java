package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //어딘가에 내장될 수 있다는 의미의 어노테이션
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
