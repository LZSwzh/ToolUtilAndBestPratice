package com.best.practice.aop.fieldconvert.pojo;

import com.best.practice.aop.fieldconvert.anno.OAFields;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@Data
@OAFields(values = {"fileName,htmc","needWatermark,sfyy"})
public class Document  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**<p>合同文本ID</p>*/
    private Integer documentId;

    /**<p>合同ID</p>*/
    private Integer contractId;

    /**<p>文件类型</p>*/
    private String fileType;

    /**<p>文件名称</p>*/
    private String fileName;

    /**<p>是否用印</p>*/
    private String needWatermark;

    /**<p>模版附件</p>*/
    private Integer templateFileId;



    /**<p>模板附件ID</p>*/
    private Integer templateAttaId;

    /**<p>模版附件备注</p>*/
    private String templateAttaRemark;

    /**<p>留痕文件</p>*/
    private Integer revisionFileId;

    /**<p>合同文本</p>*/
    private Integer contractFileId;

    /**<p>流程文档id</p>*/
    private String flowFileId;

    /**<p>原合同文本ID</p>*/
    private Integer originalDocumentId;

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

    /**<p>行样式名称</p>*/
    private String rowClassName;

    /**<p>单元格样式名称</p>*/
    private Map<String,Object> cellClassName;

    /**<p>模版附件原文件名</p>*/
    private String templateFileName;

    /**<p>留痕文件原文件名</p>*/
    private String revisionFileName;

    /**<p>合同文本原文件名</p>*/
    private String contractFileName;



}
