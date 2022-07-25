package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.AmazonPage;
import org.selenium.pom.pages.IHerbPage;
import org.testng.annotations.Test;

public class IHerbPageTest extends BaseTest {

    @Test
    public void openIHerbPage(){
        String searchFor = "betaine pepsin";
        IHerbPage iHerbPage = new IHerbPage(getDriver()).
                load().
                search(searchFor);
    }

    public void fetchProducts() {

    }
}
