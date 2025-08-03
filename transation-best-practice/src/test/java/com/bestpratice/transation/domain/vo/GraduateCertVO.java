package com.bestpratice.transation.domain.vo;


import com.bestpratice.transation.domain.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 这里的GraduteCert作为UserCert的一个子数组存在，所以不需要@Document注解
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GraduateCertVO extends BaseVO {
    /** <p>毕业学校</p> */
    private String graduateSchool;

    /** <p>毕业专业</p> */
    private String graduateMajor;

    /** <p>毕业时间</p> */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String graduateTime;

    /** <p>毕业证书编号</p> */
    private String graduateCertNum;

    /** <p>毕业证书,这里存放URL</p> */
    private String graduateCertFile;
}
