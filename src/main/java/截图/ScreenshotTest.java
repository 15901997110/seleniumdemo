package 截图;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/8 17:09
 */
public class ScreenshotTest {
    WebDriver driver;

    @BeforeClass
    public void init() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    /**
     * 屏幕截图
     * @throws IOException
     */
    @Test
    public void screenshotTest() throws IOException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\form.html");
        driver.findElement(By.id("inputEmail"));
        File file=new File("D:\\seScreenshot\\se"+System.currentTimeMillis()+".png");
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs,file);
    }
}
