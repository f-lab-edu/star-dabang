package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.domain.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    void insert(Product product);

    long removeByIdAndCategoryId(int categoryId, long productId);

    void update(Product product);

    List<ProductData> selectAllProduct(int limit, int offset);

    int getAllProductCount();

    Optional<ProductData> getByIdAndCategoryId(int categoryId, long productId);

    List<ProductData> getAllByCategoryId(int categoryId);

    List<ProductData> getAllByCategoryIdAndActive(int categoryId);

}
