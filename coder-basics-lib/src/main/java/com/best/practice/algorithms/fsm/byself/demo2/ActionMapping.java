package com.best.practice.algorithms.fsm.byself.demo2;

import cn.hutool.core.lang.Filter;
import lombok.Data;

/**
 * 状态转移表
 */
@Data
public class ActionMapping {

    /** 当前状态 */
    private StateEnum currentState;

    /** 次态 */
    private StateEnum nextState;

    /** 动作 */
    private Filter<Context> action;

    /** 事件 */
    private EventEnum event;

    public static ActionMapping ofMap(StateEnum currentState, EventEnum event, StateEnum nextState, Filter<Context> action) {
        return new ActionMapping(currentState, event, nextState, action);
    }

    private ActionMapping(StateEnum currentState, EventEnum event, StateEnum nextState, Filter<Context> action) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.action = action;
        this.event = event;
    }
}
