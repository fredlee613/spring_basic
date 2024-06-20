package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
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