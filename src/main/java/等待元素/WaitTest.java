package 等待元素;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/8 13:57
 */
public class WaitTest {
    WebDriver driver;

    @BeforeClass
    public void init() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void waitTest1() throws InterruptedException {

        /**
         * 全局超时设置
         */
        //隐式等待,元素最多超过10s还未找到,则抛出异常
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //页面加载等待
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        //执行javascript的超时设置
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\form.html");

        /**
         * 最传统写法,until中实现Function,或者Predicate函数
         */
        //new WebDriverWait(driver, 5).until((ExpectedCondition<Boolean>) dr -> dr.findElement(By.id("inputEmail")).isDisplayed());


        WebDriverWait wait=new WebDriverWait(driver,10);
        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("inputEmail"))));
        inputEmail.sendKeys("luqiwei@qq.com");
        Thread.sleep(3000);

    }
}
