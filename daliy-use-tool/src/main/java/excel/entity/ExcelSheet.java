package excel.entity;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class ExcelSheet {

    /**
     * key :Rid value ImagePath
     */
    private Map<String, String> imageMap = new HashMap<>();
    /**
     * key imageId value Rid
     */
    private Map<String, String> imageIdMappingMap = new HashMap<>();

    private Sheet sheet;

    /**
     * 标题map  key:colIndex ,value: ExcelDataEntry
     */
    private Map<Integer, ExcelDataEntry> sheetTitleMap = new HashMap<>();
    /**
     * 标题所在行
     */
    private Integer titleRow;

    /**
     * 文件跟目录
     */
    private String rootPath = "";
    /**
     * 图片存储目录
     */
    private String imageTargetPath = "";

    /**
     * 行数据
     */
    private List<Map<Integer, ExcelDataEntry>> rowDataList;

    /**
     * title 数据 key:title data colDataList
     */
    private Map<Object, List<ExcelDataEntry>> titleDataListMap;


    /**
     * 构造器指定POI获取的Sheet数据、待解析Excel文件地址、图片存放地址
     *
     * @param sheet           真实sheet
     * @param rootPath        待解析Excel文件地址
     * @param imageTargetPath 图片存放地址
     */
    protected ExcelSheet(Sheet sheet, Integer titleRow, String rootPath, String imageTargetPath) {
        this.sheet = sheet;
        this.rootPath = rootPath;
        this.imageTargetPath = imageTargetPath;
        this.titleRow = titleRow;

        //加载数据
        loadData();
        loadSheetDataTitle(this.titleRow);
    }

    /**
     * 加载数据
     * @param
     */
    private void loadData() {
        try {
            //数据结果集
            List<Map<Integer, ExcelDataEntry>> rowDataList = new ArrayList<>();
            //提取函数ID和图片Rid的映射关系
            ridWithIDRelationShip(this.rootPath);
            //提取Rid和图片压缩文件内部路径的映射关系
            ridWithImagePathRelationShip(this.rootPath);
            //获取浮动图片[key:行号-列号,图片对象]【无需读取压缩文件,直接POI获取】
            Map<String, XSSFPictureData> floatPictureMap = getFloatPictureMap((XSSFSheet) sheet);
            //统计浮动图片中最大的行列
            Map<Integer, Integer> rowPicMaxColIndexMap = getRowPicMaxColIndex(floatPictureMap);
            List<Integer> picRowList = rowPicMaxColIndexMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

            // sheet.getPhysicalNumberOfRows()获取总的行数
            // 循环读取每一行
            int rowIndex = 0;
            for (; ; ) {
                HashMap<Integer, ExcelDataEntry> rowDataItem = new HashMap<>();
                if (rowIndex >= sheet.getPhysicalNumberOfRows() && (CollectionUtil.isEmpty(picRowList) || rowIndex > picRowList.get(0))) {
                    break;
                }
                Row row = sheet.getRow(rowIndex);
                // row.getPhysicalNumberOfCells()获取总的列数
                int colIndex = 0;

                Integer rowPicMaxColIndex = rowPicMaxColIndexMap.get(rowIndex);
                int rowPhysicalNumberOfCells = 0;
                if (row != null) {
                    rowPhysicalNumberOfCells = row.getPhysicalNumberOfCells();
                }
                // 循环读取每一个格
                for (; ; ) {
                    if (colIndex > rowPhysicalNumberOfCells && (rowPicMaxColIndex == null || colIndex > rowPicMaxColIndex)) {
                        break;
                    }
                    ExcelDataEntry colDataItem = new ExcelDataEntry();
                    colDataItem.setRow(rowIndex);
                    colDataItem.setCol(colIndex);
                    String RCindex = rowIndex + "-" + colIndex;


                    if (rowPhysicalNumberOfCells != 0) {
                        // 获取数据，但是我们获取的cell类型
                        Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if (cell == null) {
                            continue;
                        }
                        //获取数据类型
                        String dataType = getCellDataType(cell);
                        // 转换为字符串类型
                        Object currentData = getCellData(cell);
                        // 获取得到字符串
//                        String currentData = cell.getStringCellValue();
                        if ("FORMULA".equals(dataType)) {
                            if (currentData.toString().contains("DISPIMG")) {
                                String imageId = subImageId(currentData.toString());
                                String picPath = getImplantPicById(imageId);
                                InputStream picInputStream = openFile("xl/" + picPath);
                                File pic = saveFile(picInputStream, this.imageTargetPath + "/" + getFileRealName(picPath));
                                colDataItem.setData(pic);
                            }
                        } else if (currentData != null) {
                            colDataItem.setData(currentData);
                        }

                    }
                    XSSFPictureData floatPicture = floatPictureMap.get(RCindex);
                    if (floatPicture != null) {
                        InputStream picInputStream = floatPicture.getPackagePart().getInputStream();
                        File pic = saveFile(picInputStream, this.imageTargetPath + "/" + getFileRealName(floatPicture.getPackagePart().getPartName().toString()));
                        colDataItem.setData(pic);
                    }

                    if (colDataItem.getData() != null) {
                        rowDataItem.put(colIndex, colDataItem);
                    }
                    colIndex++;
                }
                rowDataList.add(rowDataItem);
                rowIndex++;
            }
            this.rowDataList = rowDataList;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Object getCellData(Cell cell) {
        CellType cellType = cell.getCellType();
        Object value = null;
        switch (cellType) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        return value;
    }

    private String getCellDataType(Cell cell) {

        CellType cellType = cell.getCellType();
        String type = null;
        switch (cellType) {
            case STRING:
                type = "STRING";
                break;
            case NUMERIC:
                type = "NUMERIC";
                break;
            case BOOLEAN:
                type = "BOOLEAN";
                break;
            case FORMULA:
                type = "FORMULA";
                break;
            default:
                type = "STRING";
        }
        return type;
    }

    private String getFileRealName(String fileName) {
        try {
            fileName.replace("\\", "/");
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        } catch (Exception e) {
            return fileName;
        }
        return fileName;
    }

    /**
     * 遍历压缩文件内部的xl/cellimages.xml,根基函数ID:[name]【EG:ID_E828CA3CD96D410ABEA2658ECD97E4EF】
     * 获取Rid:[r:embed],存到映射imageIdMappingMap中
     * @param
     */
    private void ridWithIDRelationShip(String path) throws IOException {
        InputStream inputStream = null;
        try {

            //读取关系xml文件
            inputStream = openFile("xl/cellimages.xml");
            // 创建SAXReader对象
            SAXReader reader = new SAXReader();
            // 加载xml文件
            Document dc = reader.read(inputStream);
            // 获取根节点
            Element rootElement = dc.getRootElement();
            //获取子节点 每一个图片节点
            List<Element> cellImageList = rootElement.elements();

            //循环处理每一个图片
            for (Element cellImage : cellImageList) {
                Element pic = cellImage.element("pic");
                Element nvPicPr = pic.element("nvPicPr");
                Element cNvPr = nvPicPr.element("cNvPr");
                //图片id
                String imageId = cNvPr.attribute("name").getValue().replace("ID_", "");
                Element blipFill = pic.element("blipFill");
                Element blip = blipFill.element("blip");
                //图片Rid
                String imageRid = blip.attribute("embed").getValue();
                //存入map中
                imageIdMappingMap.put(imageId, imageRid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private InputStream openFile(String filePath) {
        try {

            File file = new File(rootPath);
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));

            ZipEntry nextEntry = null;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                String name = nextEntry.getName();
                if (name.equalsIgnoreCase(filePath)) {
                    return zipFile.getInputStream(nextEntry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取xl/_rels/cellimages.xml.rels文件中Rid:[id]
     * 对应内部存储图片路径paht:[Target]存储到映射关系imageMap中
     * @param
     */
    private void ridWithImagePathRelationShip(String path) throws IOException {
        InputStream inputStream = null;
        try {
            //读取关系文件
            inputStream = openFile("xl/_rels/cellimages.xml.rels");
            // 创建SAXReader对象
            SAXReader reader = new SAXReader();
            // 加载xml文件
            Document dc = reader.read(inputStream);
            // 获取根节点
            Element rootElement = dc.getRootElement();

            List<Element> imageRelationshipList = rootElement.elements();

            //处理每个关系
            for (Element imageRelationship : imageRelationshipList) {
                String imageRid = imageRelationship.attribute("Id").getValue();
                String imagePath = imageRelationship.attribute("Target").getValue();
                this.imageMap.put(imageRid, imagePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    /**
     * @param
     */
    private File saveFile(byte[] bytes, String fileName) throws IOException {
        FileOutputStream fos = null;
        try {
            File outputFile = new File(fileName);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(fileName);
            fos.write(bytes);
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return null;
    }

    /**
     * @param
     */
    private String subImageId(String imageId) {
        return imageId.substring(imageId.indexOf("ID_") + 3, imageId.lastIndexOf("\""));
    }


    /**
     * @param sheet
     */
    private Map<String, HSSFPictureData> getFloatPictureMap(HSSFSheet sheet) throws IOException {
        Map<String, HSSFPictureData> map = new HashMap<String, HSSFPictureData>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = picture.getClientAnchor();
                HSSFPictureData pdata = picture.getPictureData();
                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
                map.put(key, pdata);
            }
        }
        return map;
    }


    /**
     * @param sheet
     */
    private Map<String, XSSFPictureData> getFloatPictureMap(XSSFSheet sheet) throws IOException {
        Map<String, XSSFPictureData> map = new HashMap<String, XSSFPictureData>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    String key = marker.getRow() + "-" + marker.getCol();
                    map.put(key, picture.getPictureData());
                }
            }
        }
        return map;
    }

    /**
     * @param floatPicMap
     */
    private Map<Integer, Integer> getRowPicMaxColIndex(Map<String, XSSFPictureData> floatPicMap) {
        //获取全部图片的行列信息
        Set<String> rowWithColStringSet = floatPicMap.keySet();
        Map<Integer, Integer> rowPicMaxColMap = new HashMap<>();
        for (String rowWithColString : rowWithColStringSet) {
            String[] rowWithColArray = rowWithColString.split("-");
            //图片所在行
            Integer row = Integer.parseInt(rowWithColArray[0]);
            //图片所在列
            Integer col = Integer.parseInt(rowWithColArray[1]);
            //如果小就替换
            Integer cacheCol = rowPicMaxColMap.get(row);
            if (cacheCol == null || cacheCol < col) {
                rowPicMaxColMap.put(row, col);
            }
        }
        return rowPicMaxColMap;
    }


    /**
     * @param imageId
     */
    private String getImplantPicById(String imageId) throws IOException {
        String imageRid = imageIdMappingMap.get(imageId);
        String imagePath = imageMap.get(imageRid);
        return imagePath;
    }

    /**
     * 保存文件
     * @param fileName
     * @param inputStream
     */
    private File saveFile(InputStream inputStream, String fileName) throws IOException {
        OutputStream outputStream = null;
        try {
            File outputFile = new File(fileName);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return null;
    }


    /**
     * 获取行数据 包含空行
     * 此方法返回数据索引与Excel 行号一致
     *
     * @return rowDataList
     */
    public List<Map<Integer, ExcelDataEntry>> getRowDataList() {
        return this.rowDataList;
    }

    /**
     * 获取行数据
     * 此方法只获取不为空的行
     *
     * @return rowDataList
     */
    public List<Map<Integer, ExcelDataEntry>> getNotNullRowDataList() {
        return this.rowDataList.stream().filter(CollectionUtil::isNotEmpty).collect(Collectors.toList());
    }

    /**
     * 获取数据 by title 返回一个单元格数据
     *
     * @param titleRow 标题所在行 传空默认第一行有数据的行
     * @param title    标题
     * @param rowIndex 行数
     * @return
     */
    public ExcelDataEntry getDataByTitle(Integer titleRow, Object title, Integer rowIndex) {
        if (this.titleDataListMap == null || this.titleRow != titleRow) {
            loadDataByTitle(titleRow);
        }
        List<ExcelDataEntry> colDataList = titleDataListMap.get(title);
        return colDataList.get(rowIndex);
    }

    /**
     * 通过标题查数据 返回一列数据
     *
     * @param titleRow 标题所在行 传空默认第一行有数据的行
     * @param title    标题
     * @return
     */
    public List<ExcelDataEntry> getDataByTitle(Integer titleRow, Object title) {
        if (this.titleDataListMap == null || this.titleRow != titleRow) {
            loadDataByTitle(titleRow);
        }
        return titleDataListMap.get(title);
    }

    /**
     * @param title
     * @param rowIndex
     * @return
     */
    public ExcelDataEntry getDataByTitle(Object title, Integer rowIndex) {
        return this.getDataByTitle(null, title, rowIndex);
    }

    public List<ExcelDataEntry> getDataByTitle(Object title) {
        return this.getDataByTitle(null, title);
    }

    /**
     * @param titleRow title所在行   传入null 则默认第一个有数据的行
     * @return [test.ExcelSheet]
     * @version [V1.0]
     * @Title: [loadDataByTitle]
     * @author [小鹿同学]
     * @description [加载使用title分组数据]
     * @createTime [2023/9/20 18:00]
     * @updateUser: [小鹿同学]
     * @updateTime: [2023/9/20 18:00]
     * @updateRemark: [描述说明本次修改内容]
     */
    private synchronized void loadDataByTitle(Integer titleRow) {
        if (this.titleDataListMap != null) {
            return;
        }
        this.titleRow = titleRow;
        //获取title数据
        loadSheetDataTitle(titleRow);

        Map<Integer, ExcelDataEntry> sheetDataTitle = this.sheetTitleMap;
        Map<Object, List<ExcelDataEntry>> titleDataListMap = new HashMap<>();
        //循环处理数据
        for (Map<Integer, ExcelDataEntry> rowDataItem : rowDataList) {
            Set<Integer> titleColIndexList = sheetDataTitle.keySet();
            for (Integer titleColIndex : titleColIndexList) {
                Object title = sheetDataTitle.get(titleColIndex).getData();
                ExcelDataEntry colData = rowDataItem.get(titleColIndex);
                List<ExcelDataEntry> titleColDataList = (List<ExcelDataEntry>) titleDataListMap.get(title);
                if (CollectionUtils.isEmpty(titleColDataList)) {
                    titleColDataList = new ArrayList<>();
                }
                titleColDataList.add(colData);
                titleDataListMap.put(title, titleColDataList);
            }
        }
        this.titleDataListMap = titleDataListMap;
    }

    public List<Map<Object, ExcelDataEntry>> getColDataList() {
        List<Map<Object, ExcelDataEntry>> colDataList = new ArrayList<>();
        Map<Integer, ExcelDataEntry> sheetTitleMap = this.sheetTitleMap;
        for (int rowIndex = 0; rowIndex < this.rowDataList.size(); rowIndex++) {
            if (titleRow!=null&&rowIndex == titleRow) {
                continue;
            }
            Map<Integer, ExcelDataEntry> rowData = rowDataList.get(rowIndex);
            Map<Object, ExcelDataEntry> colData = new HashMap<>();
            for (Integer colIndex : sheetTitleMap.keySet()) {
                ExcelDataEntry colTitle = sheetTitleMap.get(colIndex);
                ExcelDataEntry colDataItem = rowData.get(colIndex);
                colData.put(colTitle.getData(), colDataItem);
            }
            colDataList.add(colData);
        }
        return colDataList;
    }

    /**
     * 加载标题
     *
     * @param row 标题所在行 ,传null 默认第一行有数据的
     */
    private void loadSheetDataTitle(Integer row) {
        if (row == null) {
            this.sheetTitleMap = this.getNotNullRowDataList().get(0);
            return;
        }
        this.sheetTitleMap = this.getRowDataList().get(row);
    }
}
