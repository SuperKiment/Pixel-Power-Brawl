package com.epsi.epsi_pixel_power_brawl.integ;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.This;

@SpringBootTest
@TestPropertySource("classpath:application.properties") // Ensure properties file is loaded
public class LoginMatchmakingEndToEndTest {

    private WebDriver driver;
    private WebDriverWait wait;
	    
	    @Value("${test.username}")
	    private String username;
	
	    @Value("${test.password}")
	    private String password;
	    
	    @Value("${test.frontendurl}")
	    private String frontEndURL;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginPage() {
        driver.get("http://localhost:4200/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        usernameField.sendKeys(this.username);
        passwordField.sendKeys(this.password);
        loginButton.click();
        
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/team-choosing"));
        
        driver.getCurrentUrl();
        WebElement playButton = driver.findElement(By.id("jouer-button"));
        
        playButton.click();

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("http://localhost:4200/matchmaking");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}