package hello;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class HelloServiceImpl implements HelloService.Iface {

    @Autowired
    private Sql2o sql2o;

    @Override
    public String hello(String name) throws TException {

        String sql = "SELECT * FROM member where name=:name";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
               .addParameter("name", name)
               .executeAndFetchFirst(Member.class);

            return "Hello, " + name;
        }
    }
}