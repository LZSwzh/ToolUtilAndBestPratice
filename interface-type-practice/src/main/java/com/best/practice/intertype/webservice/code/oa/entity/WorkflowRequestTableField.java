package com.best.practice.intertype.webservice.code.oa.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class WorkflowRequestTableField implements Serializable {
    private static final long serialVersionUID = -2865596549306206630L;
    private String fieldId;
    private String fieldName;
    private String fieldValue;
    private String fieldHtmlType;
    private String fieldType;
    private String fieldDBType;
    private String fieldFormName;
    private int fieldOrder;
    private boolean isView;
    private boolean isEdit;
    private boolean isMand;
    private String fieldShowName;
    private String browserurl;
    private String[] selectnames;
    private String[] selectvalues;
    private String fieldShowValue;
    private String filedHtmlShow;

    public WorkflowRequestTableField() {
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String var1) {
        this.fieldName = var1;
    }

    public String getFieldValue() {
        return this.fieldValue;
    }

    public void setFieldValue(String var1) {
        this.fieldValue = var1;
    }

    public String getFieldHtmlType() {
        return this.fieldHtmlType;
    }

    public void setFieldHtmlType(String var1) {
        this.fieldHtmlType = var1;
    }

    public String getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(String var1) {
        this.fieldType = var1;
    }

    public String getFieldDBType() {
        return this.fieldDBType;
    }

    public void setFieldDBType(String var1) {
        this.fieldDBType = var1;
    }

    public String getFieldFormName() {
        return this.fieldFormName;
    }

    public void setFieldFormName(String var1) {
        this.fieldFormName = var1;
    }

    public int getFieldOrder() {
        return this.fieldOrder;
    }

    public void setFieldOrder(int var1) {
        this.fieldOrder = var1;
    }

    public boolean isView() {
        return this.isView;
    }

    public void setView(boolean var1) {
        this.isView = var1;
    }

    public boolean isEdit() {
        return this.isEdit;
    }

    public void setEdit(boolean var1) {
        this.isEdit = var1;
    }

    public boolean isMand() {
        return this.isMand;
    }

    public void setMand(boolean var1) {
        this.isMand = var1;
    }

    public String getFieldShowName() {
        return this.fieldShowName;
    }

    public void setFieldShowName(String var1) {
        this.fieldShowName = var1;
    }

    public String getFieldShowValue() {
        return this.fieldShowValue;
    }

    public void setFieldShowValue(String var1) {
        this.fieldShowValue = var1;
    }

    public String getBrowserurl() {
        return this.browserurl;
    }

    public void setBrowserurl(String var1) {
        this.browserurl = var1;
    }

    public String[] getSelectnames() {
        return this.selectnames;
    }

    public void setSelectnames(String[] var1) {
        this.selectnames = var1;
    }

    public String[] getSelectvalues() {
        return this.selectvalues;
    }

    public void setSelectvalues(String[] var1) {
        this.selectvalues = var1;
    }

    public String getFiledHtmlShow() {
        return this.filedHtmlShow;
    }

    public void setFiledHtmlShow(String var1) {
        this.filedHtmlShow = var1;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String var1) {
        this.fieldId = var1;
    }
}
