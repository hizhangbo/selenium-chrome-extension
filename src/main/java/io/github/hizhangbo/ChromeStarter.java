package io.github.hizhangbo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeStarter {

    public static WebDriver webDriver;
    //    public static String  baseUrl = "chrome://extensions";
    public static String baseUrl = "https://www.douyu.com";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src\\main\\resources\\extensions\\screenity-2.4.6.crx"));
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();

        /** 打开指定的网站 */
        webDriver.get(baseUrl);
//        webDriver.findElement(By.id("knob")).click();

//        WebElement inputBox = webDriver.findElement(By.id("kw"));
//        inputBox.sendKeys("CSDN");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.postMessage('clicked_browser_action', '*')");
        Thread.sleep(3000000);

        /** 关闭浏览器 */
        webDriver.quit();
    }
}
