package dabang.star.cafe.domain.option;

import dabang.star.cafe.api.request.OptionCreateRequest;

public class OptionFactory {

    public static Option from(OptionCreateRequest optionCreateRequest) {

        return Option.builder()
                .name(optionCreateRequest.getName())
                .price(optionCreateRequest.getPrice())
                .maxQuantity(optionCreateRequest.getMaxQuantity())
                .build();
    }

}
