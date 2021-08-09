package com.aisino.tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 









import jxl.Sheet;
import jxl.Workbook;
import jxl.common.Logger;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelOperator {
	    private static List<String> title;
	    private static String sheetName;
	    private static WritableWorkbook book = null;
	    private static WritableSheet sheet = null;
	    private static Logger logger = Logger.getLogger(ExcelOperator.class);
	    private static File file=null;
	    /*
	     * titleName[0]表示Excel文件的名字
	     * titleName后面的值是Excel第一行要填入的值
	     */
	    public static void  CreatExcel(String testRoot,List<String> titlenames){
	        sheetName = "bigdata";
	        file = new File(testRoot); 
	      /*  List<String> temp = new ArrayList<String>();
	        for (String s : titleName) {
	            temp.add(s);
	        }
	        temp.remove(0);
	        titleName = (String[]) temp.toArray(new String[temp.size()]);*/
	        create(titlenames);
	    }
	 
	    private  static void  create(List<String> titleNames) {
	        title = titleNames;
	        try {
	        	if(file.exists()){
	        		Workbook wb = Workbook.getWorkbook(file);
	        		book=Workbook.createWorkbook(file,wb);
	        		
	        	}else{
	        		book= Workbook.createWorkbook(file);	
	        	}
	            sheet = addTitle();
	 
	        } catch (Exception e) {
	            logger.debug("CreateWorkbook failed !");
	            e.printStackTrace();
	        }
	    }
	 
	    public static void addDataToExcel(List<String> data) {
	 
	        try {
	            Workbook wb = Workbook.getWorkbook(file);
	 
	            book = Workbook.createWorkbook(file, wb);
	            sheet = book.getSheet(sheetName);
	            int length = sheet.getColumns();
	            logger.debug("-------列长度"+length);
	            for (int i = 0; i < data.size(); i++) {
	                Label label = new Label(length,i , data.get(i));
	                sheet.addCell(label);
	            }
	            book.write();
	            book.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    private static WritableSheet addTitle() {
	 
	        String nameStrs = "";
	        String[] sheetNames = book.getSheetNames();
	        for (String sheetName : sheetNames) {
	            nameStrs += sheetName;
	        }
	        try {
	            if (nameStrs.contains(sheetName))
	                sheet = book.getSheet(sheetName);
	            else {
	            	/*
	            	 * 添加列名
	            	 */
	                sheet = book.createSheet(sheetName, 0);
	                sheet.getSettings().setDefaultColumnWidth(25);
	 
	                for (int i = 0; i < title.size(); i++) {
	                    sheet.addCell(new Label(0, i, title.get(i)));
	                }
	            }
	            book.write();
	            book.close();
	        } catch (Exception e) {
	        }
	        return sheet;
	    }
	 public static List readExcel(String path){
		   File file=new File(path);
		 try {
			InputStream issInputStream=new FileInputStream(file.getAbsoluteFile());
			//jxl提供的Workbook类
			Workbook wb=Workbook.getWorkbook(file);
			//得到sheet页数
			int sheetsize=wb.getNumberOfSheets();
			for(int index=0;index<sheetsize;index++){
				List outerList=new ArrayList();
				//每个页创建一个sheet对象
				Sheet sheet=wb.getSheet(index);
				for(int i=0;i<sheet.getRows();i++){
					List innerList=new ArrayList();
					//返回该页的总列数
					for(int j=0;j<sheet.getColumns();j++){
						String cellinfoString=sheet.getCell(j, i).getContents().toString();
						innerList.add(cellinfoString);
						System.out.println(cellinfoString);
			
					}
					outerList.add(i,innerList);
					
				}
					return outerList;	
				
				
			}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
 
			return null; 
	 }
	    	    
	    
	}

	
	
	


