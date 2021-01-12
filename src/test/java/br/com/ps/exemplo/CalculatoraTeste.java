package br.com.ps.exemplo;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class CalculatoraTeste {

    private AndroidDriver<MobileElement> driver;

    @BeforeEach
        //antes de fazer qualquer teste é necessário realizar um setup
    void setUp() {

        //inicializar o appium
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();

        //propriedades
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        //o driver que carrega as capabilities
        driver = new AndroidDriver<MobileElement>(service.getUrl(), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    void testeCalculadora(){

        //codigo que vai realizar o teste
        driver.findElement(By.id("com.android.calculator2:id/digit_2")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_6")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_4")).click();
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
        Assertions.assertEquals("30",driver.findElement(By.id("com.android.calculator2:id/result")).getText());
        driver.findElement(By.id("com.android.calculator2:id/op_div")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_3")).click();
        driver.findElement(By.id("com.android.calculator2:id/result")).click();
        Assertions.assertEquals("10",driver.findElement(By.id("com.android.calculator2:id/result")).getText());

        driver.quit();

    }

}
