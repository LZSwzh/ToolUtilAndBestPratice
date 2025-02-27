package excel.entity;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelUtil {
    private List<ExcelSheet> sheetList=new ArrayList<>();
    private String rootPath = "";
    private String imageTargetPath = "";
    private Workbook workbook = null;

    /**
     * 构造器指定Excel文件路径以及输出的图片路径，并将excel数据读取到内存
     * @param filePath
     * @param imageTargetPath
     */
    public ExcelUtil(String filePath,String imageTargetPath ){
        this.rootPath=filePath;
        this.imageTargetPath=imageTargetPath;
        readExcel(filePath);
    }

    private void readExcel(String path) {
        try {
            this.rootPath = path;
            // 获取文件输入流
            InputStream inputStream = new FileInputStream(path);
            // 截取路径名 . 后面的后缀名，判断是xls还是xlsx
            String fileType = path.substring(path.lastIndexOf(".") + 1);
            if (fileType.equals("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExcelSheet getSheet(int index, Integer titleRow){
        Sheet sheet = workbook.getSheetAt(index);
        return new ExcelSheet(sheet,titleRow,rootPath,imageTargetPath);
    }
}