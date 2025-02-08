package com.best.practice.aop.fieldconvert.util;

import com.best.practice.aop.fieldconvert.anno.OAChild;
import com.best.practice.aop.fieldconvert.anno.OAFields;
import com.best.practice.aop.fieldconvert.pojo.Contract;
import com.best.practice.aop.fieldconvert.pojo.Document;
import com.best.practice.aop.fieldconvert.pojo.Payment;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangzh
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        /****************************** 构造数据 *********************************/
        Contract contract = new Contract();
        contract.setContractNum("1111111");
        contract.setContractName("合同1");

        Payment payment = new Payment();
        payment.setPaymentStageDesc("这是一个描述");
        contract.setPaymentList(Arrays.asList(payment));

        Document document = new Document();
        document.setContractFileName("这是文本名称");
        document.setNeedWatermark("是");
        contract.setDocumentList(Arrays.asList(document));
        /****************************** 反射操作,将注解标记得属性反射为OA需要得属性 *********************************/



        Class<Contract> contractClazz = Contract.class;




    }
    static HashMap<String, Object> mainTableInfo = new HashMap<>();
    static List<HashMap<String,Object>> defaultTableInfos = new LinkedList<>();

    public static void tttt(Class clazz) throws ClassNotFoundException {

        OAFields oaFields = (OAFields) clazz.getAnnotation(OAFields.class);

        //获取映射,key:字段名,val对应OA需要得key
        Map<String, String> map = Arrays.stream(oaFields.values())
                .map(item -> item.split(oaFields.separator()))
                .collect(Collectors.toMap(item -> item[0], item -> item[1]));

        for(Field field:clazz.getFields()){
            String fieldName = field.getName();
            String oaKey = map.getOrDefault(fieldName, null);

            //如果是子表
            OAChild childAnno = field.getAnnotation(OAChild.class);
            Class<?> childClazz = Class.forName(childAnno.value());

            //如果是主表字段
            if (StringUtils.isEmpty(oaKey)) continue;
            field.setAccessible(true);
//            field.get()
//            mainTableInfo.put(oaKey,)
        }
    }

}
