package com.bestpratice.transation.domain.entity;

import com.bestpratice.transation.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "user_cert")
@EqualsAndHashCode(callSuper = false)
public class UserCertDO extends BaseDO {
    /** <p>主键ID</p> */
    @Id
    private String id;

    /** <p>用户ID</p> */
    @Indexed(unique = false)
    private String userId;

    /** <p>身份证号,一一映射关系</p> */
    @Indexed(unique = true)
    private String identityNum;

    /** <p>身份证照片,1:1关系</p> */
    private String identityFile;

    /** <p>毕业证书列表,1:n关系</p> */
    private List<GraduateCertDO> graduateCerts;

}
