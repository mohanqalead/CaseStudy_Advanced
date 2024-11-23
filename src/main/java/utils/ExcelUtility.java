package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            
            data = new Object[rowCount - 1][colCount];
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = cell.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
	
	static Map<String, Map<String, String>> excelSheetData;
	static Map<String, String> rowData; 
	
	//Method for read a data from excel and stored in a hashmap
	public static Map<String, Map<String, String>> readData(String filePath, String sheetName) throws IOException {
		excelSheetData = new LinkedHashMap<>();		
		File file = new File(filePath);
		try (FileInputStream fis = new FileInputStream(file)) {
			Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(rowCount);
            System.out.println(colCount);
            for(int i=1; i<=colCount-1; i++) {
    			rowData = new HashMap<>();
    			for(int j=0; j<=rowCount-1; j++) {    				
    				rowData.put(getCellValueAsString(sheet.getRow(j).getCell(0)), getCellValueAsString(sheet.getRow(j).getCell(i)));    				
    				
    			}
    			excelSheetData.put(String.valueOf(i), rowData);
    		}
		}catch (IOException e) {
            e.printStackTrace();
        }
		return excelSheetData;
		
	}
	
	public static String getCellValueAsString(Cell cell) {
	    String cellValue = null;
	    switch (cell.getCellType()) {
	        case NUMERIC:
	            cellValue = String.valueOf(cell.getNumericCellValue());
	            break;
	        case STRING:
	            cellValue = cell.getStringCellValue();
	            break;
	        case BOOLEAN:
	            cellValue = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case FORMULA:
	           cellValue= cell.getCellFormula();
	        case BLANK:
	            cellValue="BLANK";
	        default:
	            cellValue ="DEFAULT";
	    }
	    return cellValue;
	}


}

