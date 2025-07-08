package excel.eaxyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SyncRead1 {
    public static final String FILE_PATH = "D:\\555.XLSX";
    public static void main(String[] args) {
        //输出当前JDK版本
        System.out.println("当前JDK版本：" + System.getProperty("java.version"));
        ArrayList<String> colData = Lists.newArrayList();

        List<DemoData> list = EasyExcel.read(FILE_PATH).head(DemoData.class).sheet().doReadSync();
        for (DemoData data : list) {
            // 使用静态方法调用
            log.info("读取到数据:{}", JSONObject.toJSONString(data));
            colData.add(data.getItemCode());
//            colData.add(data.getPurOrder());
        }
        System.out.println("最终数据:\n"+colData.stream().distinct().map(t->"'"+t+"'").collect(Collectors.joining(",")));
        System.out.println("最终数据大小:\n" + colData.stream().distinct().count());
    }

    /**
     * 同步方式读取Excel
     */
    @Data
    // 将 DemoData 类声明为 static 类
    public static class DemoData {
        /**
         * 强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
         */
//        @ExcelProperty(index = 0)
//        private Long planetCode;
        /**
         * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
         */
//        @ExcelProperty(value = "采购订单")
//        private String purOrder;
        @ExcelProperty(value = "物料编码")
        private String itemCode;
    }
}
