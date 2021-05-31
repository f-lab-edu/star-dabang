package dabang.star.cafe.domain.push;

public interface PushService {

    String sendToMember(long memberId, String title, String message);

    void saveToken(long memberId, String token);

}
