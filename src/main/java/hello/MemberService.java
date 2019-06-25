package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private Sql2o sql2o;

    public Member update(String name) {
        try(Connection con = sql2o.beginTransaction()) {
            memberRepository.update(con, name, 1);
            memberRepository.update(con, name, 2);
//        memberRepository.update(name, 1);
//        memberRepository.update(name, 2);
            throw new RuntimeException("");
            //var member = memberRepository.findMember("test");
            //return member;
        }
    }
}
