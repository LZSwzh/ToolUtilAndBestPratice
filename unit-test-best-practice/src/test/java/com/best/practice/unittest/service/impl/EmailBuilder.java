package com.best.practice.unittest.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmailBuilder {
    public static void main(String[] args) {

        String flowNum = "20250330";
        String supplierName = "上海中科华源信息科技有限公司";
        String categoryName = "结构件";
        String times = "2023-05-03~2025-03-01";
        ArrayList<String> modules = new ArrayList<>();
        modules.add("成本");
        modules.add("技术支持");
        modules.add("质量+交付");
        modules.add("保密");
        modules.add("总分");
        ArrayList<BigDecimal> weights = new ArrayList<>();
        weights.add(BigDecimal.valueOf(15));
        weights.add(BigDecimal.valueOf(15));
        weights.add(BigDecimal.valueOf(70));
        weights.add(BigDecimal.ZERO);
        weights.add(weights.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        ArrayList<BigDecimal> scores = new ArrayList<>();
        scores.add(BigDecimal.valueOf(12.6));
        scores.add(BigDecimal.valueOf(10.45));
        scores.add(BigDecimal.valueOf(0));
        scores.add(BigDecimal.valueOf(-72.5));
        scores.add(scores.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        ArrayList<Integer> ranks = new ArrayList<>();
        ranks.add(1);
        ranks.add(2);
        ranks.add(3);
        ranks.add(4);
        //总分排名
        ranks.add(1);
        StringBuilder emailHeader = buildHtmlHeader(flowNum, supplierName, categoryName, times, ranks.get(ranks.size()-1));
        StringBuilder tableHeader = builderTableHeader(emailHeader, modules, weights, scores);
        StringBuilder emailBuilder = builderTableAndTail(tableHeader, categoryName, ranks);

        System.out.println(emailBuilder.toString());
    }
    private static StringBuilder buildHtmlHeader(String flowNum,String supplierName,String categoryName,String times,Integer totalRank){
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"zh-CN\">\n");
        sb.append("<head>\n");
        sb.append("    <meta charset=\"UTF-8\">\n");
        sb.append("    <title>诺瓦星云运营指标晾晒</title>\n");
        sb.append("    <style>\n");
        sb.append("        body{font-family:\"PingFang SC\",\"Microsoft YaHei\",sans-serif;background:#f5f5f5;margin:0;padding:0;}\n");
        sb.append("        .report-container{max-width:800px;margin:40px auto;background:#fff;padding:40px;box-shadow:0 0 10px rgba(0,0,0,.1);border-radius:8px;}\n");
        sb.append("        .header{text-align:center;border-bottom:2px solid #d9534f;padding-bottom:20px;margin-bottom:30px;}\n");
        sb.append("        .header h1{font-size:28px;color:#333;margin:0;}\n");
        sb.append("        .header p{font-size:14px;color:#888;margin:5px 0 0;}\n");
        sb.append("        .content{font-size:16px;line-height:1.8;color:#333;}\n");
        sb.append("        .content p{margin:10px 0;}\n");
        sb.append("        .score-table{width:100%;border-collapse:collapse;margin:30px 0;}\n");
        sb.append("        .score-table th,.score-table td{border:1px solid #e6e6e6;padding:12px;text-align:center;}\n");
        sb.append("        .score-table th{background:#f9f9f9;font-weight:bold;}\n");
        sb.append("        .highlight{color:#d9534f;font-weight:bold;}\n");
        sb.append("    </style>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("    <div class=\"report-container\">\n");
        sb.append("        <div class=\"header\">\n");
        sb.append("            <h1>诺瓦星云运营指标晾晒</h1>\n");
        sb.append("            <p>采购部管理运营文件 编号：").append(flowNum).append("</p>\n");
        sb.append("        </div>\n");
        sb.append("        <div class=\"content\">\n");
        sb.append("            <p>").append(supplierName).append("：</p>\n");
        sb.append("            <p><strong>").append(categoryName).append("绩效排名并列第").append(totalRank).append("</strong></p>\n");
        sb.append("            <p>在").append(times).append("的").append(categoryName).append("绩效评估中，贵司表现优异，特通报如下：</p>\n");
        return sb;
    }

    private static StringBuilder builderTableHeader(StringBuilder sb, List<String> modules, List<BigDecimal> weights, List<BigDecimal> scores){
        /* ===== 动态表格开始 ===== */
        sb.append("            <table class=\"score-table\">\n");
        sb.append("                <thead>\n");
        sb.append("                    <tr>\n");
        sb.append("                        <th>模块</th>\n");
        for (int i = 0; i <modules.size(); i++) {
            String moduleName = modules.get(i);
            BigDecimal weightScore = weights.get(i);
            sb.append("                        <th>").append(moduleName).append("<br>(").append(weightScore).append("分)</th>\n");
        }
        sb.append("                    </tr>\n");
        sb.append("                </thead>\n");


        sb.append("                <tbody>\n");
        sb.append("                    <tr>\n");
        sb.append("                        <td>得分</td>\n");
        for (int i = 0; i < scores.size(); i++) {
            BigDecimal score = scores.get(i);
            if (scores.size()-1==i){
                sb.append("                        <td class=\"highlight\">").append(score).append("</td>\n");
            }else{
                sb.append("                        <td>").append(score).append("</td>\n");
            }
        }
        sb.append("                    </tr>\n");
        return sb;
    }
    private static StringBuilder builderTableAndTail(StringBuilder sb,String categoryName,List<Integer> ranks){
        // 3. 动态排名行（同样可替换为变量）
        sb.append("                    <tr>\n");
        sb.append("                        <td>").append(categoryName).append("</td>\n");
        for(Integer rank:ranks){
            sb.append("                        <td class=\"highlight\">并列第").append(rank).append("</td>\n");
        }
        sb.append("                    </tr>\n");
        sb.append("                </tbody>\n");
        sb.append("            </table>\n");
        /* ===== 动态表格结束 ===== */
        sb.append("            <p>特此通报，以资鼓励！</p>\n");
        sb.append("            <p style=\"text-align:right;\">诺瓦星云科技股份有限公司<br>采购部</p>\n");
        sb.append("        </div>\n");
        sb.append("    </div>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb;
    }
}
