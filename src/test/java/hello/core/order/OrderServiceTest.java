package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Test
    void createOrder() {
        final MemberService memberService = new MemberServiceImpl();
        final OrderService orderService = new OrderServiceImpl();
        //given
        Member member = new Member(1L, "tester01", Grade.VIP);
        memberService.join(member);
        //when
        Order createdOrder = orderService.createOrder(member.getId(), "item01", 5000);
        //then
        assertThat(createdOrder.getMemberId()).isEqualTo(member.getId());
        assertThat(createdOrder.getItemName()).isEqualTo("item01");
        assertThat(createdOrder.getItemPrice()).isEqualTo(5000);
        assertThat(createdOrder.getDiscountPrice()).isEqualTo(1000);
    }

}