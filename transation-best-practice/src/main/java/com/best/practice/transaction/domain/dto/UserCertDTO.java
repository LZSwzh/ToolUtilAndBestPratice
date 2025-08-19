package com.best.practice.transaction.domain.dto;

import com.best.practice.transaction.domain.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserCertDTO extends BaseDTO {

    /** <p>主键ID</p> */
    private String id;

    /** <p>用户ID</p> */
    private String userId;

    /** <p>身份证号,一一映射关系</p> */
    private String identityNum;

    /** <p>身份证照片,1:1关系</p> */
    private String identityFile;

    /** <p>毕业证书列表,1:n关系</p> */
    private List<GraduateCertDTO> graduateCerts;
}
