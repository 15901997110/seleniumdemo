package 浏览器相关操作;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/7 13:13
 */
public class BrowserTest {

    WebDriver driver;
    /**
     * 打开浏览器
     */
    @BeforeClass
    public void init(){
        /**
         * firefox
         */
        //WebDriver driver=new FirefoxDriver();

        /**
         * ie
         */
        /*System.setProperty("webdriver.ie.driver","C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\IEDriverServer.exe");
        WebDriver driver=new InternetExplorerDriver();*/

        /**
         * chrome
         */
        System.setProperty("webdriver.chrome.driver","C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\chromedriver.exe");
        driver=new ChromeDriver();
    }

    @AfterClass
    public void afterClass(){
        //close关闭浏览器 ,关闭的是一个窗口(一个tab页)

        //driver.close();

        //quit关闭整个浏览器(如果存在多个窗口,则全部被关闭)
        driver.quit();
    }

    /**
     * 访问链接
     */
    @Test
    public void toLink() throws InterruptedException {

        /**
         * get访问链接,相当于人在浏览器地址栏中输入url回车访问(推荐使用此方法,1.与实际测试场景更相似,2.效率更高?)
         */
        //driver.get("http://www.baidu.com");

        /**
         * navigate.to,相当于调用浏览器的底层,从内部驱动访问一个地址
         */
        driver.navigate().to("http://www.baidu.com");
        System.out.println(driver.getTitle());//获取Title
        System.out.println(driver.getCurrentUrl());//获取url地址

        driver.navigate().to("http://www.sina.com");
        Thread.sleep(1000);

        driver.navigate().back();//后退
        Thread.sleep(1000);

        driver.navigate().forward();//前进
        Thread.sleep(1000);
        //driver.manage().window().setSize(new Dimension(600,800));//设置浏览器大小


    }
}
