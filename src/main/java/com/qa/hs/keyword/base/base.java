package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class base {
	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\moksha.nagesh\\Downloads\\"
					+ "chromedriver_win32 (1)\\chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver();
			}

		}
		return driver;
	}
	
	public Properties init_properties() throws IOException {
		prop=new Properties();
		FileInputStream ip=new FileInputStream("C:\\Users\\moksha.nagesh\\Downloads\\Kdt\\src"
				+ "\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
		prop.load(ip);
		return prop;
		
	}

}
