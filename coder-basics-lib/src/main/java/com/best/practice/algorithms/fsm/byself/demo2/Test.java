package com.best.practice.algorithms.fsm.byself.demo2;

import static com.best.practice.algorithms.fsm.byself.demo2.EventEnum.*;
import static com.best.practice.algorithms.fsm.byself.demo2.StateEnum.*;

public class Test {
    public static void main(String[] args) {
        Machine machine = new Machine();

        Context context = new Context("洗衣机", "家电", 1);
        machine.tramsform(INIT,CREATE_ORDER,context);
        machine.tramsform(WAIT_PAY,PAY_SUCCESS,context);
        machine.tramsform(WAIT_SHIP,SHIP_ORDER,context);
        machine.tramsform(WAIT_RECEIVE,CONFIRM_RECEIPT,context);
    }
}
