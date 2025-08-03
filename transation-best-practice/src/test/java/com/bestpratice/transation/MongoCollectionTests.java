package com.bestpratice.transation;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class MongoCollectionTests extends MongoParentTest{
    @Resource
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void printMongoUri() {
        MongoDatabase database = mongoTemplate.getDb();
        String uri = database.getName();
        System.out.println("实际连接的MongoDB地址: " + uri);
    }

    /**
     * 判断集合是否存在
     */
    @Test
    public void testCollectionExists(){
        boolean exists = mongoTemplate.collectionExists("user_cert");
        System.out.println("文档user_cert是否存在:"+exists);
    }

    /**
     * 删除集合
     */
    @Test
    public void testDropCollection(){
        boolean exists = mongoTemplate.collectionExists("user_cert");
        if (exists){
            mongoTemplate.dropCollection("user_cert");
        }
    }

    /**
     * 创建集合及校验器工厂方法
     * - criteria:相对功能有限但是简洁，且利于复用。常用于指定某个规则
     * - schema:功能更强大但是写起来略微繁琐，用法比较接近原生，适合想用OOP的风格的
     * - document:直接用类似原生的操作方式，最灵活但是类型安全啥的不到位
     */
    @Test
    public void testCreateCollection(){
        /*  ****************************有参构造指定是否固定大小**************************** */
//        CollectionOptions collectionOptions = new CollectionOptions(1024l,10000l,true);
        //指定固定大小，指定集合大小以及最大的文档数,等价于上述有参构造
//        collectionOptions.capped().size(1024).maxDocuments(1000);


        /* ****************************使用Validator-Criteria&构建者模式指定校验规则**************************** */

        // 使用Criteria校验规则;合并规则（所有规则必须同时满足）;Criteria不能直接通过type控制创建类型,因为生成的是查询操作符{ "age": { "$type": "int" } },
        // 但是创建集合的时候，需要$jsonSchema,即：validator: { $jsonSchema: {bsonType: "object",required: ["name", "email"],properties: {age: {bsonType: "int",description: "必须为字符串且为必填项"}}}},
        // 因此Criteria这里只能指定规则,不能指定类型
        Criteria ageRule = Criteria.where("age").gte(18).lte(65);
        Criteria nameRule = Criteria.where("name").exists(true).ne("");
        Criteria emailRule = Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Criteria combinedCriteria = new Criteria().andOperator(ageRule, nameRule, emailRule);

        CollectionOptions options = CollectionOptions.empty()
                .capped().size(1024).maxDocuments(1000)
                .validator(Validator.criteria(combinedCriteria))
                .strictValidation()
                .failOnValidationError();
        if (!mongoTemplate.collectionExists("test_user")){
            mongoTemplate.createCollection("test_user", options);
        }

        /* *********************************使用Validator-schema******************************** */

        //定义校验规则，
        // "identityNum", "graduateFile"必填
        //"identityNum"和 "graduateFile"为string类型且长度大于1；"graduateCerts"为array类型且长度大于1
        MongoJsonSchema jsonScheme = MongoJsonSchema.builder()
                .required("identityNum", "graduateFile")
                .properties(
                        JsonSchemaProperty.string("identityNum").minLength(1),
                        JsonSchemaProperty.string("graduateFile").minLength(1),
                        JsonSchemaProperty.array("graduateCerts").minItems(1)
                )
                .build();
        CollectionOptions schemeOptions = CollectionOptions.empty()
                .capped().size(1024).maxDocuments(1000)
                .validator(Validator.schema(jsonScheme))
                .strictValidation()
                .failOnValidationError();
        if (!mongoTemplate.collectionExists("user_cert")){
            mongoTemplate.createCollection("user_cert", schemeOptions);
        }

        /* *********************************使用Validator-document******************************** */
        Document jsonSchema = new Document("bsonType","object")
                .append("required", Arrays.asList("name","age","emial"))
                .append("properties",new Document()
                        .append("name",new Document()
                                .append("bsonType","string")
                                .append("description","name must be a non-empty string")
                        ).append("age",new Document()
                                .append("bsonType","int")
                                .append("minimum",18)
                                .append("description","age must be an integer greater than 18")
                        ).append("email",new Document()
                                .append("bsonType","string")
                                .append("pattern", "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
                                .append("description","email must be a valid email address")
                        )
                );
        CollectionOptions documentOptions = CollectionOptions.empty()
                .capped().size(1024).maxDocuments(1000)
                .validator(Validator.document(new Document("$jsonSchema", jsonSchema)))
                .strictValidation()
                .failOnValidationError();
        if (!mongoTemplate.collectionExists("test_user2")){
            mongoTemplate.createCollection("test_user2", documentOptions);
        }
    }

    /**
     * 重命名集合,springboot-data-mongo没有提供直接重命名的方法
     * 只能自己写原生命令runCommand
     */
    @Test
    public void testRenameCollection(){

        String oldName = "test_user2";
        String newName = "test_user3";
        // 获取数据库
        MongoDatabase db = mongoTemplate.getDb();

        // 获取admin数据库，因为rename操作必须在这个库执行
        MongoDatabase adminDb = mongoTemplate.getMongoDatabaseFactory().getMongoDatabase("admin");

        // 执行 renameCollection 命令
        adminDb.runCommand(new Document("renameCollection", db.getName() + "." + oldName)
                .append("to", db.getName() + "." + newName)
                .append("dropTarget", false)); // 如果目标集合已存在是否删除
    }
}
