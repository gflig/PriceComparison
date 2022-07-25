package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.AmazonPage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AmazonPageTest extends BaseTest {

    @Test
    public void openAmazonPage() throws IOException, NoSuchMethodException {
        String searchFor = "betaine pepsin";

        AmazonPage amazonPage = new AmazonPage(getDriver()).
                load().
                search(searchFor).
                //fetchProducts().
                pagination();
    }
}
