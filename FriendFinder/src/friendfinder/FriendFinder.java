/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package friendfinder;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author SIHUL
 */
public class FriendFinder {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{ 
        getUserName();
    }

    public static void getUserName() throws Exception{
        try {
            System.setProperty("webdriver.chrome.driver" , "C:\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
            driver.get("https://www.facebook.com/");
            
            WebElement username = driver.findElement(By.name("email"));
            WebElement pass = driver.findElement(By.name("pass"));
            WebElement btn = driver.findElement(By.id("loginbutton"));
            
            username.sendKeys("sihultibeb@gmail.com");
            pass.sendKeys("stkzion09");
            btn.click();
            
// FIND AND CLICK THE PROFILE LINK(xpath of profile link)
             try {
            Thread.sleep(5000);   
         } catch (Exception e) {
        } 
        
          driver.findElement(By.xpath("//*[@id=\"u_0_4\"]/div[1]/div[1]/div/a")).click();
           
            
// FIND AND CLICK THE FRIENDS LINK(xpath of friends link)
            try {
            Thread.sleep(5000);   
         } catch (Exception e) {
        } 
            driver.findElement(By.xpath("//a[text()='Friends']")).click();
            
//GETTING TOTAL NO OF FRIENDS AND PRINTING OUT(xpath of the class containig all the friends list)
// WRITING TO EXCEL FILE
            
            
            File src = new File("C:\\Users\\SIHUL\\Desktop\\VNV\\FriendFinder\\friends.xlsx");
            FileInputStream filein = new FileInputStream(src);
            XSSFWorkbook workBook = new XSSFWorkbook(filein);
            XSSFSheet sheet = workBook.getSheetAt(0);
            
            while(true){
                List<WebElement> friendsBeforeScroll = driver.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
                System.out.println("total friends" + friendsBeforeScroll.size() );
               
                for(WebElement f : friendsBeforeScroll){
                System.out.println(f.getText());
                }
//SCROLL TO THE END     
                
                  
                WebElement lastFriend = friendsBeforeScroll.get(friendsBeforeScroll.size()-1);
//                System.out.println(lastFriend);
                int y = lastFriend.getLocation().y;
               
                 

                JavascriptExecutor js;
                js = (JavascriptExecutor)driver ;
                js.executeScript("window.scrollTo(0,"+y+")");
                Thread.sleep(4000);  

                  
                 List<WebElement> friendsAfterScroll = driver.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
                 if(friendsAfterScroll.size() == friendsBeforeScroll.size()){
                     for(WebElement e: friendsBeforeScroll){
                        for(int i=0; i<friendsAfterScroll.size()-1;i++){
                           sheet.getRow(0).createCell(0).setCellValue(e.getText()); 
                        }
                    
                }
                     break;
                     
                   }
                              }
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
      }


    }   
}
