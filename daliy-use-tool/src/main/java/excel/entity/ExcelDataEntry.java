package excel.entity;


import lombok.Data;
import lombok.ToString;

import java.io.File;

/**
 * 单元格内容的实体类，由于业务需求有一个dataType属性用来存放数据的类型
 */
@ToString
public class ExcelDataEntry {

    /** <p>行索引</p> */
    private Integer row;

    /** <p>列索引</p> */
    private Integer col;

    /** <p>单元格数据</p> */
    private Object data;

    /** <p>行索引</p> */
    private String dataType;


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        if (data instanceof String){
            setDataType("String");
        }else if (data instanceof Double){
            setDataType("Double");
        }else if (data instanceof Integer){
            setDataType("Integer");
        }else if (data instanceof File){
            setDataType("File");
        }else if (data instanceof Boolean){
            setDataType("Boolean");
        }else {
            setDataType("String");
        }
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

}