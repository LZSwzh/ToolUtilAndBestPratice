package com.best.practice.aop.fieldconvert.pojo;

import com.best.practice.aop.fieldconvert.anno.OAFields;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@OAFields(values = {"paymentStageDesc,desc"})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**<p>合同付款阶段ID</p>*/
    private Integer paymentStageId;

    /**<p>合同ID</p>*/
    private Integer contractId;

    /**<p>付款阶段名称</p>*/
    private String paymentStageName;

    /**<p>付款阶段说明</p>*/
    private String paymentStageDesc;

    /**<p>付款比例</p>*/
    private Integer paymentRatio;

    /**<p>预计付款时间</p>*/
    private LocalDate planPayDate;

    /**<p>预警提前期(天)</p>*/
    private Integer warnLeadDay;

    /**<p>应付金额</p>*/
    private BigDecimal dueAmount;

    /**<p>实付金额</p>*/
    private BigDecimal payAmount;

    /**<p>付款完成</p>*/
    private String paidFlag;

    /**<p>付款时间</p>*/
    private String paymentDate;

    /**<p>付款状态</p>*/
    private String paymentStatus;

    /**<p>付款条件节点ID</p>*/
    private Integer paymentTermNode;

    /**<p>原付款阶段ID</p>*/
    private Integer originalPaymentStageId;

    /**<p>扩展分类</p>*/
    private String attributeCategory;

    /**<p>扩展字段1</p>*/
    private String attribute1;

    /**<p>扩展字段2</p>*/
    private String attribute2;

    /**<p>扩展字段3</p>*/
    private String attribute3;

    /**<p>扩展字段4</p>*/
    private String attribute4;

    /**<p>扩展字段5</p>*/
    private String attribute5;

    private Map<String,Object> cellClassName;
    private String rowClassName;

}
