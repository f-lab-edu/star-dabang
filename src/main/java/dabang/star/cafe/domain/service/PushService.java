package dabang.star.cafe.domain.service;

public interface PushService {

    void sendToMember(long id, String title, String message);

    void sendToManager(long id, String title, String message);

    void registerMemberToken(long memberId, String token);

    void registerManagerToken(Long id, String token);
}
