package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.application.data.ProductData;
import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import dabang.star.cafe.domain.product.ProductRepository;
import dabang.star.cafe.infrastructure.mapper.ProductMapper;
import dabang.star.cafe.infrastructure.mapper.ProductOptionMapper;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MybatisProductRepository implements ProductRepository {

    private final ProductMapper productMapper;
    private final ProductOptionMapper productOptionMapper;

    @Transactional
    @Override
    public void save(Product product) {

        if (product.getId() == null) {
            productMapper.insert(product);
            saveProductOption(product.getId(), product.getOptions());
        } else {
            productMapper.update(product);
            updateProductOption(product.getId(), product.getOptions());
        }
    }

    private void saveProductOption(long productId, List<ProductOption> options) {
        if (options.size() != 0) {
            options.forEach(option -> option.setProductId(productId));
            productOptionMapper.insertList(options);
        }
    }

    private void updateProductOption(long productId, List<ProductOption> options) {
        if (options.size() != 0) {
            options.forEach(option -> option.setProductId(productId));
            productOptionMapper.updateList(options);
        }
    }

    @Override
    public long deleteByIdAndCategoryId(int categoryId, long productId) {
        return productMapper.removeByIdAndCategoryId(categoryId, productId);
    }

    @Override
    public Page<ProductData> findAll(Pagination pagination) {

        int size = pagination.getSize();
        int offset = pagination.getOffset();
        int page = pagination.getPage();

        List<ProductData> productDataList = productMapper.selectAllProduct(size, offset);
        int totalCount = productMapper.getAllProductCount();

        return Page.from(productDataList, totalCount, size, page);
    }

    @Override
    public Optional<ProductData> findByIdAndCategoryId(int categoryId, long productId) {
        return productMapper.getByIdAndCategoryId(categoryId, productId);
    }

    @Override
    public long deleteOptionById(long productId, List<Long> deleteOptions) {
        if (deleteOptions.size() != 0) {
            return productOptionMapper.removeById(productId, deleteOptions);
        }
        return 0;
    }

    @Override
    public void saveOption(long productId, List<ProductOption> options) {
        if (options.size() != 0) {
            saveProductOption(productId, options);
        }
    }

    @Override
    public List<ProductData> findAllByCategoryId(int categoryId) {
        return productMapper.getAllByCategoryId(categoryId);
    }

    @Override
    public List<ProductData> findAllByCategoryIdAndActive(int categoryId) {
        return productMapper.getAllByCategoryIdAndActive(categoryId);
    }

    @Override
    public Optional<ProductData> findById(long productId) {
        return productMapper.getById(productId);
    }

    @Override
    public List<ProductData> findByIds(List<Long> productIds) {
        return productMapper.getByIds(productIds);
    }

}
