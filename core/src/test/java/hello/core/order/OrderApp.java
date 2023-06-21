package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {


    OrderService orderService ;
    MemberService memberService ;

    @BeforeEach
    public void beforEach() {
       // AppConfig appConfig = new AppConfig();
       // orderService = appConfig.orderService();
        //memberService = appConfig.memberService();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        orderService = ac.getBean(OrderService.class);
        memberService = ac.getBean( MemberService.class);
    }

    @Test
    void test() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);
        memberService.join(member);
        Order orderA = orderService.createOrder(member.getId(), "itemA", 10000);

        //when
        int price = orderA.calculatePrice();

        //then
        Assertions.assertThat(price).isEqualTo(9000);
        Assertions.assertThat(orderA.getDiscountPrice()).isEqualTo(1000);
        System.out.println("order = " + orderA);

    }
}
