package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 컨테이너에 등록된 모든 빈 조회/ 스프링 빈 조회 기초
 */
public class ApplicationContextBasicFindTest {
    //given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);

            }
        }
    }

    @Test
    @DisplayName("find bean by name")
    void findBeanByName() {
        //when
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("find bean by type")
    void findBeanByType() {
        //when
        MemberService memberService = ac.getBean(MemberService.class);
        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("find bean by class")
    void findBeanByClass() {
        //when
        MemberServiceImpl memberServiceImpl = ac.getBean(MemberServiceImpl.class);
        //then
        assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("find bean that it's not registered")
    void findBeanThatDoesNotExist() {
        assertThatThrownBy(() -> {ac.getBean("xxxxxxx", MemberService.class);})
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
