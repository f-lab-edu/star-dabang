package dabang.star.cafe.domain.order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(long id);

    void deleteById(long orderId);
}
