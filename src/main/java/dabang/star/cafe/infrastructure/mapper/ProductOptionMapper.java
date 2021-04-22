package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.product.ProductOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductOptionMapper {

    void insertList(List<ProductOption> productOptions);

    void updateList(List<ProductOption> productOptions);

    long removeById(long productId, List<Long> deleteOptions);

}
