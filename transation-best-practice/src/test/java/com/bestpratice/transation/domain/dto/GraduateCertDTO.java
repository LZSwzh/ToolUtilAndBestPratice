package com.bestpratice.transation.domain.dto;

import com.bestpratice.transation.domain.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GraduateCertDTO extends BaseDTO {

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
