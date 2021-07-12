package Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelData { 
	
	public static String[][] getData(int cells, String fileName, String sheetName) throws IOException {
		
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
					 //System.out.println(data[j][i]);
					 
				}
				
			}
			workbook.close();
			
			return data;
			
	}
	
	public static void writeData(String name , List<String> str ) throws IOException {
		
		File file = new File("C:\\Users\\sachi\\eclipse-workspace\\sample1\\HackathonData.xlsx");  // creating path where to store the excel file 
		
		XSSFWorkbook wb = new XSSFWorkbook(); // creating a workbook 
		
		XSSFSheet sh = wb.createSheet(name); // create a sheet 

		for (int i = 0 ; i<str.size();i++)
		{
			sh.createRow(i).createCell(0).setCellValue(str.get(i));
			
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
