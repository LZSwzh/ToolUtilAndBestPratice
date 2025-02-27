package excel.poi;

import com.alibaba.fastjson.JSONObject;
import excel.entity.ExcelDataEntry;
import excel.entity.ExcelSheet;
import excel.entity.ExcelUtil;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadPic4Xlsx {
    public static void main(String[] args) {
//        readFloatPic();
        readEmbedPic();
    }



    public static void readEmbedPic(){
        String path = "output.xlsx";
        String imageTargetPath = "D:/workspace/BestPratice/ToolUtilAndBestPratice";

        ExcelUtil excelUtil = new ExcelUtil(path, imageTargetPath);
        ExcelSheet sheet = excelUtil.getSheet(0,1);


        System.out.println(sheet.getRowDataList());
        List<Map<Integer, ExcelDataEntry>> rowDataList = sheet.getRowDataList();
        String s = JSONObject.toJSONString(rowDataList);
        //这里打印的是所有行的数据，每个元素对应一行数据
        System.out.println(s);
/*
        //这里打印的是标题为编号对应列的数据，每个元素对应一个自定义的Entry对象
        List<ExcelDataEntry> a = sheet.getDataByTitle("编号" );
        System.out.println("a:"+a);
        //这里打印的是指定标题为编号、指定行号2打印的数据，缺省了一个titleRow,默认是
        ExcelDataEntry c = sheet.getDataByTitle("编号" ,2);
        System.out.println("c:"+c);

        ExcelDataEntry d = sheet.getDataByTitle(1,"编号" ,2);
        System.out.println("d:"+d);
*/
        List<Map<Object, ExcelDataEntry>> colDataList = sheet.getColDataList();
        String colDataListString = JSONObject.toJSONString(colDataList);
        System.out.println(colDataListString);
    }

    public static void readFloatPic(){
        String excelFilePath = "output.xlsx"; // 替换为你的 Excel 文件路径
        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            Workbook workbook = WorkbookFactory.create(fis); // 自动识别文件格式
            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表

            // 获取所有图片数据
            List<PictureData> pictures = (List<PictureData>) workbook.getAllPictures();

            if (pictures == null || pictures.isEmpty()) {
                System.out.println("没有找到图片！");
                return;
            }

            // 遍历所有图片
            int pictureIndex = 0;
            for (PictureData picture : pictures) {
                // 获取图片的字节数据和扩展名
                byte[] data = picture.getData();
                String extension = picture.suggestFileExtension();

                // 保存图片到本地文件
                String outputFilePath = "extracted_image_" + pictureIndex + "." + extension; // 输出路径
                try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                    fos.write(data);
                    System.out.println("图片已保存到: " + outputFilePath);
                }
                pictureIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
