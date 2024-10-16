package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class OpenTwitterAppTest {
    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setup() throws Exception {
        // Configurar las capacidades para la app de X (ex Twitter)
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");  // Nombre del emulador
        caps.setCapability("platformName", "Android");  // Plataforma Android
        caps.setCapability("appPackage", "com.twitter.android");  // Paquete de la app de X
        caps.setCapability("appActivity", "com.twitter.app.main.MainActivity");
        caps.setCapability("fullReset", false);
        caps.setCapability("noReset", true);
        caps.setCapability("autoLaunch", true);  // Forzar la apertura de la app
        // Conectar con Appium Server
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void openApp() {
        System.out.println("La app de X se ha abierto correctamente.");
    }
}
