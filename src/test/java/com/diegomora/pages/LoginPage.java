package com.diegomora.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);  // Espera de hasta 30 segundos
    }

    // Método para cerrar el pop-up si está presente
    public void closePopUp() {
        try {
            MobileElement popUpCloseButton = driver.findElementByAccessibilityId("Close sheet");
            if (popUpCloseButton.isDisplayed()) {
                popUpCloseButton.click();
                System.out.println("Se hizo clic en el botón para cerrar el pop-up.");
            }
        } catch (Exception e) {
            System.out.println("El pop-up no está presente.");
        }
    }

    // Método para hacer clic en el texto 'Log in' usando coordenadas
    public void clickLogIn() {
        try {
            MobileElement logInText = (MobileElement) wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@resource-id='com.twitter.android:id/detail_text' and contains(@text, 'Log in')]")));

            if (logInText.isDisplayed()) {
                // Obtener las coordenadas del elemento
                int startX = logInText.getLocation().getX();
                int startY = logInText.getLocation().getY();

                // Realizar el clic en las coordenadas usando TouchAction
                TouchAction<?> action = new TouchAction<>(driver);
                action.tap(TapOptions.tapOptions().withPosition(PointOption.point(startX, startY))).perform();

                System.out.println("Se hizo clic en el texto 'Log in' mediante coordenadas.");
                Thread.sleep(5000);  // Pausa para esperar el cambio de pantalla
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar el texto 'Log in'.");
        }
    }

    // Método para verificar si la página de inicio de sesión está cargada
    public void verifyLogInPageAndEnterEmail() {
        try {
            // Pausa adicional para esperar a que la página se cargue completamente
            Thread.sleep(5000);  // Espera 5 segundos después de hacer clic en "Log in"

            // Luego buscar el campo de correo electrónico
            MobileElement emailField = (MobileElement) wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("com.twitter.android:id/ocf_text_input_edit")));
            if (emailField.isDisplayed()) {
                System.out.println("La página de inicio de sesión está presente.");
                emailField.sendKeys("prueba@seek.com");  // Ingresar el correo de prueba
                System.out.println("Se ingresó el correo 'prueba@seek.com'.");
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar el campo de correo en la página de inicio de sesión.");
        }
    }
}
