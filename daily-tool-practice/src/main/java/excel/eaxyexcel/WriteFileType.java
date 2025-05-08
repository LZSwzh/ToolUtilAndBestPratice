package excel.eaxyexcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFileType {
    public static void main(String[] args) {
        // 文件格式列表
        String[] fileFormats = {
                "xls", "xlsx", "doc", "docx", "log", "ppt", "pptx", "pdf", "txt",
                "xml", "rmvb", "mp3", "mp4", "video", "png", "bmp", "jpeg", "jpg",
                "zip", "rar", "7z", "gz", "dwg", "gif", "msg", "dxf", "scr", "image",
                "rcfgx", "emf", "eml", "dsn", "brd", "m", "olb", "mdd", "cdr", "v",
                "mov", "nuzip", "vsd", "ofd", "vsdx", "wps", "mat", "bit", "ai", "dat",
                "heic", "et", "cr2", "schdoc", "license", "db", "csv", "ltx", "rtf",
                "module", "tif", "ncp", "z01", "img", "tmp", "mmap", "dcp", "hex",
                "dotx", "xmind", "sldprt", "z02", "scfg", "caj", "tiff", "key", "mkv",
                "rcfg", "xlsb", "mpp", "cs", "jfjf", "kvdat", "z03", "saving", "fpx",
                "xdc", "code", "fig", "z04", "mht", "wmv", "chm", "dotm", "webp",
                "ces", "pacpng", "vh", "psd", "pcb", "yuv", "rpt", "raw", "lic",
                "sldasm", "mts", "pld", "txtnew", "sv", "vhd", "project", "emmx",
                "btw", "tgz", "pos", "gammdat", "nprj", "oscfg", "zipx", "mexw64",
                "ndf", "tar", "mhtml", "rcvbp", "vglcx", "schdot", "sch", "xlk",
                "numbers", "fxsp", "xps", "ico", "temp", "avi", "dmp", "dmg", "obj",
                "otf", "md", "odt", "wef", "vcf", "pages", "mtl", "rp", "srcx", "aor",
                "z08", "z09", "slddrw", "z07", "z06", "z05", "gamdat", "ipa", "z10",
                "dfu", "3gp", "easm", "dsmlck", "crdownload", "tim", "mtsp"
        };

        System.out.println(fileFormats.length);
//        // Excel 文件路径
//        String excelFilePath = "file_formats.xlsx";
//
//        // 创建工作簿
//        Workbook workbook = new XSSFWorkbook();
//
//        // 创建工作表
//        Sheet sheet = workbook.createSheet("File Formats");
//
//        // 创建表头
//        Row headerRow = sheet.createRow(0);
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("File Formats");
//
//        // 写入文件格式数据
//        for (int i = 0; i < fileFormats.length; i++) {
//            Row row = sheet.createRow(i + 1);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(fileFormats[i]);
//        }
//
//        // 自动调整列宽
//        sheet.autoSizeColumn(0);
//
//        // 写入 Excel 文件
//        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
//            workbook.write(fileOut);
//        } catch (IOException e) {
//            System.out.println("写入 Excel 文件时出错: " + e.getMessage());
//        }
//
//        // 关闭工作簿
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            System.out.println("关闭工作簿时出错: " + e.getMessage());
//        }
//
//        System.out.println("文件格式已成功写入到: " + excelFilePath);
    }
}
