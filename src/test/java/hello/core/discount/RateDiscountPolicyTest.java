package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    void vip_o() {
        //given
        Member member = new Member(1L, "tester01", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 5000);
        //then
        assertThat(discount).isEqualTo(500);
    }

    @Test
    void vip_x() {
        //given
        Member member = new Member(2L, "tester02", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 5000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}