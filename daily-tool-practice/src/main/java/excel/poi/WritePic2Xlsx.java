package excel.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritePic2Xlsx {
    /**
     * 什么是浮动：直接CV过来，鼠标可以随意拖动位置；不受单元格大小和位置的限制
     * 什么是嵌入: 点击插入,选择图片,选择转换成嵌入的
     * ClientAnchor.AnchorType枚举:
     *         MOVE_AND_RESIZE(0),完全嵌入单元格并随单元格调整大小
     *         DONT_MOVE_DO_RESIZE(1),固定在某个位置但随单元格调整大小
     *         MOVE_DONT_RESIZE(2),随单元格移动但保持固定大小
     *         DONT_MOVE_AND_RESIZE(3);完全独立于单元格的移动和大小调整
     * @param args
     */
    public static void main(String[] args){
        try {
            //向Excel中插入浮动的图片
//            insertFloatPic();
            //向Excel中插入嵌入的图片
            insertEmbedPic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertFloatPic() throws IOException {
        // 创建工作簿【适用于.xlsx格式】
        Workbook workbook = new XSSFWorkbook();
        // 创建sheet页
        Sheet sheet = workbook.createSheet("Sheet1");

        // 创建第一行，在第一行创建第一列单元格
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

        // 读取图片文件为byte数组
        FileInputStream fis = new FileInputStream("D:/pic/java.jpg"); // 替换为图片路径
        byte[] bytes = new byte[fis.available()]; // 创建一个足够大的字节数组
        fis.read(bytes);
        fis.close();

        // 将图片添加到工作簿
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

        // 创建图片锚点
        CreationHelper helper = workbook.getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        // 列起始位置
        anchor.setCol1(cell.getColumnIndex());
        // 行起始位置
        anchor.setRow1(row.getRowNum());
        anchor.setCol2(cell.getColumnIndex() + 1); // 列结束位置
        anchor.setRow2(row.getRowNum() + 1); // 行结束位置

        // 创建图片并插入
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        drawing.createPicture(anchor, pictureIdx);

        // 保存文件
        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    public static void insertEmbedPic() throws IOException{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

        FileInputStream fis = new FileInputStream("D:/pic/java.jpg");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

        CreationHelper helper = workbook.getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        anchor.setCol1(cell.getColumnIndex());
        anchor.setRow1(row.getRowNum());
        anchor.setCol2(cell.getColumnIndex() + 1);
        anchor.setRow2(row.getRowNum() + 1);

        Drawing<?> drawing = sheet.createDrawingPatriarch();
        drawing.createPicture(anchor, pictureIdx);

        try (FileOutputStream fileOut = new FileOutputStream("output2.xlsx")) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}
/*
<!-- Apache poi 核心库 &处理.xlsx文件的OOXML支持库 -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.3</version>
            <exclusions>
                <exclusion>
                    <groupId>org.apache.poi</groupId>
                    <artifactId>poi-ooxml-schemas</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.3</version>
        </dependency>
 */