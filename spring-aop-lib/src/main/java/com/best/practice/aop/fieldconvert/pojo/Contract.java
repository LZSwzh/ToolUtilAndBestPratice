package com.best.practice.aop.fieldconvert.pojo;

import com.best.practice.aop.fieldconvert.anno.OAChild;
import com.best.practice.aop.fieldconvert.anno.OAFields;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/** key为字段,val为键值对 */
@Data
@OAFields(values = {"contractNum,htbh", "contractName,htbh"})
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * <p>合同ID</p>
     *
     */
    private Integer contractId;

    /**
     * <p>合同编号</p>
     */
    private String contractNum;

    /**<p>提交的时候生成，取合同编号组合字段+1</p>*/
    private String contractSubmitNum;

    /**<p>合同编号组合字段</p>*/
    private String contractNumSplit;

    /**
     * <p>合同名称</p>
     */
    private String contractName;

    /**
     * <p>甲方公司</p>
     */
    private Integer ouId;

    /**
     * <p>采购组织</p>
     */
    private Integer purOrgId;

    /**
     * <p>采购组织编码</p>
     */
    private String purOrgCode;

    /**
     * <p>采购组织名称</p>
     */
    private String purOrgName;

    /**
     * <p>经办部门</p>
     */
    private Integer departmentId;

    /**
     * <p>经办部门编码</p>
     */
    private String departmentCode;
    /**
     * <p>经办部门名称</p>
     */
    private String departmentName;




    /**
     * <p>需求部门ID</p>
     */
    private Integer requireDeptId;

    /**
     * <p>需求部门编码</p>
     */
    private String requireDeptCode;

    /**
     * <p>需求部门名称</p>
     */
    private String requireDeptName;

    /**
     * <p>预合同</p>
     */
    private String bfhContractFlag;

    /**
     * <p>主合同</p>
     */
    private String masterContractFlag;

    /**
     * <p>主合同名称</p>
     */
    private String masterContractName;

    /**
     * <p>以合同付款</p>
     */
    private String payToContractFlag;

    /**
     * <p>合同版本</p>
     */
    private Integer contractVersion;

    /**
     * <p>合同状态</p>
     */
    private String contractStatus;

    /**
     * <p>合同审批状态</p>
     */
    private String approvalStatus;

    /**
     * <p>合同标准分类</p>
     */
    private String contractCategory;

    /**
     * <p>合同类别</p>
     */
    private String contractType;

    /**
     * <p>供应商</p>
     */
    private Integer supplierId;

    /**
     * <p>供应商名称</p>
     */
    private String supplierName;

    /**
     * <p>供应商编码</p>
     */
    private String supplierCode;

    /**<p>代办人ID</p>*/
    private Integer proxyId;

    /**<p>代办人姓名</p>*/
    private String proxyName;

    /**<p>责任人ID</p>*/
    private String liabilityId;

    /**<p>责任人名称</p>*/
    private String liabilityName;

    /**<p>责任人名称</p>*/
    private String liabilityCode;

    /**<p>是否需代理</p>*/
    private String needProxy;

    /**<p>是否需供应商确认</p>*/
    private String needConfirmFlag;

    /**
     * <p>付款条件</p>
     */
    private Integer paymentTermId;

    /**
     * <p>来源类型</p>
     */
    private String sourceCode;

    /**
     * <p>来源单号</p>
     */
    private String sourceId;

    /**
     * <p>来源单号</p>
     */
    private String sourceNum;

    /**
     * <p>币种</p>
     */
    private String currencyCode;

    /**
     * <p>是否合同模板</p>
     */
    private String templateFlag;

    /**
     * <p>合同模版</p>
     */
    private Integer templateId;

    /**
     * <p>主合同ID</p>
     */
    private Integer masterContractId;

    /**
     * <p>主合同编号</p>
     */
    private String masterContractNum;

    /**
     * <p>本次合同金额</p>
     */
    private BigDecimal currentAmount;

    /**
     * <p>本次合同不含税金额</p>
     */
    private BigDecimal currentUnAmount;

    /**
     * <p>本次合同税额</p>
     */
    private BigDecimal currentTaxAmount;

    /**
     * <p>累计合同金额</p>
     */
    private BigDecimal totalAmount;

    /**
     * <p>已付金额</p>
     */
    private BigDecimal paidAmount;

    /**
     * <p>公司签字日期</p>
     */
    private LocalDate ouSignDate;

    /**
     * <p>乙方签字日期</p>
     */
    private LocalDate supplierSignDate;

    /**
     * <p>生效日期</p>
     */
    private LocalDate startDate;

    /**
     * <p>失效日期</p>
     */
    private LocalDate endDate;

    /**
     * <p>项目ID</p>
     */
    private Integer projectId;

    /**
     * <p>项目编码</p>
     */
    private String projectNum;

    /**
     * <p>项目名称</p>
     */
    private String projectName;

    /**
     * <p>备注</p>
     */
    private String remark;

    /**<p>终止审批状态</p>*/
    private String terminationApprovalStatus;

    /**
     * <p>终止日期</p>
     */
    private LocalDate terminationDate;

    /**
     * <p>终止原因</p>
     */
    private String terminationReason;

    /**<p>签章流程id</p>*/
    private String signFlowId;

    /**<p>原合同ID</p>*/
    private Integer originalContractId;

    /**
     * <p>扩展分类</p>
     */
    private String attributeCategory;

    /**
     * <p>扩展字段1</p>
     */
    private String attribute1;

    /**
     * <p>扩展字段2</p>
     */
    private String attribute2;

    /**
     * <p>扩展字段3</p>
     */
    private String attribute3;

    /**
     * <p>扩展字段4</p>
     */
    private String attribute4;

    /**
     * <p>扩展字段5</p>
     */
    private String attribute5;

    /**
     * <p>OA流程审批单ID</p>
     */
    private String oaFlowId;

    /**
     * <p>OA流程审批单号</p>
     */
    private String oaFlowCode;

    /**
     * <p>OA流程审批备注</p>
     */
    private String oaFlowApprovalRemark;

    /**
     * <p>预算使用类型</p>
     */
    private String budgetType;

    /**
     * <p>公司</p>
     */
    private String ouName;
    /**
     * <p>公司编码</p>
     */
    private String ouCode;

    /**
     * <p>模版名称</p>
     */
    private String templateName;

    /**
     * <p>付款条件名称</p>
     */
    private String paymentTermName;

    /**
     * <p>合同归档附件</p>
     */
    private String archiveFileIds;

    /**
     * <p>采购组织ID</p>
     */
    private Integer puId;

    /**
     * <p>采购订单类型ID</p>
     */
    private Integer billTypeId;

    /**
     * <p>快速下单</p>
     */
    private String quickOrderFlag;


    private String cctTeamworkType;

    /**
     * <p>合同类别说明</p>
     */
    private String contractTypeDesc;

    /**
     * <p>币种名称</p>
     */
    private String currencyName;

    /**<p>是否支持电子签章</p>*/
    private String electronicSignatureFlag;

    /**<p>结算方式</p>*/
    private String paymentMethod;

    /**<p>合同类型</p>*/
    private String contractKind;

    /**<p>用章模式 快码:CCT_CONTRACT_USE_SEAL_MODE</p>*/
    private String useSealMode;

    /**<p>合同等级 快码:CCT_CONTRACT_LEVEL</p>*/
    private String contractLevel;

    /**<p>用章类型 快码:CCT_CONTRACT_USE_SEAL_TYPE</p>*/
    private String useSealType;

    /**<p>发票类型 快码:RCN_INVOICE_TYPE</p>*/
    private String invoiceType;

    /**<p>合同简介</p>*/
    private String introduction;

    /**<p>支付进度(快码:CONTRACT_PAYMENT_PROGRESS)</p>*/
    private String paymentProgress;

    /**<p>其他付款条件</p>*/
    private String otherPaymentTerm;

    /**<p>违约条款</p>*/
    private String breakContractClause;

    /**<p>质保条款</p>*/
    private String warrantyTerm;

    /**<p>争议解决方式</p>*/
    private String disputeSolution;

    /**<p>月结周期(快码:CCT_MONTHLY_SETTLEMENT_CYCLE)</p>*/
    private String monthlySettlementCycle;

    /**<p>支付方式(快码:CCT_PAYMENT_METHOD)</p>*/
    private String cctPaymentMethod;

    /**<p>其他附件</p>*/
    private String otherFileIds;

    /**
     * <p>供应商地址</p>
     */
    private String detailedAddress;

    /**<p>银行账号</p>*/
    private String accountNum;

    /**<p>账户名称</p>*/
    private String accountName;

    /**<p>联系电话</p>*/
    private String mobilePhone;

    /**<p>预警接收邮箱</p>*/
    private String email;

    /**<p>法人代表</p>*/
    private String legalPerson;

    /**<p>传真号码</p>*/
    private String faxNumber;

    @OAChild(value = "com.best.practice.aop.fieldconvert.pojo.Payment",order = 1)
    private List<Payment> paymentList;
    @OAChild(value = "com.best.practice.aop.fieldconvert.pojo.Document",order = 2)
    private List<Document> documentList;

}
