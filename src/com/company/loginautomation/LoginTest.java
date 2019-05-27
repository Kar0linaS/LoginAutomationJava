package com.company.loginautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static final String WEB_PAGE = "https://courses.ultimateqa.com/users/sign_in";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");

        WebDriver browser = new ChromeDriver();
        browser.manage().window().fullscreen();

        browser.get(WEB_PAGE);

        waitAMoment(2);

        //Test invalid email and password
        insertCredentials(browser, "AlaMaKota@gmail.com", "sdfndsffga");
        clickLogin(browser);
        waitAMoment(2);
        verifyLoggedIn(browser);

        //Test invalid email
        insertCredentials(browser, "AlaMaKota@gmail.com", "alamakota");
        clickLogin(browser);
        waitAMoment(2);
        verifyLoggedIn(browser);

        //Test invalid password
        insertCredentials(browser, "karolina.szczepek92@gmail.com", "sdfndsffga");
        clickLogin(browser);
        waitAMoment(2);
        verifyLoggedIn(browser);

        //Test valid credentials
        insertCredentials(browser, "karolina.szczepek92@gmail.com", "alamakota");
        clickLogin(browser);
        waitAMoment(3);
        verifyLoggedIn(browser);

        waitAMoment(5);

        browser.quit();
    }

    private static void insertCredentials(WebDriver browser, String login, String password) {
        browser.findElement(By.id("user_email")).sendKeys(login);
        browser.findElement(By.id("user_password")).sendKeys(password);
    }

    private static void clickLogin(WebDriver browser) {
        browser.findElement(By.id("btn-signin")).click();
    }

    private static void verifyLoggedIn(WebDriver browser) {
        try {
            browser.findElement(By.id("my_account"));
            System.out.println("User logged in");
        } catch (NoSuchElementException e) {
            System.out.println("User not logged in");
        }
    }

    private static void waitAMoment(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
