package com.diegomora;

import com.diegomora.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppTest {

    private AndroidDriver<MobileElement> driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "15");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.twitter.android");
        caps.setCapability("appActivity", "com.twitter.android.StartActivity");
        caps.setCapability("noReset", false);
        caps.setCapability("newCommandTimeout", 120); // Aumenta el timeout
        caps.setCapability("autoGrantPermissions", true);

        URL appiumServerURL = new URL("http://localhost:4723");
        driver = new AndroidDriver<>(appiumServerURL, caps);

        // Instanciar LoginPage con el driver
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogIn() {
        System.out.println("Iniciando prueba de inicio de sesión.");

        // Cerrar el pop-up si está presente
        loginPage.closePopUp();

        // Intentar hacer clic en "Log in"
        loginPage.clickLogIn();

        // Verificar que estamos en la página de inicio de sesión e ingresar correo electrónico
        loginPage.verifyLogInPageAndEnterEmail();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
