package fsm.byself.demo;

public class StateMachineDemo {

    //流转状态
    private FlowStateEnum  flowState;

    //构造器,将状态设置为员工提交态
    public StateMachineDemo() {
        this.flowState = FlowStateEnum.SUBMIT_APPLY;
    }

    //转换流转状态
    public void perform(String condition) {
        flowState = flowState.transition(condition);
    }

    public static void main(String[] args) {
        StateMachineDemo stateMachineDemo = new StateMachineDemo();
        stateMachineDemo.perform("arg1...");
        stateMachineDemo.perform("arg2...");
        stateMachineDemo.perform("arg3...");
        stateMachineDemo.perform("arg4...");
    }
}
