package dabang.star.cafe.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Order {

    private Long id;

    private Long memberId;

    private Integer officeId;

    private LocalDateTime orderTime;

    private LocalDateTime approveTime;

    private LocalDateTime completionTime;

}
