package excel.poi;

/**
 * 存放一些常量
 */
public interface Constants {
    /**
     * 发起请求所需要的相关数据
     */
    String clientId =  "a9e19da1-ade2-4fb4-a907-363c289a338f";
    String privateKey = "{\n" +
            "        \"d\": \"DCdFycXZuwSaaK5GueHXJS1PVEWzF_LnC8sYk3i1u5e9GdbvfwW3Trcypzj5xzEOhMidCtEqOIdInNPKQGeza18J9GupGRaJrbU9n4OPtxSL7AllgD9n0XQMw_hQyU91-8fqoYSR-h8ZdwmejxZj4JmVHAX8oBmSrBxUmbnjHd1htrBVm-NDuPndUHPeftsIDq9yKMfNt5OIMVaQ9r7Ka70aox24mS1zrf-Uf--ZxHhNtbOeHI2qe2JXLpGC_hkR98wvnyps6Phg_cwinV-6foUs83dOZ-qoPG6Yeh1f5IlJWB51goc_7diWNqxDSsZ1s27DdfmMfT8f7_dioCGdgQ\",\"e\": \"AQAB\",\"kid\": \"35893e52-c336-4963-b5d4-526c6269640c\",\n" +
            "        \"use\": \"sig\",\n" +
            "        \"dp\": \"RkPQVvWEQG3RVmCz-n8PDiM6FuIY4NfqDOd_pIz8UyFbwDeXLu0eoMzRGfvrFZdhwMa8mR3FqcXqQQvTvGKU8r8gEmBK6ykL1H3w5iNne1Sraik-Id-lNV6GZd9XhfmRV1YwO_pNiYeuGFMRhuz1bSjJmJfpgbRgN2S2iOY_0WE\",\n" +
            "        \"dq\": \"fKVxEABqJxDopMzwP1d0ZZyNkM9fUfhGYZWrPN_ubO2OWo6slhO9gezEI9R51oXxtNq0CZzIH-IVDb5_hXj_syDcjIoUXkVWjX06IqmYuFcqUmU3770fT9iyOgBGFy3oNt0Sje_qqOLORgJGwomr9Cq32525seLUcMtatL-H2YE\",\n" +
            "        \"n\": \"h6qe2hecntIlpIxhKC8jJU0oZyAFepI0WvbnIHQ2x68Z1sCG6M_U8BtEyDbPedKjmIh5Mdk80H5Tz-Dp5rUfvzlltHX4y1qZaP7LJBy-wKeSJJkHyiUQ_90J_Hi6sloOLpCb_bKfy7jR6pgS3ik0UxYB5sHkcioRbYikkIaDEDRuKI1PjRxbekA9rlrB6VVX7suC7Cxs-ytDUsg1SsjwcgZJhQK9NsRagZYBJoui1FSzgDBT9KQ2xWFHLhThmUDBwlb3NSqz7io0zqoMcgVPPGhXZcF0km4co1sBItXze_DA0yOHQzYD6molIYOYrTk9LqtFAzv1-1yKKk-1vqBGOQ\",\n" +
            "        \"kty\": \"RSA\",\n" +
            "        \"p\": \"-eOHrddeffHpDaR1EeEgPu8qid2n6R39u6xg2uO6blM5iMxejjOk1xgyxasNTTe5p1Il5SNhLnLbz6EoLac4soouJFil-Q22D5Vik_sTtXxmAYJr2C7GcjAQEIcasNTQgJOxWKpkL6NrTRakSRsfM-5P7OfbdX7_qdPZvH8Jm6k\",\n" +
            "        \"q\": \"ivv7oiLjbO-oJd104syfBBS2FYU1s2A9t5Oe722QfCrEoEUpc0z8gWLhGIhwhlutDFqiLvk2hD5AzzLlON4wWMkMoxl5m40-jrz1TWe1_9wxGip8NMatXw4NHPdk1itpxdVSdFIPgldH7AB5R1Pu2OYpxVjFbN9ywfERLbmrcBE\",\n" +
            "        \"qi\": \"IDz_DtpS-9YFgRlcArqz4OvmCeSRRcksDxkr6shQ8b02SIv0KSVPkGsloqWQtIbbtT4kdBlRgMLEsCvjl-pvFFBmzeZkA00D933JLsxQCsfp1uPFduLXaOXRt_FsSTZAFkaYSptqUx197gLMgA3yYZUL4q2imvpnNAl-NZC6-0g\"\n" +
            "    }";
    String keyId = "35893e52-c336-4963-b5d4-526c6269640c";
    String audience = "serviceAccount";

    String account_type = "serviceAccount";

    /**
     * 这里使用EHR标准架构下的数据
     */
    String ehr_dept_id = "dp-aec83ac917b946efb3d1e69fa86dbe76";
    //IamDept(id=dp-aec83ac917b946efb3d1e69fa86dbe76, tenantId=tn-b41765d68cb14d028143bea7e1df51a8, modifiedOn=1720142131774, createdOn=1720142131774, objectType=DEPT, values=IamDeptValue(name=EHR标准组织架构, parentId=dp-b41765d68cb14d028143bea7e1df51a8, status=ENABLED, ancestor=[dp-b41765d68cb14d028143bea7e1df51a8]), pinyinForName=EHRbiaozhunzuzhijiagou)
    /**
     * 使用企业微信架构下的数据
     */
    String en_wechat_id = "dp-e5b419daa6394d16910cbcad73aea532";
    //IamDept(id=dp-e5b419daa6394d16910cbcad73aea532, tenantId=tn-b41765d68cb14d028143bea7e1df51a8, modifiedOn=1722261197383, createdOn=1722261197383, objectType=DEPT, values=IamDeptValue(name=企业微信组织架构, parentId=dp-b41765d68cb14d028143bea7e1df51a8, status=ENABLED, ancestor=[dp-b41765d68cb14d028143bea7e1df51a8]), pinyinForName=qiyeweixinzuzhijiagou)
}
