package dabang.star.cafe.domain.option;

import dabang.star.cafe.api.request.OptionCreateRequest;
import dabang.star.cafe.api.request.OptionUpdateRequest;

public class OptionFactory {

    public static Option from(OptionCreateRequest optionCreateRequest) {

        return Option.builder()
                .name(optionCreateRequest.getName())
                .price(optionCreateRequest.getPrice())
                .maxQuantity(optionCreateRequest.getMaxQuantity())
                .build();
    }

    public static Option from(OptionUpdateRequest optionUpdateRequest) {

        return Option.builder()
                .id(optionUpdateRequest.getId())
                .name(optionUpdateRequest.getName())
                .price(optionUpdateRequest.getPrice())
                .maxQuantity(optionUpdateRequest.getMaxQuantity())
                .build();
    }

}
