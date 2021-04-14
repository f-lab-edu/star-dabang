package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.domain.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    void insert(Product product);

    void update(Product product);

    long removeById(long id);

    List<ProductData> selectAllProduct(int limit, int offset);

    int getAllProductCount();

    Optional<ProductData> getById(long productId);

}
