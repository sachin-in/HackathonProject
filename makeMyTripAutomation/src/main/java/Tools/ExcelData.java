package Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        
}	
        

