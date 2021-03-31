package dabang.star.cafe.utils.page;

import lombok.Getter;

@Getter
public class Pagination {

    private static final int DEFAULT_PAGE = 100;

    private static final int DEFAULT_SIZE = 20;

    private Integer page;

    private Integer size;

    public Pagination(Integer page, Integer size) {
        this.page = page == null ? DEFAULT_PAGE : page;
        this.size = size == null ? DEFAULT_SIZE : size;
    }

    public int getOffset() {
        return page * size;
    }

}
