package hello;

import org.sql2o.Connection;

public interface MemberRepository {
     Member findMember(String memberName);
     void update(Connection con, String name, int count);
     void update(String name, int count);
}
