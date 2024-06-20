package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Member member = new Member(1L, "tester01", Grade.VIP);
        appConfig.memberService().join(member);

        Order orderItem = appConfig.orderService().createOrder(member.getId(), "item1", 5000);
        System.out.println("orderItem = " + orderItem);
    }
}
