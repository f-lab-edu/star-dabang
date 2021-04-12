package dabang.star.cafe.domain.product;

public interface ProductRepository {

    void save(Product product);

    long deleteById(long productId);

}
