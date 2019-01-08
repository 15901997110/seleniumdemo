package 浏览器相关操作;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: luqiwei
 * @Date: 2019/1/7 19:11
 */
public class OperateElement {
    WebDriver driver;

    @BeforeClass
    public void init() {
        //ChromeOptions chromeOptions= new ChromeOptions();
        //使用当前用户的配置信息（包括书签、扩展程序、代理设置等）,firefox的话,相应的配置firefox profile来设置
        //chromeOptions.addArguments("user-data-dir=C:\\Users\\luqiwei\\AppData\\Local\\Google\\Chrome\\User Data");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "C:\\Users\\luqiwei\\OneDrive\\Selenium\\webdriver2.0\\chromedriver.exe");
        //driver=new ChromeDriver(chromeOptions);
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    /**
     * 模拟键盘操作复制粘贴
     */
    @Test
    public void sendkeysTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\selenuimPage.html");
        WebElement text1 = driver.findElement(By.xpath("//*[@id=\"text1\"]"));
        WebElement text2 = driver.findElement(By.xpath("//*[@id=\"text2\"]"));
        text1.sendKeys("hello world");//输入内容
        text1.sendKeys(Keys.CONTROL, "a");//全选
        text1.sendKeys(Keys.CONTROL, "c");//复制
        Thread.sleep(2 * 1000);
        text2.sendKeys(Keys.CONTROL, "v");//粘贴剪贴板上的内容到text2输入框
        Thread.sleep(2 * 1000);
    }

    /**
     * 下拉框的相关操作
     */
    @Test
    public void selectTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\selenuimPage.html");
        WebElement selectEle = driver.findElement(By.xpath("//*[@id=\"mySelect\"]"));

        Select select = new Select(selectEle);
        Thread.sleep(2000);
        select.selectByVisibleText("上海");
        Thread.sleep(2000);
        select.selectByValue("3");
        Thread.sleep(2000);
        select.selectByIndex(0);
        Thread.sleep(2000);
    }

    /**
     * 模拟上传操作
     */
    @Test
    public void fileUploadTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\selenuimPage.html");
        WebElement fileUpload = driver.findElement(By.xpath("//*[@id=\"fileInput\"]"));
        fileUpload.sendKeys("C:\\Users\\luqiwei\\Downloads\\200000277763.pfx");
        Thread.sleep(3000);
    }

    /**
     * 弹出框的处理
     */
    @Test
    public void alertTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\弹出框页面.html");
        Thread.sleep(1000);
        Alert alert1 = driver.switchTo().alert();//原生 confirm确认框
        System.out.println(alert1.getText());
        //alert1.accept();//确定
        alert1.dismiss();//取消
        Thread.sleep(2000);

        Alert alert2 = driver.switchTo().alert();//原生 提示框
        System.out.println(alert2.getText());
        alert2.accept();//警告框只能进行"确定操作"
        Thread.sleep(2000);
    }

    /**
     * 输入弹出框的处理
     */
    @Test
    public void promptTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\弹出框_prompt.html");
        Thread.sleep(1000);
        Alert prompt = driver.switchTo().alert();
        Thread.sleep(1000);
        System.out.println(prompt.getText());
        prompt.sendKeys("输入框中输入的内容!!!!");

        prompt.accept();//确定

        Thread.sleep(2000);
    }

    /**
     * frame切换
     */
    @Test
    public void frameTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\iframepage.html");

        Thread.sleep(1000);

        driver.switchTo().frame("f3");
        driver.findElement(By.id("kw")).sendKeys("hello world");
        driver.findElement(By.id("su")).click();

        driver.switchTo().defaultContent();//回到最外层页面,不管进入了多少层frame,此方法都能够返回到最外层的页面
        Thread.sleep(1000);
        /**
         * 进入多层frame只能一层一层的进
         */
        driver.switchTo().frame("f1");
        driver.switchTo().frame("f2");
        driver.findElement(By.id("kw")).sendKeys("java之父");
        driver.findElement(By.id("su")).click();
        Thread.sleep(2000);
        driver.switchTo().parentFrame().findElement(By.id("innerA")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        driver.switchTo().defaultContent();

        driver.findElement(By.id("text1")).sendKeys("最外层的输入框");
        Thread.sleep(2000);
    }


    /**
     * 在页面上执行javascript
     */
    @Test
    public void jsTest() throws InterruptedException {
        driver.get("C:\\workspace\\seleniumdemo\\src\\main\\resources\\static\\form.html");
        WebElement btn = driver.findElement(By.className("btn"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //指定某个元素执行js代码
        executor.executeScript("$(arguments[0]).click();", btn);
        Thread.sleep(2000);

        //直接在页面执行js代码
        executor.executeScript("$('#inputEmail').val('luqiwei@qq.com');");
        Thread.sleep(2000);
    }

    /**
     * cookie操作
     */
    @Test
    public void cookieTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        Thread.sleep(2 * 1000);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        Thread.sleep(2 * 1000);
        Cookie cookie = new Cookie("BDUSS", "dRM1ZRN0Y4ZE1oZW1wVlhHSS11LUtxZnpZWi1WOWFnUjNKfmoxU0lZQUNoMXBjQVFBQUFBJCQAAAAAAAAAAAEAAADEubEUZW50d2luZTIwMTIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAL6MlwC-jJcUT");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(2 * 1000);
    }

    /**
     * 滚动条操作与焦点获取
     * 针对不再页面范围内的元素的操作
     */
    @Test
    public void scrollTest() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("hello world");

        driver.findElement(By.id("su")).click();
        Thread.sleep(2000);
        WebElement nextPage = driver.findElement(By.linkText("下一页>"));
        //滚动垂直滚动条,直到元素出现
        //element.scrollIntoView(),滚动垂直滚动条,直到元素上边沿与窗口上部分重合
        //element.scrollIntoView(true)与element.scrollIntoView()效果相同,缺省时就是true参数生效
        //element.scrollIntoView(false),滚动垂直滚动条,直到元素的下边沿与窗口的下边界重合
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",nextPage);

        /**
         * 使元素获得焦点,此方法也可以自动将元素置于页面可见位置
         */
        ((JavascriptExecutor)driver).executeScript("arguments[0].focus();",nextPage);

        Thread.sleep(2000);
        nextPage.click();
        Thread.sleep(2000);
    }
}
