package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class IHerbPage extends BasePage {

    private final By searchFld = By.id("txtSearch");
    private final By searchBtn = By.id("searchBtn");
    public IHerbPage(WebDriver driver) {
        super(driver);
    }

    public IHerbPage load() {
        load("/","iHerb");
        return this;
    }

    public IHerbPage enterTextInSearchFld(String txt) {
        WebElement e = waitLong.until(ExpectedConditions.presenceOfElementLocated(searchFld));
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    public IHerbPage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    public IHerbPage clickSearchBtn() {
        driver.findElement(searchBtn).click();
        return this;
    }
}
