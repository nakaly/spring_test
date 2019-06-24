package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member update(String name) {
        memberRepository.update(name, 1);
        memberRepository.update(name, 2);
        throw new RuntimeException("");
        //var member = memberRepository.findMember("test");
        //return member;
    }
}
