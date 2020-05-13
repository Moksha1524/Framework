package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hs.keyword.base.base;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	public WebDriverWait wait;

	public static Workbook book;
	public static Sheet sheet;

	public base base1;

	public final String Sheet_path = "C:\\Users\\moksha.nagesh\\Downloads\\Kdt\\src\\main"
			+ "\\java\\com\\qa\\hs\\keyword\\scenarios\\Testcase.xlsx";
	

	public void startExecution(String sheetName) throws IOException {

		FileInputStream file = null;

		try {
			file = new FileInputStream(Sheet_path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {

				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

				switch (action) {
				case "open browser":

					base1 = new base();
					base1.init_properties();
					if (value.isEmpty() || value.equals("NA")) {
						base1.init_driver(prop.getProperty("browser"));
					} else {
						driver = base1.init_driver(value);

					}
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
					} else {
						driver.get(value);

					}
					break;
					
				case "homepage":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("baseurl"));
					} else {
						driver.get(value);

					}
					break;

				case "quit":
					Thread.sleep(1000);
					driver.close();
					break;
				default:
					break;

				}
				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
						Thread.sleep(1000);

					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(1000);
						element.click();
						Thread.sleep(1000);
					}
					locatorType = null;
					break;

				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					element.click();
					System.out.println("sign in done");
					
					 wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='account-text-container col s12 l6 no-pad']")));
					Thread.sleep(10000);
					locatorType = null;
					break;
					
				case "xpath1":
					element =driver.findElement(By.xpath(locatorValue));
					element.click();
					System.out.println("Sign up button verified");
					
					wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Sign Up')]")));
					Thread.sleep(10000);
					locatorType = null;
					break;
				
					default:
						break;

				}
				switch (locatorType) {
				case "xpath":
					element=driver.findElement(By.xpath(locatorValue));
					element.click();
					wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'About')]")));
					Thread.sleep(10000);
					locatorType = null;
					break;
					
				case "xpath2":
					element=driver.findElement(By.xpath(locatorValue));
					element.click();
					wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Overview')]")));
					WebElement slider=driver.findElement(By.xpath("//div[@class='timeline-element slick-slide slick-current slick-center']"));
					for(int m=2;m<=11;m++) {
						
						slider.sendKeys(Keys.ARROW_RIGHT);
						
					}
					for(int n=2;n>=2;n--) {
						slider.sendKeys(Keys.ARROW_LEFT);
					}
					
					Thread.sleep(10000);
					locatorType = null;
					break;
					
					
					default:
						break;
				}
				

			} 
			
			catch (Exception e) {

			}
			
			
		}

	}
}
