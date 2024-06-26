package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 스프링 빈 조회 - 동일한 타입이 둘 이상
 */
public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testAppConfig.class);

    @Test
    @DisplayName("finding beans with duplicated type will cause NoUniqueBeanDefinitionException")
    void findTwoBeansWithSameType() {
        assertThatThrownBy(() -> ac.getBean(MemberRepository.class)).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("To avoid NoUniqueBeanDefinitionException, we can specify the bean by using bean names")
    void findBeanByName() {
        ac.getBean("memberRepository1", MemberRepository.class);
        ac.getBean("memberRepository2", MemberRepository.class);
    }

    @Test
    @DisplayName("Find certain type of beans")
    void findBeansByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String s : beansOfType.keySet()) System.out.println("name = " + s + "bean = " + beansOfType.get(s));
    }

    @TestConfiguration
    static class testAppConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
