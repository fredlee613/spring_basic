package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    @Override
    public int discount(Member member, int price) {
        int discount = 0;
        if (member.getGrade() == Grade.VIP) return 1000;
        else return 0;
    }
}
