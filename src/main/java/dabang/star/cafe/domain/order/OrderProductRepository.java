package dabang.star.cafe.domain.order;

import java.util.Optional;

public interface OrderProductRepository {

    void save(OrderProduct orderProduct);

    Optional<OrderProduct> findById(long id);

    long sumTotalPriceByOrderId(long orderId);
}
