package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestDiscountAppConfig.class);

    @Test
    @DisplayName("When find multiple beans with same parent class, throws NoUniqueBeanDefinitionException")
    void findBeansWithSameParentClass() {
        assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class)).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("To avoid NoUniqueBeanDefinitionException, we can specify the bean by name")
    void findBeansWithSameParentClassByName() {
        ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

    }

    @Test
    @DisplayName("find beans by child class")
    void findBeansByChildClass() {
        assertThat(ac.getBean(FixDiscountPolicy.class)).isInstanceOf(FixDiscountPolicy.class);
        assertThat(ac.getBean(RateDiscountPolicy.class)).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("find all beans with same parent")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", bean = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("find all beans with Object.class")
    void findAllBeanByObjectClass() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", bean = " + beansOfType.get(key));
        }
    }

    @TestConfiguration
    static class TestDiscountAppConfig{
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
    }
}
