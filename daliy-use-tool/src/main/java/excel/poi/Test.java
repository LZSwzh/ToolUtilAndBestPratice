package excel.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String path = "output.xlsx";
        try(FileInputStream fis=new FileInputStream(path)){
            //读取xlsx工作簿
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            //获取第0个Sheet
            XSSFSheet sheet = wb.getSheetAt(0);
            for(Row row:sheet){
                int rowIndex = row.getRowNum(); // 获取行索引
                System.out.println(String.format("============遍历第:{}行===========",rowIndex));
                for(Cell cell:row){
                    int colIndex = cell.getColumnIndex(); // 获取列索引
                    System.out.println(String.format("============遍历第:{}列===========",colIndex));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
