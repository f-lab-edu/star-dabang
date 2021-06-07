package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.order.Order;
import dabang.star.cafe.domain.order.OrderRepository;
import dabang.star.cafe.infrastructure.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepository implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            orderMapper.insert(order);
        } else {
            orderMapper.update(order);
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderMapper.getById(id);
    }

    @Override
    public void deleteById(long orderId) {
        orderMapper.removeById(orderId);
    }
}
