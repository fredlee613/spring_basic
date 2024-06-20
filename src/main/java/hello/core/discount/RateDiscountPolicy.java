package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{
    private double discountRatio = 0.1;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return (int) (price * discountRatio);
        return 0;
    }
}
