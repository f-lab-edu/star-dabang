package dabang.star.cafe.utils.page;

import lombok.Getter;

@Getter
public class Pagination {

    public static final int DEFAULT_PAGE = 1;

    private static final int DEFAULT_SIZE = 20;

    private static final int MAX_SIZE = 50;

    private int page;

    private int size;

    public Pagination(Integer page, Integer size) {
        this.page = setPage(page);
        this.size = setSize(size);
    }

    private int setSize(Integer size) {
        if (size == null || size > MAX_SIZE) {
            return DEFAULT_SIZE;
        }
        return size;
    }

    private int setPage(Integer page) {
        if (page == null || page < DEFAULT_PAGE) {
            return DEFAULT_PAGE;
        }
        return page;
    }

    public int getOffset() {
        return (page - 1) * size;
    }

}
