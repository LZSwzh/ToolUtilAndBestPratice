package webservice.code.oa.entity;

import java.io.Serializable;

public class WorkflowMainTableInfo implements Serializable {
    private static final long serialVersionUID = -8777023633784990177L;
    private String tableDBName;
    private WorkflowRequestTableRecord[] requestRecords;

    public WorkflowMainTableInfo() {
    }

    public String getTableDBName() {
        return this.tableDBName;
    }

    public void setTableDBName(String var1) {
        this.tableDBName = var1;
    }

    public WorkflowRequestTableRecord[] getRequestRecords() {
        return this.requestRecords;
    }

    public void setRequestRecords(WorkflowRequestTableRecord[] var1) {
        this.requestRecords = var1;
    }
}
