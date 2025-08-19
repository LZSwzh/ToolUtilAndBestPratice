package com.best.practice.transaction.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.practice.transaction.domain.entity.GraduateCertEntity;
import com.best.practice.transaction.domain.entity.UserCertEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
        GraduateCertEntity masterGraduateCert = new GraduateCertEntity();
        masterGraduateCert.setGraduateSchool("The Tsinghua University");
        masterGraduateCert.setGraduateMajor("Soft Engineering");
        masterGraduateCert.setGraduateTime(LocalDateTime.of(2025,06,30,12,30,0));
        masterGraduateCert.setGraduateCertNum("TH-FIT00746");
        masterGraduateCert.setGraduateCertFile("https://www.tsinghua.edu.cn/oss/fasdufhaisdu.pdf");
        //构建第二个毕业证书实体
        GraduateCertEntity bachelorGraduateCert = new GraduateCertEntity();
        bachelorGraduateCert.setGraduateSchool("The PeKing University");
        bachelorGraduateCert.setGraduateMajor("Automatic Control Engineering");
        bachelorGraduateCert.setGraduateTime(LocalDateTime.of(2022,06,30,12,30,0));
        bachelorGraduateCert.setGraduateCertNum("PK-FIT00746");
        bachelorGraduateCert.setGraduateCertFile("https://www.pku.edu.cn/oss/asdfad.pdf");
        //构建用户证件实体
        UserCertEntity userCert = new UserCertEntity();
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

    /* ******************************************* 测试查询 *******************************************/
    @Test
    public void testFindDocument() {
        findById();
        findOneWithIs();
        findOneWithLte();
        findByMultiCriteria();
    }

    /**
     * 根据id查询
     */
    private void findById() {
        UserCertEntity resById = mongoTemplate.findById("UC00001", UserCertEntity.class);
        System.out.println("根据id查询用户证件JSON:\n" + JSON.toJSONString(resById, SerializerFeature.PrettyFormat));
    }

    /**
     * findOne+is查找
     */
    private void findOneWithIs() {
        UserCertEntity resByIs = mongoTemplate.findOne(new Query(Criteria.where("identityNum").is("43092830000910933X")), UserCertEntity.class);
        System.out.println("根据==身份证号查证件JSON:\n" + JSON.toJSONString(resByIs, SerializerFeature.PrettyFormat));
    }

    /**
     * findOne+lte查找
     */
    private void findOneWithLte() {
        UserCertEntity resBylte = mongoTemplate.findOne(new Query(Criteria.where("userId").lte("000001")), UserCertEntity.class);
        System.out.println("根据<=00001查询证件JSON:\n" + JSON.toJSONString(resBylte, SerializerFeature.PrettyFormat));
    }

    /**
     * 多条件查询
     */
    private void findByMultiCriteria() {
        Criteria mutiCriteria = new Criteria().orOperator(
                new Criteria().andOperator(
                        Criteria.where("_id").is("UC00001"),
                        Criteria.where("userId").is("000001")
                ),
                Criteria.where("identityNum").is("43092830000910933X")
        );
        List<UserCertEntity> mutiQueryRes = mongoTemplate.find(new Query(mutiCriteria), UserCertEntity.class);
        mutiQueryRes.stream().map(JSON::toJSONString).forEach(System.out::println);
    }

    @Test
    public void testUpdateDocument() {
        updateBySave();
        updateByUpdateFirst();
//        updateByUpdateMulti();
    }

    /**
     * 根据Save更新,这是全量更新，不存在的字段置空或者默认值
     */
    @Test
    public void updateBySave() {
        //save方法，保存时如果_id存在执行的是更新的逻辑；这个是全量更新,不存在的字段置空或者默认值
        UserCertEntity existCert = mongoTemplate.findOne(new Query(Criteria.where("_id").is("UC00001")), UserCertEntity.class);
        if (existCert != null) {
            existCert.setIdentityNum("43092830000910933X-0");
            mongoTemplate.save(existCert);
        }
        UserCertEntity result = mongoTemplate.findOne(new Query(Criteria.where("_id").is("UC00001")), UserCertEntity.class);
        System.out.println("更新后的结果是："+JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    /**
     * 根据updateFirst更新单条,这是局部更新
     */
    @Test
    public void updateByUpdateFirst() {
        Query query = new Query(Criteria.where("_id").is("UC00001"));
        //声明更新的字段
        Update update = new Update().set("identityNum", "43092830000910933X");
        //更新单条记录的特定字段
        mongoTemplate.updateFirst(query,update, UserCertEntity.class);

        UserCertEntity result = mongoTemplate.findOne(new Query(Criteria.where("_id").is("UC00001")), UserCertEntity.class);
        System.out.println("更新后的结果是："+JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }

    /**
     * 根据updateMulti更新多条,这是局部更新
     * 这样貌似无法做到类似这种SQL:UPDATE tb_aaa SET score=score+1 WHERE aaa_id=xxx这种效果
     * 可以用.inc实现自增
     */
    @Test
    public void updateByUpdateMulti() {
        Query query = new Query(Criteria.where("_id").in("UC00001","UC00002"));
        List<UserCertEntity> queryList = mongoTemplate.find(query, UserCertEntity.class);
        System.out.println("更新前：");
        queryList.stream().map(UserCertEntity::getIdentityNum).forEach(System.out::println);
        Update update = new Update().set("identityNum", "84309283334910231");

        // 执行批量更新
        mongoTemplate.updateMulti(query, update, UserCertEntity.class);

        List<UserCertEntity> queryList2 = mongoTemplate.find(query, UserCertEntity.class);
        System.out.println("更新后：");
        queryList2.stream().map(UserCertEntity::getIdentityNum).forEach(System.out::println);
    }


    
}
