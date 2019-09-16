package hello;

import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class AsyncHelloServiceImpl implements HelloService.AsyncIface {

    @Autowired
    private Sql2o sql2o;

    @Override
    public void hello(String name, AsyncMethodCallback<String> resultHandler) {
        String sql = "SELECT * FROM member where name=:name";

        try (Connection con = sql2o.open()) {
            Thread.sleep(100);
            con.createQuery(sql)
               .addParameter("name", name)
               .executeAndFetchFirst(Member.class);
            resultHandler.onComplete("Hello, " + name + '!');
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}