package dabang.star.cafe.utils.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {

    List<T> content;

    int totalCount;
}
