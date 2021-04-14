package dabang.star.cafe.utils.page;

import java.util.List;

public class PageFactory {

    public static <T> Page<T> create(List<T> content, int totalElements, int size, int pageNumber) {
        return new Page<>(content, totalElements, size, pageNumber);
    }
}
