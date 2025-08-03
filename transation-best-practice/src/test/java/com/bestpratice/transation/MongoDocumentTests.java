package com.bestpratice.transation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bestpratice.transation.domain.entity.GraduateCertDO;
import com.bestpratice.transation.domain.entity.UserCertDO;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MongoDocumentTests extends MongoParentTest{
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert() {
        //构建一个毕业证书实体
        GraduateCertDO masterGraduateCert = new GraduateCertDO();
        masterGraduateCert.setGraduateSchool("The Tsinghua University");
        masterGraduateCert.setGraduateMajor("Soft Engineering");
        masterGraduateCert.setGraduateTime(LocalDateTime.of(2025,06,30,12,30,0));
        masterGraduateCert.setGraduateCertNum("TH-FIT00746");
        masterGraduateCert.setGraduateCertFile("https://www.tsinghua.edu.cn/oss/fasdufhaisdu.pdf");
        //构建第二个毕业证书实体
        GraduateCertDO bachelorGraduateCert = new GraduateCertDO();
        bachelorGraduateCert.setGraduateSchool("The PeKing University");
        bachelorGraduateCert.setGraduateMajor("Automatic Control Engineering");
        bachelorGraduateCert.setGraduateTime(LocalDateTime.of(2022,06,30,12,30,0));
        bachelorGraduateCert.setGraduateCertNum("PK-FIT00746");
        bachelorGraduateCert.setGraduateCertFile("https://www.pku.edu.cn/oss/asdfad.pdf");
        //构建用户证件实体
        UserCertDO userCert = new UserCertDO();
        userCert.setId("UC00001");
        userCert.setUserId("000001");
        userCert.setIdentityNum("43092830000910933X");
        userCert.setIdentityFile("https://www.gongan.cn/oss/fasdfasd.pdf");
        userCert.setGraduateCerts(Arrays.asList(masterGraduateCert,bachelorGraduateCert));
        //save方法，保存时如果_id存在执行的是更新的逻辑
//        mongoTemplate.save(userCert);
        //insert如果_id存在会抛异常,本方法还支持批量插入
        mongoTemplate.insert(userCert);
//        mongoTemplate.insert(Arrays.asList(userCert), UserCertDO.class);
    }

    @Test
    public void testFindDocument() {
        UserCertDO resById = mongoTemplate.findById("UC00001", UserCertDO.class);
        System.out.println("根据id查询用户证件JSON:\n"+ JSON.toJSONString(resById, SerializerFeature.PrettyFormat));

        UserCertDO resByIs = mongoTemplate.findOne(new Query(Criteria.where("identityNum").is("43092830000910933X")), UserCertDO.class);
        System.out.println("根据==身份证号查证件JSON:\n"+ JSON.toJSONString(resByIs, SerializerFeature.PrettyFormat));


        UserCertDO resBylte = mongoTemplate.findOne(new Query(Criteria.where("userId").lte("000001")), UserCertDO.class);
        System.out.println("根据<=00001查询证件JSON:\n"+ JSON.toJSONString(resBylte, SerializerFeature.PrettyFormat));

        //多条件查询，内部的_id和userId是and的关系，他们和identityNum是or关系
        Criteria mutiCriteria = new Criteria().orOperator(
                new Criteria().andOperator(
                        Criteria.where("_id").is("UC00001"),
                        Criteria.where("userId").is("000001")
                ),
                Criteria.where("identityNum").is("43092830000910933X")
        );
        List<UserCertDO> mutiQueryRes = mongoTemplate.find(new Query(mutiCriteria), UserCertDO.class);
        mutiQueryRes.stream().map(JSON::toJSONString).forEach(System.out::println);
    }

    @Test
    public void testUpdateDocument() {

//        mongoTemplate.updateFirst();
    }
    
}
