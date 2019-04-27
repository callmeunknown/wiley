package com.wiley;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WileyTest {
    WebDriver webDriver;
    MainPage mainPage;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 30, 500);

        mainPage = new MainPage(webDriver);


        webDriver.get("https://www.wiley.com/en-us");

        mainPage.btnYes.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.navigation-menu-items.initialized")));
    }


    @Test
    public void checkNavigationMenu() {
        List<WebElement> elements = mainPage.checkMenu();
        for(WebElement el: elements){
            Assert.assertTrue(el.isDisplayed());
            System.out.println(el.getText() + " are displayed in the top menu");
        }
    }


    @Test
    public void checkSubMenu() {
        List<WebElement> elements = mainPage.checkSubMenu();
        System.out.println(elements.size() + " items under resources sub-header");
        System.out.println("Title are: ");
        for(WebElement el: elements){
            Assert.assertTrue(el.isDisplayed());
            System.out.println("- "+ el.getText());
        }
    }


    @Test
    public void checkStudentPage() {
        mainPage.checkStudent();
        System.out.println("Student page is opened");
        Assert.assertTrue(webDriver.getTitle().equals("Students | Wiley"));
        Assert.assertTrue(webDriver.getCurrentUrl().equals("https://www.wiley.com/en-us/students"));
        List<WebElement> elements = mainPage.checkLearnMore();
        for (WebElement el: elements) {
            Assert.assertTrue(el.getAttribute("href").toLowerCase().contains("www.wileyplus.com"));
        }
    }


    @Test
    public void checkEducation() {
        List<WebElement> elements = mainPage.checkPageEducation();
        System.out.println("Education page is opened");
        System.out.println(elements.size() +  " items are displayed");
        for(WebElement el: elements){
            Assert.assertTrue(el.isDisplayed());
            System.out.println("- "+ el.getText());
        }
    }


    @Test
    public void checkLogoWiley() {
        mainPage.checkLogo();
        Assert.assertTrue(webDriver.getCurrentUrl().equals("https://www.wiley.com/en-us"));
    }

    @Test
    public void checkSearchBtn() {
        mainPage.checkSearch();
        Assert.assertTrue(webDriver.getCurrentUrl().equals("https://www.wiley.com/en-us"));
    }


    @Test
    public void cheackSearchArea(){
        mainPage.checkSearchInput();
    }

    @Test
    public void checkSearch() {
        List<WebElement> elements = mainPage.search();
        Assert.assertTrue(elements.size()==10);
        for (WebElement el: elements) {
            Assert.assertTrue(el.getText().toLowerCase().contains("java"));
        }
    }

//    @Test
//    public void checkSearchAgain() {
//        mainPage.searchAgain();
//        for (int i= mainPage.results.size(); i > 0; i++) {
//            Assert.assertTrue(mainPage.results.get(i).getText().toLowerCase().equals(mainPage.resultsAgain.get(i).getText().toLowerCase()));
//        }
//        for (int i= 0; i < mainPage.results.size(); i++) {
//            System.out.println(mainPage.results.get(i).getText().toLowerCase().equals(mainPage.resultsAgain.get(i).getText().toLowerCase()));
//        }
//    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
