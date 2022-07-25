package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waitLong;
    protected WebDriverWait waitShort;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void load(String endPoint, String site) {
        driver.get(ConfigLoader.getInstance().getBaseUrl(site) + endPoint);
    }

    public WebElement getElement(By element) {
        return waitLong.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
