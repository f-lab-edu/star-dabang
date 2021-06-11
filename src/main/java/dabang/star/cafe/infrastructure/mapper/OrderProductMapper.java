package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.order.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OrderProductMapper {

    void insert(OrderProduct orderProduct);

    void update(OrderProduct orderProduct);

    long addAllPriceByOrderId(long orderId);

    Optional<OrderProduct> getById(long id);
}
