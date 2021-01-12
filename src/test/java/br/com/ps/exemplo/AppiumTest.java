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

public class AppiumTest {

        private AndroidDriver<MobileElement> driver;

        @BeforeEach //antes de fazer qualquer teste é necessário realizar um setup
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
            desiredCapabilities.setCapability("appPackage", "br.com.uol.ps.myaccount");
            desiredCapabilities.setCapability("appActivity", "br.com.uol.ps.myaccount.main.MainActivity");

            //o driver que carrega as capabilities
            driver = new AndroidDriver<MobileElement>(service.getUrl(), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }

        @Test
        void testeUm(){

            driver.findElement(By.xpath("//*[contains(@text, 'JÁ SOU CLIENTE')]")).click();
            driver.findElement(By.id("br.com.uol.ps.myaccount:id/input_email")).click();
            driver.getKeyboard().sendKeys("teste-saratov@saratov.com");
            driver.hideKeyboard();
            driver.findElement(By.id("br.com.uol.ps.myaccount:id/input_password")).click();
            driver.getKeyboard().sendKeys("ps654321");
            driver.hideKeyboard();
            driver.findElement(MobileBy.xpath("//*[contains(@text, 'ENTRAR')]")).click();
            Assertions.assertTrue(driver.findElements(By.id("br.com.uol.ps.myaccount:id/btn_continue_pin_register")).size() > 0);

        }

    }
