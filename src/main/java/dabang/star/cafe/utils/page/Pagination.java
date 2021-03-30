package dabang.star.cafe.utils.page;

import lombok.Getter;

@Getter
public class Pagination {

    private Integer page;

    private Integer size;

    public Pagination(Integer page, Integer size) {
        this.page = page == null ? 0 : page;
        this.size = size == null ? 20 : size;
    }

    public int getOffset() {
        return page * size;
    }
    
}
