package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.domain.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    void insert(Product product);

    long removeByIdAndCategoryId(int categoryId, long productId);

    List<ProductData> selectAllProduct(int limit, int offset);

    int getAllProductCount();

}
