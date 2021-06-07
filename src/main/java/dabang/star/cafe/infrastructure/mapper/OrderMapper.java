package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OrderMapper {

    void insert(Order order);

    void update(Order order);

    Optional<Order> getById(long id);

    void removeById(long orderId);

}
