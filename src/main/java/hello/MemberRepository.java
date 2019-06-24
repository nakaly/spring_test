package hello;

public interface MemberRepository {
     Member findMember(String memberName);
     void update(String name, int count);
}
