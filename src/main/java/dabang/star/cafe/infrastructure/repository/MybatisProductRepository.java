package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.product.Product;
import dabang.star.cafe.domain.product.ProductOption;
import dabang.star.cafe.domain.product.ProductRepository;
import dabang.star.cafe.infrastructure.mapper.ProductMapper;
import dabang.star.cafe.infrastructure.mapper.ProductOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

            List<ProductOption> options = product.getOptions();
            if (options.size() != 0) {
                options.forEach(option -> option.setProductId(product.getId()));
                productOptionMapper.insertList(product.getOptions());
            }
        }
    }

}
