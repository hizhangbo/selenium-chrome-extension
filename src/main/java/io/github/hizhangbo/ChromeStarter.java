package io.github.hizhangbo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;

public class ChromeStarter {

    public static WebDriver webDriver;
    //    public static String  baseUrl = "chrome://extensions";
//    public static String baseUrl = "https://apis.baidu.com/store/aladdin/land?cardType=ipSearch";
    public static String baseUrl = "https://www.ipip.net/ip.html";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src\\main\\resources\\extensions\\screenity-2.4.6.crx"));

        // http://www.freeproxylists.net/zh
        // http://free-proxy.cz/zh/proxylist/country/CN/all/ping/all
        // http://httpbin.org/get # 查看原始代理ip
        String proxyServer = String.format("%s:%d", "52.83.192.177", 8889);
//        String proxyServer = String.format("%s:%d", "123.73.209.41", 45603);
//        String proxyServer = String.format("%s:%d", "123.253.36.99", 8080);

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServer);
        proxy.setSslProxy(proxyServer);
//        proxy.setSocksUsername();
//        proxy.setSocksPassword();
        proxy.setAutodetect(false);
        proxy.setProxyType(Proxy.ProxyType.MANUAL);

        options.setCapability(CapabilityType.PROXY, proxy);

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

    public static void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
