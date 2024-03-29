package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s! I'm %s";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        var member = memberService.update("test");

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name, member.getName()));
    }
}
