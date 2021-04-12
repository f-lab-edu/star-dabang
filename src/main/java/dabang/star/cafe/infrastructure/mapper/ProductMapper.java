package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    void insert(Product product);

}
