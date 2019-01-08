package 模拟鼠标和键盘操作;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/8 16:28
 */
public class ActionTest {
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

    @Test
    public void actionTest() {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\form.html");
        driver.findElement(By.id("inputEmail")).sendKeys("lu");
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        WebElement btn = driver.findElement(By.className("btn"));

        Actions actions = new Actions(driver);
        actions.click(btn).perform();//模拟单击
        /*
        actions.doubleClick();//双击
        actions.clickAndHold();//单击不松开
        actions.release();//鼠标释放

        actions.keyDown();//键盘按下
        actions.keyUp();//键盘松开
        actions.sendKeys();//

        actions.moveToElement();//把鼠标移动到元素的中间点位置
        actions.contextClick();//鼠标右击
        actions.dragAndDrop();//拖拽
        */

    }
}
