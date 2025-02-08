package webservice.code.oa.entity;

import java.io.Serializable;

public class WorkflowRequestTableRecord implements Serializable {
    private static final long serialVersionUID = -202638298244870735L;
    private int recordOrder;
    private WorkflowRequestTableField[] workflowRequestTableFields;

    public WorkflowRequestTableRecord() {
    }

    public int getRecordOrder() {
        return this.recordOrder;
    }

    public void setRecordOrder(int var1) {
        this.recordOrder = var1;
    }

    public WorkflowRequestTableField[] getWorkflowRequestTableFields() {
        return this.workflowRequestTableFields;
    }

    public void setWorkflowRequestTableFields(WorkflowRequestTableField[] var1) {
        this.workflowRequestTableFields = var1;
    }
}