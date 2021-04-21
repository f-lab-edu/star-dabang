package dabang.star.cafe.utils.page;

import lombok.Getter;

import java.util.List;

import static dabang.star.cafe.utils.page.Pagination.DEFAULT_PAGE;

@Getter
public class Page<T> {

    private List<T> content;

    private int totalElements;

    private int totalPages;

    private boolean last;

    private boolean first;

    private int size;

    private int pageNumber;

    private int pageNumberOfElements;

    /**
     * content : 데이터 목록
     * totalElements : 전체 데이터 개수
     * size : 데이터를 조회한 양
     * pageNumber : 현재 페이지 번호
     * first : 첫번째 페이지 여부
     * last : 마지막 페이지 여부
     * pageNumberOfElements : 해당 페이지의 데이터 개수
     */
    public Page(List<T> content, int totalElements, int size, int pageNumber) {
        this.content = content;
        this.totalElements = totalElements;
        this.size = size;
        this.pageNumber = pageNumber;
        this.totalPages = setTotalPages(totalElements, size);
        this.first = setFirst(pageNumber);
        this.last = setLast(pageNumber);
        this.pageNumberOfElements = content.size();
    }

    private int setTotalPages(int totalElements, int size) {
        if (totalElements % size == 0) {
            return totalElements / size;
        }
        return totalElements / size + 1;
    }

    private boolean setLast(int pageNumber) {
        return pageNumber == this.totalPages;
    }

    private boolean setFirst(int pageNumber) {
        return pageNumber == DEFAULT_PAGE;
    }

    public static <T> Page<T> from(List<T> content, int totalElements, int size, int pageNumber) {
        return new Page<>(content, totalElements, size, pageNumber);
    }

}
