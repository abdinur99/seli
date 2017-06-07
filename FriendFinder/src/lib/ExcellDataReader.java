/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author SIHUL
 */
public class ExcellDataReader {
//GLOBAL VARIABLES
    
    XSSFWorkbook workBook;
    XSSFSheet sheet;
    File src;
   
// IMPORTING THE FILE
    public ExcellDataReader(String filePath) {       
        try {
            File src = new File(filePath);
            FileInputStream file = new FileInputStream(src);
            workBook = new  XSSFWorkbook(file);
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        } 
    }
    
// PRINTING OUT THE DATA
    public String getData( int sheetNumber, int row , int column){
      sheet = workBook.getSheetAt( sheetNumber);
      String data =   sheet.getRow(row).getCell(column).getStringCellValue();
       System.out.println(data);
       return data;      
    }
//WRITING TO EXCELL FILE

    public void writeData(int sheetNumber,int row, int column, String text){
        try {
            
            
            sheet = workBook.getSheetAt(sheetNumber);
            sheet.getRow(row).createCell(column).setCellValue(text);
            try (FileOutputStream fileOut = new FileOutputStream(src)) {
                workBook.write(fileOut);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     
        }
    
    }
        
    
    
    

