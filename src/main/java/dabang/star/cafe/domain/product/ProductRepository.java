package dabang.star.cafe.domain.product;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

public interface ProductRepository {

    void save(Product product);

    long deleteByIdAndCategoryId(int categoryId, long productId);

    Page<ProductData> findAll(Pagination pagination);

}
