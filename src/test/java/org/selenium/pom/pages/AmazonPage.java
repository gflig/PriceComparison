package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AmazonPage extends BasePage {

    private final By searchFld = By.id("twotabsearchtextbox");
    private final By searchBtn = By.id("nav-search-submit-button");
    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    public AmazonPage load() {
        load("/","amazon");
        return this;
    }

    public AmazonPage enterTextInSearchFld(String txt) {
        WebElement e = waitLong.until(ExpectedConditions.presenceOfElementLocated(searchFld));
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    public AmazonPage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    public AmazonPage clickSearchBtn() {
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getCurrentDateTime(String datePart) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObjDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter myFormatObjTime = DateTimeFormatter.ofPattern("HH:mm");
        String currentDate = myDateObj.format(myFormatObjDate);
        String currentTime = myDateObj.format(myFormatObjTime);
        if (datePart == "date") {
            return currentDate;
        } else {
            return currentTime;
        }
    }

    public AmazonPage fetchProducts() throws IOException, NoSuchMethodException {
        System.out.println("Start of fetchProducts function");
        //Wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //List<WebElement> list_of_products = driver.findElements(By.xpath("//div[contains(@class, \"s-result-item s-asin\")]"));
        List<WebElement> list_of_products = waitLong.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, \"s-result-item s-asin\")]")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("list_of_products: " + list_of_products.size());

        String currentDate = getCurrentDateTime("date");
        String currentTime = getCurrentDateTime("time");

//        Prepare the CSV file for writing data into it
        String path = "GuyTestSelenium.csv";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
//        Create a header in CSV file
//        bw.write("Product_Name,Product_Price,Product_Price_Per_Count,Product_Link,Timestamp_Date,Timestamp_Time");
//        bw.newLine();

        for (WebElement product : list_of_products) {
            String product_name = product.findElement(By.xpath(".//span[@class=\"a-size-base-plus a-color-base a-text-normal\"]")).getText();
            product_name = product_name.replace("\"","");
            String product_link = product.findElement(By.xpath(".//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"]")).getAttribute("href");
            String product_price_whole = "0";
            String product_price_fraction = "0";
            String product_price = "0.0";
            String product_price_per_count = "0";
            try {
                product_price_whole = product.findElement(By.xpath(".//span[@class=\"a-price-whole\"]")).getText();
                product_price_whole = product_price_whole.replace(",", "");
                product_price_fraction = product.findElement(By.xpath(".//span[@class=\"a-price-fraction\"]")).getText();
                product_price = product_price_whole + "." + product_price_fraction;
                product_price_per_count = product.findElement(By.xpath(".//span[@class=\"a-size-base a-color-secondary\"]")).getText();
                product_price_per_count = product_price_per_count.
                        replace("(","").
                        replace(")","").
                        replace("$","").
                        replace("/count","");

            } catch(Exception e) {
                //System.out.println("Exception " + product_name + " - " + e);
            }
            if (product_name.toLowerCase().contains("betaine") & product_name.toLowerCase().contains("pepsin")) {
                System.out.println(product_name + ": " + product_price + " " + product_price_per_count + " " + product_link + "," + currentDate + "," + currentTime);
                bw.write("\"" + product_name + "\"," + product_price + "," + product_price_per_count + "," + product_link + "," + currentDate + "," + currentTime);
                bw.newLine();
            }
        }
        bw.close();
        System.out.println("End of fetchProducts function");
        return this;
    }

    public AmazonPage pagination() throws IOException, NoSuchMethodException {


        boolean next_page_visible = true;
        while (next_page_visible) {
            try {
                //Wait for 10 seconds
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                String current_page = driver.findElement(By.xpath(".//span[@class=\"s-pagination-item s-pagination-selected\"]")).getText();
                System.out.println("Fetching data from page " + current_page);
                fetchProducts();
                driver.findElement(By.xpath("//a[contains(@aria-label, \"Go to next page\")]")).click();
            } catch (Exception e) {
                next_page_visible = false;
            }
        }
/*
        //String next_page = driver.findElement(By.xpath("//a[@aria-label='Go to next page, page 2']")).getText();
        System.out.println("Start Fetch 1");
        fetchProducts();
        System.out.println("End Fetch 1");

        driver.findElement(By.xpath("//a[@aria-label='Go to next page, page 2']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Fetch 2");
        fetchProducts();
        System.out.println("End Fetch 2");

        driver.findElement(By.xpath("//a[@aria-label='Go to next page, page 3']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Fetch 3");
        fetchProducts();
        System.out.println("End Fetch 3");

        driver.findElement(By.xpath("//a[@aria-label='Go to next page, page 4']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Fetch 4");
        fetchProducts();
        System.out.println("End Fetch 4");

        driver.findElement(By.xpath("//a[@aria-label='Go to next page, page 5']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Fetch 5");
        fetchProducts();
        System.out.println("End Fetch 5");*/
        return this;
    }
}
