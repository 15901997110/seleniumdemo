package 浏览器相关操作;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/7 15:00
 */
public class ChromeOptionTest {
    WebDriver driver;
    @BeforeClass
    public void init(){
        ChromeOptions chromeOptions= new ChromeOptions();
        //使用当前用户的配置信息（包括书签、扩展程序、代理设置等）,firefox的话,相应的配置firefox profile来设置
        chromeOptions.addArguments("user-data-dir=C:\\Users\\luqiwei\\AppData\\Local\\Google\\Chrome\\User Data");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\chromedriver.exe");
        driver=new ChromeDriver(chromeOptions);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void BaiduTest(){
        driver.get("http://www.baidu.com");
    }
}
