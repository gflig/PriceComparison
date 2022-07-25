package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.AmazonPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void openAmazonPage(){
        String searchFor = "toe separators";
        AmazonPage amazonPage = new AmazonPage(getDriver()).
                load().
                search(searchFor);
    }

    public void fetchProducts() {

    }
}
