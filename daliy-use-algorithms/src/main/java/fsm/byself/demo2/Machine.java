package fsm.byself.demo2;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.IdUtil;

import java.util.LinkedList;
import java.util.List;

import static fsm.byself.demo2.EventEnum.*;
import static fsm.byself.demo2.StateEnum.*;

/**
 * 状态机
 */
public class Machine {
    /**
     * 状态转移表
     */
    private List<ActionMapping> mappings = new LinkedList<>();

    {
        //初始化--->待支付
        mappings.add(ActionMapping.ofMap(INIT,CREATE_ORDER,WAIT_PAY,this::createOrder));
        //待支付-->待接单
        mappings.add(ActionMapping.ofMap(WAIT_PAY,PAY_SUCCESS,WAIT_RECEIVE,this::payForOrder));
        //待接单 --> 待发货
        mappings.add(ActionMapping.ofMap(WAIT_ACCEPT,ACCEPT_ORDER,WAIT_SHIP,this::acceptOrder));
        //待发货 --> 待收货
        mappings.add(ActionMapping.ofMap(WAIT_SHIP,SHIP_ORDER,WAIT_RECEIVE,this::sendOrder));
        //待收货 --> 交易完成
        mappings.add(ActionMapping.ofMap(WAIT_RECEIVE,CONFIRM_RECEIPT,COMPLETED,this::completedOrder));
        //待收货 --> 退货中
        mappings.add(ActionMapping.ofMap(WAIT_RECEIVE,RETURN,RETURNING,this::returnOrder));
        //退货中 -->  交易完成
        mappings.add(ActionMapping.ofMap(RETURNING,ACCEPT_RETURN,COMPLETED,this::completedOrder));
    }

    public boolean tramsform(StateEnum state,EventEnum event,Context context){
        ActionMapping mapping = getMapping(state, event);
        if (mapping==null) throw new RuntimeException("不存在该状态映射");
        //执行动作
        Filter<Context> action = mapping.getAction();
        action.accept(context);

        return true;
    }
    /** 根据传入当前状态和事件,获取动作和状态的映射 */
    private ActionMapping getMapping(StateEnum  currentState, EventEnum event) {
        if (mappings.size() > 0) {
            for (ActionMapping n : mappings) {
                if (n.getCurrentState().equals(currentState) && n.getEvent().equals(event)) {
                    return n;
                }
            }
        }
        return null;
    }

    /**动作:创建订单*/
    public boolean createOrder(Context context){
        System.out.println("1......用户尝试创建订单:"+context);
        System.out.println("数据库查询库存是否足够");
        long orderId = IdUtil.getSnowflake(1, 1).nextId();
        System.out.println("数据库库存重组,成功创建订单:"+orderId);

        return true;
    }

    /**动作:支付*/
    public boolean payForOrder(Context context) {
        System.out.println("2......用户尝试支付订单:"+context);
        System.out.println("调用相关api查询余额是否充足");
        System.out.println("余额充足,开始转账....");
        return true;
    }
    /**动作:接单*/
    public boolean acceptOrder(Context context) {
        System.out.println("3......商家开始接单:"+context);
        return true;
    }
    /**动作:发货*/
    public boolean sendOrder(Context context){
        System.out.println("4......商品开始邮寄:"+context);
        return true;
    }
    /**动作:收货*/
    public boolean receiveOrder(Context context) {
        System.out.println("5.....用户收货:"+context);
        return true;
    }
    /**动作:退货*/
    public boolean returnOrder(Context context) {
        System.out.println("5.....用户退货:"+context);
        return true;
    }
    /**动作:取消订单*/
    public boolean rejectOrder(Context context) {
        System.out.println("?......用户取消订单:"+context);
        return true;
    }
    /**动作:交易完成*/
    public boolean completedOrder(Context context) {
        System.out.println("final......交易完成:"+context);
        System.out.println("发送消息通知商户和买家");
        return true;
    }
}
