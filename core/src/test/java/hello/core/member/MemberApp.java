package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    MemberService memberService ;

    @BeforeEach
    public void beforEach() {
        //AppConfig appConfig1 = new AppConfig();
        //memberService = appConfig1.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);

    }


    @Test
    void member() {
        //given
        Member member = new Member(1L, "memberId", Grade.VIP);

        //when
        memberService.join(member);
        Member member1 = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(member1);

    }
}
