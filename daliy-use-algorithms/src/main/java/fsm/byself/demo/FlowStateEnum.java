package fsm.byself.demo;

public enum FlowStateEnum {
    /**下面的语法代表一个FlowState的枚举常量,每个常量都是FlowStateEnum的一个实例,可以有自己的具体实现*/

    //发起流程
    SUBMIT_APPLY{
        @Override
        FlowStateEnum transition(String condition){
            System.out.println(String.format("员工发起流程,同步到部门领导审批,参数:%s",condition));
            return DEPT_LEADER_AUDIT;
        }
    },

    DEPT_LEADER_AUDIT{
        @Override
        FlowStateEnum transition(String condition){
            System.out.println(String.format("给员工发送消息：部门领导审批完成,同步状态到HR,参数:%s",condition));
            return HR;
        }
    },
    HR{
        @Override
        FlowStateEnum transition(String condition){
            System.out.println(String.format("给员工发送消息：HR审批完成,同步到结束组件,参数%s",condition));
            return END;
        }
    },
    END{
        @Override
        FlowStateEnum transition(String condition){
            System.out.println(String.format("给员工发送消息：流程结束,参数%s",condition));
            return this;
        }
    };
    /** 枚举中定义抽象方法,在具体的实例中定义实现 */
    abstract FlowStateEnum transition(String condition);
}
