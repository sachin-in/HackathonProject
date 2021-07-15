package Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelData { 
	
	public static String sheetPath;
	public static File file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public static String[][] readData(int cells, String fileName, String sheetName) throws IOException {
		
		
			String[][] data=new String[4][cells];
		
			//getting absolute path of excel file
			String cwd=new File(fileName).getAbsolutePath();
			
			//Locate excel file
			FileInputStream file=new FileInputStream(fileName); 
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			
			//Select excel sheet
			XSSFSheet sheet=workbook.getSheet(sheetName);
			
			//read data
			int j, i;
			for(j=0;j<4;j++)
			{
				
				for(i=0;i<cells;i++)
				{ 
					
					 data[j][i]=String.valueOf(sheet.getRow(j).getCell(i));
					 
				}
				
			}
			workbook.close();
			
			return data;
			
	}
	
//	public static void createSheet(String outputFile) {
//		sheetPath = new File(outputFile).getAbsolutePath();
// 		file = new File(sheetPath);
//
// 		workbook = new XSSFWorkbook();  
// 		sheet = workbook.createSheet("WebPageOutputs");
//	}
	
	 public static void writeData(int rowNumber, int cellNumber, String cellValue) throws Exception {   //Write the Test result to the excel sheet
		 sheetPath = new File("HackathonOutputData.xlsx").getAbsolutePath();
	 		file = new File(sheetPath);

	 		workbook = new XSSFWorkbook();  
	 		sheet = workbook.createSheet("WebPageOutputs");
         
         	sheet.createRow(rowNumber).createCell(cellNumber).setCellValue(cellValue);
         	
         FileOutputStream fos;
         try {
             fos = new FileOutputStream(file);
             workbook.write(fos);
             fos.close();
             workbook.close();
         } catch (FileNotFoundException e) {
             
             e.printStackTrace();
         }
     }
	 
	 //The write Excel code FROM MY MINI PROJECT
	 public static void writeExcelData(List<WebElement> list) throws Exception {   //Write the Test result to the excel sheet
         
	 		String sheetPath = "D:\\Selenium-Java Eclipse\\seleniumTesting\\Sheet.xlsx";
	 		File file = new File(sheetPath);

	 		
	 		XSSFWorkbook workbook = new XSSFWorkbook();  
         XSSFSheet sheet = workbook.createSheet("Sheet");
         for(int i = 0; i<list.size(); i++)  
         {
         	sheet.createRow(i).createCell(0).setCellValue(list.get(i).getText());
         }
         
         FileOutputStream fos;
         try {
             fos = new FileOutputStream(file);
             workbook.write(fos);
             fos.close();
             workbook.close();
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         }
         
        
}	
        

