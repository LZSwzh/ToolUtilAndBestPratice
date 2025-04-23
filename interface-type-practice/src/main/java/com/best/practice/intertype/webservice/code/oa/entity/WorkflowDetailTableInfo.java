package com.best.practice.intertype.webservice.code.oa.entity;

import java.io.Serializable;

public class WorkflowDetailTableInfo implements Serializable {
    private static final long serialVersionUID = 1328729781044551608L;
    private String tableTitle;
    private String tableDBName;
    private String[] tableFieldName;
    private WorkflowRequestTableRecord[] workflowRequestTableRecords;

    public WorkflowDetailTableInfo() {
    }

    public String getTableTitle() {
        return this.tableTitle;
    }

    public void setTableTitle(String var1) {
        this.tableTitle = var1;
    }

    public String getTableDBName() {
        return this.tableDBName;
    }

    public void setTableDBName(String var1) {
        this.tableDBName = var1;
    }

    public String[] getTableFieldName() {
        return this.tableFieldName;
    }

    public void setTableFieldName(String[] var1) {
        this.tableFieldName = var1;
    }

    public WorkflowRequestTableRecord[] getWorkflowRequestTableRecords() {
        return this.workflowRequestTableRecords;
    }

    public void setWorkflowRequestTableRecords(WorkflowRequestTableRecord[] var1) {
        this.workflowRequestTableRecords = var1;
    }
}
