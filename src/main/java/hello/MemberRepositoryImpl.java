package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public Member findMember(String memberName) {
        String sql = "SELECT * FROM member where name=:name";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                      .addParameter("name", memberName)
                      .executeAndFetchFirst(Member.class);
        }
    }

}
