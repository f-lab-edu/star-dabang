package dabang.star.cafe.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Page {

    private static final int MAX_LIMIT = 100;

    private int offset = 0;

    private int limit = 20;

    public Page(int offset, int limit) {
        setOffset(offset);
        setLimit(limit);
    }

    private void setOffset(int offset) {
        this.offset = Math.max(offset, 0);
    }

    private void setLimit(int limit) {
        this.limit = Math.min(limit, MAX_LIMIT);
    }
}
