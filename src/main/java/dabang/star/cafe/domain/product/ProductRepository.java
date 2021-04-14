package dabang.star.cafe.domain.product;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    long deleteById(long productId);

    Page<ProductData> findAll(Pagination pagination);

    Optional<ProductData> findById(long productId);

    long deleteOptionById(long productId, List<Long> deleteOptions);

    void saveOption(long productId, List<ProductOption> option);

}
