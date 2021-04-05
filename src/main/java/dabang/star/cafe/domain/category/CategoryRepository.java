package dabang.star.cafe.domain.category;

public interface CategoryRepository {

    Integer save(Category category);

    boolean existsByName(String name);

}
