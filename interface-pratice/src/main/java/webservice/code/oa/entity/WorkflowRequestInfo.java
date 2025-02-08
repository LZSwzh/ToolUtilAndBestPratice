package webservice.code.oa.entity;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class WorkflowRequestInfo implements Serializable {
    private static final long serialVersionUID = 4236890406953604169L;
    private String requestId;
    private String requestName;
    private String requestLevel;
    private String messageType;
    private WorkflowBaseInfo workflowBaseInfo;
    private String currentNodeName;
    private String currentNodeId;
    private String status;
    private String creatorId;
    private String creatorName;
    private String createTime;
    private String lastOperatorName;
    private String lastOperateTime;
    private String receiveTime;
    private boolean canView;
    private boolean canEdit;
    private boolean mustInputRemark;
    private boolean needAffirmance;
    private String submitButtonName;
    private String subnobackButtonName;
    private String subbackButtonName;
    private String rejectButtonName;
    private String forwardButtonName;
    private WorkflowMainTableInfo workflowMainTableInfo;
    private WorkflowDetailTableInfo[] workflowDetailTableInfos;
    private WorkflowRequestLog[] workflowRequestLogs;
    private String[] WorkflowHtmlTemplete;
    private String[] WorkflowHtmlShow;
    private String[][] workflowPhrases;
    private String remark;
    private String isnextflow;

    public WorkflowRequestInfo() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String var1) {
        this.requestId = var1;
    }

    public String getRequestName() {
        return this.requestName;
    }

    public void setRequestName(String var1) {
        this.requestName = var1;
    }

    public String getRequestLevel() {
        return this.requestLevel;
    }

    public void setRequestLevel(String var1) {
        this.requestLevel = var1;
    }

    public WorkflowBaseInfo getWorkflowBaseInfo() {
        return this.workflowBaseInfo;
    }

    public void setWorkflowBaseInfo(WorkflowBaseInfo var1) {
        this.workflowBaseInfo = var1;
    }

    public String getCurrentNodeName() {
        return this.currentNodeName;
    }

    public void setCurrentNodeName(String var1) {
        this.currentNodeName = var1;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String var1) {
        this.creatorId = var1;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String var1) {
        this.creatorName = var1;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String var1) {
        this.createTime = var1;
    }

    public String getLastOperatorName() {
        return this.lastOperatorName;
    }

    public void setLastOperatorName(String var1) {
        this.lastOperatorName = var1;
    }

    public String getLastOperateTime() {
        return this.lastOperateTime;
    }

    public void setLastOperateTime(String var1) {
        this.lastOperateTime = var1;
    }

    public WorkflowMainTableInfo getWorkflowMainTableInfo() {
        return this.workflowMainTableInfo;
    }

    public void setWorkflowMainTableInfo(WorkflowMainTableInfo var1) {
        this.workflowMainTableInfo = var1;
    }

    public WorkflowDetailTableInfo[] getWorkflowDetailTableInfos() {
        return this.workflowDetailTableInfos;
    }

    public void setWorkflowDetailTableInfos(WorkflowDetailTableInfo[] var1) {
        this.workflowDetailTableInfos = var1;
    }

    public WorkflowRequestLog[] getWorkflowRequestLogs() {
        return this.workflowRequestLogs;
    }

    public void setWorkflowRequestLogs(WorkflowRequestLog[] var1) {
        this.workflowRequestLogs = var1;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String var1) {
        this.messageType = var1;
    }

    public boolean isCanView() {
        return this.canView;
    }

    public void setCanView(boolean var1) {
        this.canView = var1;
    }

    public boolean isCanEdit() {
        return this.canEdit;
    }

    public void setCanEdit(boolean var1) {
        this.canEdit = var1;
    }

    public String getSubmitButtonName() {
        return this.submitButtonName;
    }

    public void setSubmitButtonName(String var1) {
        this.submitButtonName = var1;
    }

    public String getRejectButtonName() {
        return this.rejectButtonName;
    }

    public void setRejectButtonName(String var1) {
        this.rejectButtonName = var1;
    }

    public String getForwardButtonName() {
        return this.forwardButtonName;
    }

    public void setForwardButtonName(String var1) {
        this.forwardButtonName = var1;
    }

    public String getCurrentNodeId() {
        return this.currentNodeId;
    }

    public void setCurrentNodeId(String var1) {
        this.currentNodeId = var1;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String var1) {
        this.status = var1;
    }

    public boolean isMustInputRemark() {
        return this.mustInputRemark;
    }

    public void setMustInputRemark(boolean var1) {
        this.mustInputRemark = var1;
    }

    public String[] getWorkflowHtmlTemplete() {
        return this.WorkflowHtmlTemplete;
    }

    public void setWorkflowHtmlTemplete(String[] var1) {
        this.WorkflowHtmlTemplete = var1;
    }

    public String[] getWorkflowHtmlShow() {
        return this.WorkflowHtmlShow;
    }

    public void setWorkflowHtmlShow(String[] var1) {
        this.WorkflowHtmlShow = var1;
    }

    public String[][] getWorkflowPhrases() {
        return this.workflowPhrases;
    }

    public void setWorkflowPhrases(String[][] var1) {
        this.workflowPhrases = var1;
    }

    public String getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(String var1) {
        this.receiveTime = var1;
    }

    public String getSubnobackButtonName() {
        return this.subnobackButtonName;
    }

    public void setSubnobackButtonName(String var1) {
        this.subnobackButtonName = var1;
    }

    public String getSubbackButtonName() {
        return this.subbackButtonName;
    }

    public void setSubbackButtonName(String var1) {
        this.subbackButtonName = var1;
    }

    public boolean isNeedAffirmance() {
        return this.needAffirmance;
    }

    public void setNeedAffirmance(boolean var1) {
        this.needAffirmance = var1;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public String getIsnextflow() {
        return this.isnextflow;
    }

    public void setIsnextflow(String var1) {
        this.isnextflow = var1;
    }
}
