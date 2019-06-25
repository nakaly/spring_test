package hello;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;
import org.sql2o.connectionsources.DataSourceConnectionSource;

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

    @Override
    public void update(String name, int count) {
        String sql = "Update member SET count=:count where name=:name";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
               .addParameter("name", name)
               .addParameter("count", count)
               .executeUpdate();
        }
    }

    @Override
    public void update(Connection con, String name, int count) {
        String sql = "Update member SET count=:count where name=:name";
        con.createQuery(sql)
           .addParameter("name", name)
           .addParameter("count", count)
           .executeUpdate();

    }

}
