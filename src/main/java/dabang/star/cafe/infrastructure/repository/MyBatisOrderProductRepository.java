package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.order.OrderProduct;
import dabang.star.cafe.domain.order.OrderProductRepository;
import dabang.star.cafe.infrastructure.mapper.OrderProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderProductRepository implements OrderProductRepository {

    private final OrderProductMapper orderProductMapper;

    @Override
    public void save(OrderProduct orderProduct) {
        if (orderProduct.getId() == null) {
            orderProductMapper.insert(orderProduct);
        } else {
            orderProductMapper.update(orderProduct);
        }
    }

    @Override
    public Optional<OrderProduct> findById(long id) {
        return orderProductMapper.getById(id);
    }

    @Override
    public long sumTotalPriceByOrderId(long orderId) {
        return orderProductMapper.addAllPriceByOrderId(orderId);
    }
}
