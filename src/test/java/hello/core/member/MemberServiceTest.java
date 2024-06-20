package hello.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        // given
        Member member = new Member(1L, "tester1", Grade.VIP);
        //when
        memberService.join(member);
        //then
        assertThat(member).isEqualTo(memberService.findMember(member.getId()));
    }
}