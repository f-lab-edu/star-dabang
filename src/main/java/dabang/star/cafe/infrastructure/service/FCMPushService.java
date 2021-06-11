package dabang.star.cafe.infrastructure.service;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.domain.service.PushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDateTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Log4j2
public class FCMPushService implements PushService {

    private static final String CONTENT = "content";
    private static final String BODY = "body";
    private static final String TIME = "time";
    private static final ExecutorService executor
            = new ThreadPoolExecutor(5, 50, 5L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    private final ManagerRepository managerRepository;
    private final MemberRepository memberRepository;

    /**
     * 회원에게 푸시 알림 보내기
     *
     * @param id    알림을 보낼 회원의 아이디
     * @param title 제목
     * @param msg   내용
     */
    @Override
    public void sendToMember(long id, String title, String msg) {
        String token = memberRepository.findTokenById(id).orElseThrow(
                () -> new ResourceNotFoundException("token not found by member id : " + id)
        );
        Message message = getMessage(title, msg, token);
        asyncSend(message);
    }

    /**
     * 매장 관리자에게 푸시 알림 보내기
     *
     * @param id    알림을 보낼 아이디
     * @param title 제목
     * @param msg   내용
     */
    @Override
    public void sendToManager(long id, String title, String msg) {
        String token = managerRepository.findTokenById(id).orElseThrow(
                () -> new ResourceNotFoundException("token not found by manager id : " + id)
        );
        Message message = getMessage(title, msg, token);
        asyncSend(message);
    }

    /**
     * 비동기적으로 메시지 요청을 보내며 콜백함수를 executor 스레드 풀에서 새로운 스레드를 할당하여 등록한 콜백 함수를 실행
     *
     * @param msg 메시지
     */
    private void asyncSend(Message msg) {
        ApiFuture<String> future = FirebaseMessaging.getInstance().sendAsync(msg);
        ApiFutures.addCallback(future, new ApiFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                log.info("Send Message : {}", result);
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("Failed to send message to {}", t);
            }
        }, executor);
    }

    private Message getMessage(String title, String msg, String token) {
        return Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(msg).build())
                .putData(CONTENT, title)
                .putData(BODY, msg)
                .putData(TIME, LocalDateTime.now().toString())
                .setToken(token)
                .build();
    }

    /**
     * 회원의 토큰을 저장
     *
     * @param id    아이디
     * @param token 토큰
     */
    @Override
    public void registerMemberToken(long id, String token) {
        Member member = Member.builder()
                .id(id)
                .token(token)
                .build();

        memberRepository.save(member);
    }

    /**
     * 관리자의 토큰을 저장
     *
     * @param id    아이디
     * @param token 토큰
     */
    @Override
    public void registerManagerToken(Long id, String token) {
        Manager manager = Manager.builder()
                .id(id)
                .token(token)
                .build();

        managerRepository.save(manager);
    }

}
