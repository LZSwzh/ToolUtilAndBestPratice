package com.best.pratice.httpclient.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
//    public static void main(String[] args) {
//        String json = "[{\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2355,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 11.47,\n" +
//                "                \"freightUntaxedPrice\": 10.1500,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 11.47,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"197010003\",\n" +
//                "                \"itemId\": 41401,\n" +
//                "                \"itemName\": \"百兆以太网控制器_RTL8304MBI-VB-CGRSB\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 10,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23399,\n" +
//                "                \"quoteOrderLineNumber\": 23399,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12859,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 10.1500,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_41401\",\n" +
//                "        \"itemCode\": \"197010003\",\n" +
//                "        \"itemId\": 41401,\n" +
//                "        \"itemName\": \"百兆以太网控制器_RTL8304MBI-VB-CGRSB\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 32.11,\n" +
//                "                \"freightUntaxedPrice\": 28.4200,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 32.11,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180039\",\n" +
//                "                \"itemId\": 37862,\n" +
//                "                \"itemName\": \"千兆以太网收发器_PF025-VD-CGT\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23391,\n" +
//                "                \"quoteOrderLineNumber\": 23391,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12872,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 28.4200,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37862\",\n" +
//                "        \"itemCode\": \"111180039\",\n" +
//                "        \"itemId\": 37862,\n" +
//                "        \"itemName\": \"千兆以太网收发器_PF025-VD-CGT\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 140,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 449.60,\n" +
//                "                \"freightUntaxedPrice\": 397.8800,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 449.60,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110017\",\n" +
//                "                \"itemId\": 37664,\n" +
//                "                \"itemName\": \"MCU_STM32F429BIT6\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23386,\n" +
//                "                \"quoteOrderLineNumber\": 23386,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12867,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 397.8800,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37664\",\n" +
//                "        \"itemCode\": \"111110017\",\n" +
//                "        \"itemId\": 37664,\n" +
//                "        \"itemName\": \"MCU_STM32F429BIT6\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 32.11,\n" +
//                "                \"freightUntaxedPrice\": 28.4200,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 32.11,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180017\",\n" +
//                "                \"itemId\": 37841,\n" +
//                "                \"itemName\": \"千兆以太网收发器_PF025-VC-CGT\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23390,\n" +
//                "                \"quoteOrderLineNumber\": 23390,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12871,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 28.4200,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37841\",\n" +
//                "        \"itemCode\": \"111180017\",\n" +
//                "        \"itemId\": 37841,\n" +
//                "        \"itemName\": \"千兆以太网收发器_PF025-VC-CGT\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 29.82,\n" +
//                "                \"freightUntaxedPrice\": 26.3900,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 29.82,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180059\",\n" +
//                "                \"itemId\": 37882,\n" +
//                "                \"itemName\": \"8通道千兆以太网路由器_RTL8370N-VB-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 10,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23402,\n" +
//                "                \"quoteOrderLineNumber\": 23402,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12862,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 26.3900,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37882\",\n" +
//                "        \"itemCode\": \"111180059\",\n" +
//                "        \"itemId\": 37882,\n" +
//                "        \"itemName\": \"8通道千兆以太网路由器_RTL8370N-VB-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 140,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 441.58,\n" +
//                "                \"freightUntaxedPrice\": 390.7800,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 441.58,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110015\",\n" +
//                "                \"itemId\": 37662,\n" +
//                "                \"itemName\": \"MCU_STM32F207ZET6\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23385,\n" +
//                "                \"quoteOrderLineNumber\": 23385,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12866,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 390.7800,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37662\",\n" +
//                "        \"itemCode\": \"111110015\",\n" +
//                "        \"itemId\": 37662,\n" +
//                "        \"itemName\": \"MCU_STM32F207ZET6\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 27.53,\n" +
//                "                \"freightUntaxedPrice\": 24.3600,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 27.53,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180026\",\n" +
//                "                \"itemId\": 37850,\n" +
//                "                \"itemName\": \"千兆以太网收发器_RTL8218D-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 10,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23396,\n" +
//                "                \"quoteOrderLineNumber\": 23396,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12877,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 24.3600,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37850\",\n" +
//                "        \"itemCode\": \"111180026\",\n" +
//                "        \"itemId\": 37850,\n" +
//                "        \"itemName\": \"千兆以太网收发器_RTL8218D-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 140,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 437.91,\n" +
//                "                \"freightUntaxedPrice\": 387.5300,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 437.91,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110016\",\n" +
//                "                \"itemId\": 37663,\n" +
//                "                \"itemName\": \"MCU_STM32F207VET6\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23384,\n" +
//                "                \"quoteOrderLineNumber\": 23384,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12865,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 387.5300,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37663\",\n" +
//                "        \"itemCode\": \"111110016\",\n" +
//                "        \"itemId\": 37663,\n" +
//                "        \"itemName\": \"MCU_STM32F207VET6\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 140,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 540.22,\n" +
//                "                \"freightUntaxedPrice\": 478.0700,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 540.22,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110014\",\n" +
//                "                \"itemId\": 37661,\n" +
//                "                \"itemName\": \"MCU_STM32F429IGT6\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23387,\n" +
//                "                \"quoteOrderLineNumber\": 23387,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12868,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 478.0700,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37661\",\n" +
//                "        \"itemCode\": \"111110014\",\n" +
//                "        \"itemId\": 37661,\n" +
//                "        \"itemName\": \"MCU_STM32F429IGT6\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 86.03,\n" +
//                "                \"freightUntaxedPrice\": 76.1300,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 86.03,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180002\",\n" +
//                "                \"itemId\": 37826,\n" +
//                "                \"itemName\": \"USB2.0百兆以太网控制器_RTL8152B-VB-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23392,\n" +
//                "                \"quoteOrderLineNumber\": 23392,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12873,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 76.1300,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37826\",\n" +
//                "        \"itemCode\": \"111180002\",\n" +
//                "        \"itemId\": 37826,\n" +
//                "        \"itemName\": \"USB2.0百兆以太网控制器_RTL8152B-VB-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-07-01\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 1.14,\n" +
//                "                \"freightUntaxedPrice\": 1.0100,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 1.14,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180034\",\n" +
//                "                \"itemId\": 37857,\n" +
//                "                \"itemName\": \"千兆以太网控制器_RTL8363NB-VB-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 1,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23400,\n" +
//                "                \"quoteOrderLineNumber\": 23400,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12860,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 1.0100,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37857\",\n" +
//                "        \"itemCode\": \"111180034\",\n" +
//                "        \"itemId\": 37857,\n" +
//                "        \"itemName\": \"千兆以太网控制器_RTL8363NB-VB-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 126,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 20.07,\n" +
//                "                \"freightUntaxedPrice\": 17.7600,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 20.07,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110012\",\n" +
//                "                \"itemId\": 37659,\n" +
//                "                \"itemName\": \"MCU_STM8S003F3P6TR\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23389,\n" +
//                "                \"quoteOrderLineNumber\": 23389,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12870,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 17.7600,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37659\",\n" +
//                "        \"itemCode\": \"111110012\",\n" +
//                "        \"itemId\": 37659,\n" +
//                "        \"itemName\": \"MCU_STM8S003F3P6TR\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 16.06,\n" +
//                "                \"freightUntaxedPrice\": 14.2100,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 16.06,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180054\",\n" +
//                "                \"itemId\": 37877,\n" +
//                "                \"itemName\": \"千兆以太网控制器_RTL8367S-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 10,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23401,\n" +
//                "                \"quoteOrderLineNumber\": 23401,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12861,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 14.2100,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37877\",\n" +
//                "        \"itemCode\": \"111180054\",\n" +
//                "        \"itemId\": 37877,\n" +
//                "        \"itemName\": \"千兆以太网控制器_RTL8367S-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 126.16,\n" +
//                "                \"freightUntaxedPrice\": 111.6500,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 126.16,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180065\",\n" +
//                "                \"itemId\": 37888,\n" +
//                "                \"itemName\": \"2.5G PHY_RTL8221D-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23398,\n" +
//                "                \"quoteOrderLineNumber\": 23398,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12857,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 111.6500,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37888\",\n" +
//                "        \"itemCode\": \"111180065\",\n" +
//                "        \"itemId\": 37888,\n" +
//                "        \"itemName\": \"2.5G PHY_RTL8221D-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 13.76,\n" +
//                "                \"freightUntaxedPrice\": 12.1800,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 13.76,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180043\",\n" +
//                "                \"itemId\": 37866,\n" +
//                "                \"itemName\": \"千兆以太网控制器_RTL9301-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 1,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23383,\n" +
//                "                \"quoteOrderLineNumber\": 23383,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12864,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 12.1800,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37866\",\n" +
//                "        \"itemCode\": \"111180043\",\n" +
//                "        \"itemId\": 37866,\n" +
//                "        \"itemName\": \"千兆以太网控制器_RTL9301-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 13.76,\n" +
//                "                \"freightUntaxedPrice\": 12.1800,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 13.76,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180033\",\n" +
//                "                \"itemId\": 37856,\n" +
//                "                \"itemName\": \"百兆以太网收发器_RTL8201F-VB-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23394,\n" +
//                "                \"quoteOrderLineNumber\": 23394,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12875,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 12.1800,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37856\",\n" +
//                "        \"itemCode\": \"111180033\",\n" +
//                "        \"itemId\": 37856,\n" +
//                "        \"itemName\": \"百兆以太网收发器_RTL8201F-VB-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2293,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 154,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 521.87,\n" +
//                "                \"freightUntaxedPrice\": 461.8300,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 521.87,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111110022\",\n" +
//                "                \"itemId\": 37669,\n" +
//                "                \"itemName\": \"MCU_STM32H750XBH6\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23388,\n" +
//                "                \"quoteOrderLineNumber\": 23388,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12869,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 461.8300,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37669\",\n" +
//                "        \"itemCode\": \"111110022\",\n" +
//                "        \"itemId\": 37669,\n" +
//                "        \"itemName\": \"MCU_STM32H750XBH6\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 65.38,\n" +
//                "                \"freightUntaxedPrice\": 57.8600,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 65.38,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180030\",\n" +
//                "                \"itemId\": 37853,\n" +
//                "                \"itemName\": \"千兆以太网收发器_RTL8211FS-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23395,\n" +
//                "                \"quoteOrderLineNumber\": 23395,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12876,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 57.8600,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37853\",\n" +
//                "        \"itemCode\": \"111180030\",\n" +
//                "        \"itemId\": 37853,\n" +
//                "        \"itemName\": \"千兆以太网收发器_RTL8211FS-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 27.53,\n" +
//                "                \"freightUntaxedPrice\": 24.3600,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 27.53,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180053\",\n" +
//                "                \"itemId\": 37876,\n" +
//                "                \"itemName\": \"千兆以太网收发器_RTL8218E-VH-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 10,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23397,\n" +
//                "                \"quoteOrderLineNumber\": 23397,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12878,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 24.3600,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37876\",\n" +
//                "        \"itemCode\": \"111180053\",\n" +
//                "        \"itemId\": 37876,\n" +
//                "        \"itemName\": \"千兆以太网收发器_RTL8218E-VH-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }, {\n" +
//                "        \"agreementDate\": \"2025-06-30\",\n" +
//                "        \"agreementLineVOList\": [{\n" +
//                "                \"agentId\": 207,\n" +
//                "                \"categoryId\": 2300,\n" +
//                "                \"createdBy\": 264,\n" +
//                "                \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"currencyCode\": \"USD\",\n" +
//                "                \"dateDifference\": 0,\n" +
//                "                \"deliveryDays\": 119,\n" +
//                "                \"endDate\": \"2025-12-31\",\n" +
//                "                \"exchangeRate\": 7.184800,\n" +
//                "                \"fixPriceDateControl\": \"PO_DATE\",\n" +
//                "                \"freightPoint\": 0,\n" +
//                "                \"freightTaxedPrice\": 177.78,\n" +
//                "                \"freightUntaxedPrice\": 157.3300,\n" +
//                "                \"handleFlag\": \"N\",\n" +
//                "                \"includeTaxPrice\": 177.78,\n" +
//                "                \"infoRecordType\": \"0\",\n" +
//                "                \"itemCode\": \"111180005\",\n" +
//                "                \"itemId\": 37829,\n" +
//                "                \"itemName\": \"USB3.0千兆以太网控制器_RTL8153B-VB-CG\",\n" +
//                "                \"itemSourceType\": 1,\n" +
//                "                \"iuId\": 3,\n" +
//                "                \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "                \"lastUpdateLogin\": 86326,\n" +
//                "                \"lastUpdatedBy\": 264,\n" +
//                "                \"lineNumber\": 1,\n" +
//                "                \"lineStatus\": \"OPEN\",\n" +
//                "                \"ouId\": 2,\n" +
//                "                \"pgId\": 16,\n" +
//                "                \"preQty\": 100,\n" +
//                "                \"priceTermType\": \"PB00\",\n" +
//                "                \"priceType\": \"2\",\n" +
//                "                \"quoteOrderId\": 2686,\n" +
//                "                \"quoteOrderLineId\": 23393,\n" +
//                "                \"quoteOrderLineNumber\": 23393,\n" +
//                "                \"quoteOrderNum\": \"BJ-2025062500023\",\n" +
//                "                \"removeFlag\": \"N\",\n" +
//                "                \"sapStatus\": \"NOT_SYNC\",\n" +
//                "                \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "                \"sourceHeaderId\": 2236,\n" +
//                "                \"sourceLineId\": 12874,\n" +
//                "                \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "                \"startDate\": \"2025-06-30\",\n" +
//                "                \"stepPriceFlag\": \"NO\",\n" +
//                "                \"taxCode\": \"J1\",\n" +
//                "                \"taxRate\": 13.00,\n" +
//                "                \"untaxedPrice\": 157.3300,\n" +
//                "                \"uomCode\": \"PC\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"agreementStatus\": \"EFFECTIVE\",\n" +
//                "        \"agreementVersion\": 0,\n" +
//                "        \"approvalDate\": \"2025-06-30\",\n" +
//                "        \"asyncPushSapFlag\": \"Y\",\n" +
//                "        \"createdBy\": 264,\n" +
//                "        \"creationDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"dataKey\": \"1_2_3_207_37829\",\n" +
//                "        \"itemCode\": \"111180005\",\n" +
//                "        \"itemId\": 37829,\n" +
//                "        \"itemName\": \"USB3.0千兆以太网控制器_RTL8153B-VB-CG\",\n" +
//                "        \"iuId\": 3,\n" +
//                "        \"lastUpdateDate\": \"2025-06-30 11:10:30\",\n" +
//                "        \"lastUpdateLogin\": 86326,\n" +
//                "        \"lastUpdatedBy\": 264,\n" +
//                "        \"ouId\": 2,\n" +
//                "        \"puId\": 1,\n" +
//                "        \"pushSapFlag\": \"Y\",\n" +
//                "        \"sourceCode\": \"FROM_INQUIRY\",\n" +
//                "        \"sourceId\": \"2236\",\n" +
//                "        \"sourceNum\": \"RFQ-2025062500005\",\n" +
//                "        \"supplierId\": 207\n" +
//                "    }\n" +
//                "]\n";
//        JSONArray objArr = JSONObject.parseArray(json);
//        JSONArray lineArr = new JSONArray();
//        JSONArray dataKeyArr = new JSONArray();
//        Iterator<Object> iterator = objArr.iterator();
//        while (iterator.hasNext()){
//            JSONObject obj = (JSONObject) iterator.next();
//            if (null!=obj.getString("dataKey")){
//                dataKeyArr.add(obj.getString("dataKey"));
//            }
//            JSONArray lines = obj.getJSONArray("agreementLineVOList");
//            Iterator<Object> lineIterator = lines.iterator();
//            while (lineIterator.hasNext()){
//                JSONObject line = (JSONObject) lineIterator.next();
//                lineArr.add(line);
//            }
//        }
//
//        System.out.println(JSON.toJSONString(lineArr));
////        System.out.println("datakey:\n"+JSON.toJSONString(dataKeyArr));
//    }
public static void main(String[] args) {
    String input = " [SrmPurAgreementLineVO(agreementLineId=55633, agreementHeaderId=20886, lineNumber=1, lineStatus=OPEN, agentId=207, ouId=2, iuId=3, pgId=16, itemId=37877, itemCode=111180054, itemName=千兆以太网控制器_RTL8367S-CG, categoryId=2300, materialGroup=null, uomCode=PC, itemSourceType=null, currencyCode=USD, taxCode=J1, taxRate=13.000, preQty=10.000, untaxedPrice=14.140000, untaxedPriceDividePre=null, includeTaxPrice=15.980000, includeTaxPriceDividePre=null, freightPoint=0.000, freightUntaxedPrice=14.140000, freightUntaxedPriceDividePre=null, freightTaxedPrice=15.980000, freightTaxedPriceDividePre=null, startDate=2025-05-27, endDate=2025-06-29, infoRecordType=0, removeFlag=N, priceType=2, fixPriceDateControl=PO_DATE, stepPriceFlag=NO, sapStatus=NOT_SYNC, sapMessage=采购信息记录5300011570 NC01  1100改变;, sapInfoCode=5300011570, syncAction=U, syncBatchNum=35609696461905429238, syncRemoveFlag=null, sourceCode=FROM_INQUIRY, sourceHeaderId=1044, sourceNum=RFQ-2025042800024.2, sourceLineId=7932, sourceLineNumber=null, remark=null, mainItemId=null, mainItemCode=null, mainItemName=null, fixBidTaxPrice=null, originalUntaxedPrice=null, originalIncludeTaxPrice=null, originalTaxCode=null, originalTaxRate=null, originalPreQty=null, priceUomCode=null, lowestPrice=null, lowestFloatRate=null, latestPrice=null, latestFloatRate=null, cumulativeCalculationFlag=null, minOrderQty=null, minPacking=null, deliveryDays=119, shelfLifeDays=null, fileIds=null, moldShareCost=null, priceTermType=PB00, quoteOrderId=1616, quoteOrderNum=BJ-2025052300011, quoteOrderLineId=15634, quoteOrderLineNumber=15634, attributeCategory=null, attribute1=null, attribute2=null, attribute3=null, attribute4=null, attribute5=null, sourceLineStatus=null, originalSourceLineId=null, agreementLineStepVOList=null, agreementStatus=null, infoRecordNum=null, supplierId=null, supplierNum=null, supplierErpNum=null, supplierName=null, puId=null, puCode=null, puName=null, ouCode=null, ouName=null, iuCode=null, iuName=null, fullCategoryCode=null, fullCategoryName=null, pgCode=null, pgName=null, pgCodeName=null, agentNum=null, agentErpNum=null, agentName=null, cbuMolecule=null, cbuDenominator=null, agreementLineHisId=null, agreementHeaderHisId=null, operationType=null, agreementNum=null, globalFlag=null, ids=null, agreementLineIds=null, dateDifference=0, exchangeRate=7.178200, handleFlag=N, alertPersonId=null, alertPersonEmail=null, aliveTime=null, priceUnitHeaderId=null), SrmPurAgreementLineVO(agreementLineId=null, agreementHeaderId=null, lineNumber=1, lineStatus=OPEN, agentId=207, ouId=2, iuId=3, pgId=16, itemId=37877, itemCode=111180054, itemName=千兆以太网控制器_RTL8367S-CG, categoryId=2300, materialGroup=null, uomCode=PC, itemSourceType=1, currencyCode=USD, taxCode=J1, taxRate=13.00, preQty=10, untaxedPrice=14.2100, untaxedPriceDividePre=null, includeTaxPrice=16.06, includeTaxPriceDividePre=null, freightPoint=0, freightUntaxedPrice=14.2100, freightUntaxedPriceDividePre=null, freightTaxedPrice=16.06, freightTaxedPriceDividePre=null, startDate=2025-06-30, endDate=2025-12-31, infoRecordType=0, removeFlag=N, priceType=2, fixPriceDateControl=PO_DATE, stepPriceFlag=NO, sapStatus=NOT_SYNC, sapMessage=null, sapInfoCode=null, syncAction=null, syncBatchNum=null, syncRemoveFlag=null, sourceCode=FROM_INQUIRY, sourceHeaderId=2236, sourceNum=RFQ-2025062500005, sourceLineId=12861, sourceLineNumber=null, remark=null, mainItemId=null, mainItemCode=null, mainItemName=null, fixBidTaxPrice=null, originalUntaxedPrice=null, originalIncludeTaxPrice=null, originalTaxCode=null, originalTaxRate=null, originalPreQty=null, priceUomCode=null, lowestPrice=null, lowestFloatRate=null, latestPrice=null, latestFloatRate=null, cumulativeCalculationFlag=null, minOrderQty=null, minPacking=null, deliveryDays=119, shelfLifeDays=null, fileIds=null, moldShareCost=null, priceTermType=PB00, quoteOrderId=2686, quoteOrderNum=BJ-2025062500023, quoteOrderLineId=23401, quoteOrderLineNumber=23401, attributeCategory=null, attribute1=null, attribute2=null, attribute3=null, attribute4=null, attribute5=null, sourceLineStatus=null, originalSourceLineId=null, agreementLineStepVOList=null, agreementStatus=null, infoRecordNum=null, supplierId=null, supplierNum=null, supplierErpNum=null, supplierName=null, puId=null, puCode=null, puName=null, ouCode=null, ouName=null, iuCode=null, iuName=null, fullCategoryCode=null, fullCategoryName=null, pgCode=null, pgName=null, pgCodeName=null, agentNum=null, agentErpNum=null, agentName=null, cbuMolecule=null, cbuDenominator=null, agreementLineHisId=null, agreementHeaderHisId=null, operationType=null, agreementNum=null, globalFlag=null, ids=null, agreementLineIds=null, dateDifference=0, exchangeRate=7.184800, handleFlag=N, alertPersonId=null, alertPersonEmail=null, aliveTime=null, priceUnitHeaderId=null), SrmPurAgreementLineVO(agreementLineId=null, agreementHeaderId=null, lineNumber=1, lineStatus=OPEN, agentId=207, ouId=2, iuId=3, pgId=16, itemId=37877, itemCode=111180054, itemName=千兆以太网控制器_RTL8367S-CG, categoryId=2300, materialGroup=null, uomCode=PC, itemSourceType=1, currencyCode=USD, taxCode=J1, taxRate=13.00, preQty=10, untaxedPrice=14.2100, untaxedPriceDividePre=null, includeTaxPrice=16.06, includeTaxPriceDividePre=null, freightPoint=0, freightUntaxedPrice=14.2100, freightUntaxedPriceDividePre=null, freightTaxedPrice=16.06, freightTaxedPriceDividePre=null, startDate=2025-06-30, endDate=2025-12-31, infoRecordType=0, removeFlag=N, priceType=2, fixPriceDateControl=PO_DATE, stepPriceFlag=NO, sapStatus=NOT_SYNC, sapMessage=null, sapInfoCode=null, syncAction=null, syncBatchNum=null, syncRemoveFlag=null, sourceCode=FROM_INQUIRY, sourceHeaderId=2236, sourceNum=RFQ-2025062500005, sourceLineId=12861, sourceLineNumber=null, remark=null, mainItemId=null, mainItemCode=null, mainItemName=null, fixBidTaxPrice=null, originalUntaxedPrice=null, originalIncludeTaxPrice=null, originalTaxCode=null, originalTaxRate=null, originalPreQty=null, priceUomCode=null, lowestPrice=null, lowestFloatRate=null, latestPrice=null, latestFloatRate=null, cumulativeCalculationFlag=null, minOrderQty=null, minPacking=null, deliveryDays=119, shelfLifeDays=null, fileIds=null, moldShareCost=null, priceTermType=PB00, quoteOrderId=2686, quoteOrderNum=BJ-2025062500023, quoteOrderLineId=23401, quoteOrderLineNumber=23401, attributeCategory=null, attribute1=null, attribute2=null, attribute3=null, attribute4=null, attribute5=null, sourceLineStatus=null, originalSourceLineId=null, agreementLineStepVOList=null, agreementStatus=null, infoRecordNum=null, supplierId=null, supplierNum=null, supplierErpNum=null, supplierName=null, puId=null, puCode=null, puName=null, ouCode=null, ouName=null, iuCode=null, iuName=null, fullCategoryCode=null, fullCategoryName=null, pgCode=null, pgName=null, pgCodeName=null, agentNum=null, agentErpNum=null, agentName=null, cbuMolecule=null, cbuDenominator=null, agreementLineHisId=null, agreementHeaderHisId=null, operationType=null, agreementNum=null, globalFlag=null, ids=null, agreementLineIds=null, dateDifference=0, exchangeRate=7.184800, handleFlag=N, alertPersonId=null, alertPersonEmail=null, aliveTime=null, priceUnitHeaderId=null), SrmPurAgreementLineVO(agreementLineId=55634, agreementHeaderId=20886, lineNumber=null, lineStatus=null, agentId=null, ouId=null, iuId=null, pgId=null, itemId=null, itemCode=null, itemName=null, categoryId=null, materialGroup=null, uomCode=null, itemSourceType=null, currencyCode=null, taxCode=null, taxRate=null, preQty=null, untaxedPrice=null, untaxedPriceDividePre=null, includeTaxPrice=null, includeTaxPriceDividePre=null, freightPoint=null, freightUntaxedPrice=null, freightUntaxedPriceDividePre=null, freightTaxedPrice=null, freightTaxedPriceDividePre=null, startDate=2025-07-01, endDate=2025-12-31, infoRecordType=null, removeFlag=null, priceType=null, fixPriceDateControl=null, stepPriceFlag=null, sapStatus=NOT_SYNC, sapMessage=null, sapInfoCode=5300011570, syncAction=U, syncBatchNum=35609696461905429238, syncRemoveFlag=null, sourceCode=null, sourceHeaderId=null, sourceNum=null, sourceLineId=null, sourceLineNumber=null, remark=null, mainItemId=null, mainItemCode=null, mainItemName=null, fixBidTaxPrice=null, originalUntaxedPrice=null, originalIncludeTaxPrice=null, originalTaxCode=null, originalTaxRate=null, originalPreQty=null, priceUomCode=null, lowestPrice=null, lowestFloatRate=null, latestPrice=null, latestFloatRate=null, cumulativeCalculationFlag=null, minOrderQty=null, minPacking=null, deliveryDays=null, shelfLifeDays=null, fileIds=null, moldShareCost=null, priceTermType=null, quoteOrderId=null, quoteOrderNum=null, quoteOrderLineId=null, quoteOrderLineNumber=null, attributeCategory=null, attribute1=null, attribute2=null, attribute3=null, attribute4=null, attribute5=null, sourceLineStatus=null, originalSourceLineId=null, agreementLineStepVOList=null, agreementStatus=null, infoRecordNum=null, supplierId=null, supplierNum=null, supplierErpNum=null, supplierName=null, puId=null, puCode=null, puName=null, ouCode=null, ouName=null, iuCode=null, iuName=null, fullCategoryCode=null, fullCategoryName=null, pgCode=null, pgName=null, pgCodeName=null, agentNum=null, agentErpNum=null, agentName=null, cbuMolecule=null, cbuDenominator=null, agreementLineHisId=null, agreementHeaderHisId=null, operationType=null, agreementNum=null, globalFlag=null, ids=null, agreementLineIds=null, dateDifference=0, exchangeRate=null, handleFlag=N, alertPersonId=null, alertPersonEmail=null, aliveTime=null, priceUnitHeaderId=null), SrmPurAgreementLineVO(agreementLineId=55634, agreementHeaderId=20886, lineNumber=null, lineStatus=null, agentId=null, ouId=null, iuId=null, pgId=null, itemId=null, itemCode=null, itemName=null, categoryId=null, materialGroup=null, uomCode=null, itemSourceType=null, currencyCode=null, taxCode=null, taxRate=null, preQty=null, untaxedPrice=null, untaxedPriceDividePre=null, includeTaxPrice=null, includeTaxPriceDividePre=null, freightPoint=null, freightUntaxedPrice=null, freightUntaxedPriceDividePre=null, freightTaxedPrice=null, freightTaxedPriceDividePre=null, startDate=2025-07-01, endDate=2025-12-31, infoRecordType=null, removeFlag=null, priceType=null, fixPriceDateControl=null, stepPriceFlag=null, sapStatus=NOT_SYNC, sapMessage=null, sapInfoCode=5300011570, syncAction=U, syncBatchNum=35609696461905429238, syncRemoveFlag=null, sourceCode=null, sourceHeaderId=null, sourceNum=null, sourceLineId=null, sourceLineNumber=null, remark=null, mainItemId=null, mainItemCode=null, mainItemName=null, fixBidTaxPrice=null, originalUntaxedPrice=null, originalIncludeTaxPrice=null, originalTaxCode=null, originalTaxRate=null, originalPreQty=null, priceUomCode=null, lowestPrice=null, lowestFloatRate=null, latestPrice=null, latestFloatRate=null, cumulativeCalculationFlag=null, minOrderQty=null, minPacking=null, deliveryDays=null, shelfLifeDays=null, fileIds=null, moldShareCost=null, priceTermType=null, quoteOrderId=null, quoteOrderNum=null, quoteOrderLineId=null, quoteOrderLineNumber=null, attributeCategory=null, attribute1=null, attribute2=null, attribute3=null, attribute4=null, attribute5=null, sourceLineStatus=null, originalSourceLineId=null, agreementLineStepVOList=null, agreementStatus=null, infoRecordNum=null, supplierId=null, supplierNum=null, supplierErpNum=null, supplierName=null, puId=null, puCode=null, puName=null, ouCode=null, ouName=null, iuCode=null, iuName=null, fullCategoryCode=null, fullCategoryName=null, pgCode=null, pgName=null, pgCodeName=null, agentNum=null, agentErpNum=null, agentName=null, cbuMolecule=null, cbuDenominator=null, agreementLineHisId=null, agreementHeaderHisId=null, operationType=null, agreementNum=null, globalFlag=null, ids=null, agreementLineIds=null, dateDifference=0, exchangeRate=null, handleFlag=N, alertPersonId=null, alertPersonEmail=null, aliveTime=null, priceUnitHeaderId=null)]";

    JSONArray jsonArray = convertToJsonArray(input);
    System.out.println(jsonArray.toJSONString());
}

    public static JSONArray convertToJsonArray(String input) {
        JSONArray result = new JSONArray();
        // 匹配每个 SrmPurAgreementLineVO 对象
        Pattern objectPattern = Pattern.compile("SrmPurAgreementLineVO\\(([^)]+)\\)");
        Matcher objectMatcher = objectPattern.matcher(input);

        while (objectMatcher.find()) {
            String objectStr = objectMatcher.group(1);
            JSONObject jsonObject = new JSONObject();
            // 分割每个键值对
            String[] keyValuePairs = objectStr.split(",\\s*");
            for (String pair : keyValuePairs) {
                String[] parts = pair.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // 处理 null 值
                    if ("null".equals(value)) {
                        jsonObject.put(key, null);
                    } else {
                        jsonObject.put(key, value);
                    }
                }
            }
            result.add(jsonObject);
        }
        return result;
    }
}
