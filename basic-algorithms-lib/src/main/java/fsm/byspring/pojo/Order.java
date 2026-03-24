package fsm.byspring.pojo;

import fsm.byspring.pojo.enums.OrderState;
import lombok.Data;

@Data
public class Order {
    /** 订单ID */
    private Long id;

    /** 订单状态 */
    private OrderState state;


}
