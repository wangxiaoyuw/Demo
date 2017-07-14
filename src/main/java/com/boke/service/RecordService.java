package com.boke.service;

import com.boke.dao.RecordMapper;
import com.boke.pojo.Record;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzy on 2017/7/14.
 */
@Service
public class RecordService {

    @Autowired
    private RecordMapper recordMapper;

    public List<Record> findList() {
        return recordMapper.findList();
    }

    /**
     * 导入文件
     * @param mFile
     * @param rootPath
     * @return
     */
    public List<Record> importFile(MultipartFile mFile, String rootPath) {
        List<Record> recordList = new ArrayList<Record>();
        String filename = mFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());
        String ym = new SimpleDateFormat("yyyy-MM").format(new Date());
        String filePath = "uploadFile/"+ym+filename;
        try{
            File file = new File(rootPath+filePath);
            if (file.exists()){
                file.delete();
                file.mkdirs();
            }else {
                file.mkdirs();
            }
            mFile.transferTo(file);
            if ("xls".equals(suffix) || "XLS".equals(suffix)){
                recordList = importXls(file);
                for (int i=0;i<recordList.size();i++){
                    recordList.get(i);
                    recordMapper.importFile(recordList.get(i));
                }
                //recordMapper.importFile(recordList.get(i));
            }else if ("xlsx".equals(suffix) || "XLSX".equals(suffix)){
                recordList = importXlsx(file);
                for (int i=0;i<recordList.size();i++){
                    recordList.get(i);
                    recordMapper.importFile(recordList.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }

    private List<Record> importXlsx(File file) {
        List<Record> recordList = new ArrayList<Record>();
        InputStream is = null;
        XSSFWorkbook xssfWorkbook = null;
        try{
            is = new FileInputStream(file);
            xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            if (null!=xssfSheet){
                for (int i = 0;i<xssfSheet.getPhysicalNumberOfRows();i++){
                    Record record = new Record();
                    XSSFRow xssfRow = xssfSheet.getRow(i);
                    record.setRname(xssfRow.getCell(0).toString());
                    record.setRecord(xssfRow.getCell(1).toString());
                    recordList.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=xssfWorkbook){
                try {
                    xssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordList;
    }

    /**
     * 解析文件，将文件中数据插入数据库  xls格式
     * @param file
     * @return
     */
    private List<Record> importXls(File file) {

        List<Record> recordList = new ArrayList<Record>();
        InputStream is = null;
        HSSFWorkbook hssfWorkbook = null;
        try{
            is = new FileInputStream(file);
            hssfWorkbook = new HSSFWorkbook(is);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            if (null!=hssfSheet){
                for (int i=0;i<hssfSheet.getPhysicalNumberOfRows();i++){
                    Record record = new Record();
                    HSSFRow hssfRow = hssfSheet.getRow(i);
                    record.setRname(hssfRow.getCell(0).toString());
                    record.setRecord(hssfRow.getCell(1).toString());
                    recordList.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=is){
                try{
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=hssfWorkbook){
                try {
                    hssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordList;
    }

    /**
     * 导出
     * @param response
     * @throws IOException
     */
    public void exportFile(HttpServletResponse response) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        OutputStream os = null;
        XSSFWorkbook xssfWorkbook = null;
        try{
            String fileName = "HHH"+df.format(new Date())+".xlsx";
            os = response.getOutputStream();
            response.reset();

            response.setHeader("Content-disposition","attachment;filename = "+ URLEncoder.encode(fileName,"UTF-8"));
            response.setContentType("application/octet-stream");
            xssfWorkbook = new XSSFWorkbook();
            XSSFSheet xssfSheet = xssfWorkbook.createSheet("RecordList");
            setSheetHeader(xssfWorkbook,xssfSheet);
            setSheetContent(xssfWorkbook,xssfSheet);

            xssfWorkbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=os){
                try{
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=xssfWorkbook){
                xssfWorkbook.close();
            }
        }
    }

    /**
     * 表内容
     * @param xssfWorkbook
     * @param xssfSheet
     */
    private void setSheetContent(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet) {

        List<Record> recordList = recordMapper.findList();
        CellStyle cs= xssfWorkbook.createCellStyle();
        cs.setWrapText(true);

        if (null!= recordList&&recordList.size()>0){
            for (int i = 0;i<recordList.size();i++){
                XSSFRow xssfRow = xssfSheet.createRow(i + 1);
                Record record = recordList.get(i);
                for (int j=0;j<3;j++){
                    XSSFCell xssfCell = xssfRow.createCell(j);
                    xssfCell.setCellStyle(cs);
                    switch (j){
                        case 0:
                            xssfCell.setCellValue(record.getRname());
                            break;
                        case 1:
                            xssfCell.setCellValue(record.getRecord());
                            default:
                                break;
                    }
                }
            }
        }
    }

    /**
     * 设置sheet,表头
     * @param xssfWorkbook
     * @param xssfSheet
     */
    private void setSheetHeader(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet) {

        xssfSheet.setColumnWidth(0,40*256);
        xssfSheet.setColumnWidth(1,40*256);

        CellStyle cs = xssfWorkbook.createCellStyle();
        //设置水平居中
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置字体
        Font headerFont = xssfWorkbook.createFont();
        headerFont.setFontHeightInPoints((short)12);
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontName("宋体");
        cs.setFont(headerFont);
        cs.setWrapText(true);//是否换行

        XSSFRow xssfRow0 = xssfSheet.createRow(0);
        XSSFCell xssfCell0 = xssfRow0.createCell(0);
        xssfCell0.setCellStyle(cs);
        xssfCell0.setCellValue("名称");

        XSSFCell xssfCell1 = xssfRow0.createCell(1);
        xssfCell1.setCellStyle(cs);
        xssfCell1.setCellValue("记录");

    }
}
