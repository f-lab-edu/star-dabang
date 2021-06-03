package dabang.star.cafe.application;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import dabang.star.cafe.application.exception.PaymentException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.order.OrderProductRepository;
import dabang.star.cafe.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Profile("prod")
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamportClient iamportClient;

    private final OrderProductRepository orderProductRepository;

    private final OrderRepository orderRepository;

    public void verify(String impUid, String merchantUid) throws IamportResponseException, IOException {
        long orderId = Long.parseLong(merchantUid);

        long orderAmount = orderProductRepository.sumTotalPriceByOrderId(orderId);
        if (orderAmount == 0) {
            throw new ResourceNotFoundException("없는 주문입니다. (주문번호: " + orderId + ")");
        }

        Payment payment = iamportClient.paymentByImpUid(impUid).getResponse();
        long paymentAmount = payment.getAmount().longValue();
        String status = payment.getStatus();

        if (paymentAmount != orderAmount || !status.equals("success")) {
            orderRepository.deleteById(orderId);
            throw new PaymentException("정상적이지 않은 결제입니다. (주문번호: " + orderId + ")");
        }
    }
}
