package dabang.star.cafe.api;

import com.siot.IamportRestClient.exception.IamportResponseException;
import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.DealValidationRequest;
import dabang.star.cafe.application.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/payments")
@RestController
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentService paymentService;

    @LoginCheck
    @PostMapping("/complete")
    public void dealValidation(@RequestBody DealValidationRequest dealValidationRequest) throws IamportResponseException, IOException {
        paymentService.verify(dealValidationRequest.getImpUid(), dealValidationRequest.getMerchantUid());
    }

}
