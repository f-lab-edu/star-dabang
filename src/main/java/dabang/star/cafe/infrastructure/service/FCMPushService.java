package dabang.star.cafe.infrastructure.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.push.PushService;
import dabang.star.cafe.domain.push.Token;
import dabang.star.cafe.domain.push.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMPushService implements PushService {

    private static final String CONTENT = "content";
    private static final String BODY = "body";
    private static final String TIME = "time";

    private final TokenRepository tokenRepository;

    /**
     * 회원에게 푸시 알림 보내기
     *
     * @param memberId 알림을 보낼 회원의 아이디
     * @param title    제목
     * @param msg      내용
     * @return 성공여부 (실패시 null 반환)
     */
    @Override
    public String sendToMember(long memberId, String title, String msg) {
        String token = tokenRepository.findTokenByMemberId(memberId).orElseThrow(
                () -> new ResourceNotFoundException("token not found by member : " + memberId)
        );

        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(msg).build())
                .putData(CONTENT, title)
                .putData(BODY, msg)
                .putData(TIME, LocalDateTime.now().toString())
                .setToken(token)
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    /**
     * 토큰이 존재하지 않는다면 저장, 존재하지 않는다면 변경 후 저장
     */
    @Override
    public void saveToken(long memberId, String token) {
        Optional<Token> getToken = tokenRepository.findByMemberId(memberId);
        Token newToken;

        if (!getToken.isPresent()) {
            newToken = getToken.get();
            newToken.setTokenInfo(token);
        } else {
            newToken = Token.builder()
                    .memberId(memberId)
                    .tokenInfo(token)
                    .build();
        }

        tokenRepository.save(newToken);
    }

}
