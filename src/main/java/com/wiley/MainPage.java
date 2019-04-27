package com.wiley;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    public List<WebElement> results;
    public List<WebElement> resultsAgain;

    @FindBy(css = "ul.navigation-menu-items.initialized")
    WebElement navigationMenu;

    @FindBy(css = "button.changeLocationConfirmBtn.button.large.button-teal")
    WebElement btnYes;

    @FindBy (css = "ul.dropdown-items.ps-container.ps-theme-default")
    WebElement dropDownMenu;

    @FindBy(css = "body > main > div.main-page-container.container-fluid.partnership-solution-page > div > div.container.page-content-wrapper > div > p:nth-child(9) > a > span > span")
    WebElement btnLearnMOre;

    @FindBy(css = "div.content.cke-content")
    WebElement bodyStudent;

    @FindBy(css = "div.side-panel")
    WebElement leftMenuPageEducation;

    @FindBy(css = "ul.dropdown-items.ps-container.ps-theme-default.ps-active-y")
    WebElement getDropDownMenuSubjects;

    @FindBy(xpath = "//*[@id=\"main-header-container\"]/div/div[1]/div/div/div/a/img")
    WebElement logoWiley;

    @FindBy(xpath = "//*[@id=\"main-header-container\"]/div/div[2]/div/form/div/span/button")
    WebElement btnSearch;

    @FindBy(xpath = "//*[@id=\"js-site-search-input\"]")
    WebElement inputSearch;

    @FindBy(xpath = "//*[@id=\"ui-id-2\"]")
    WebElement searchArea;

    @FindBy(xpath = "//*[@id=\"ui-id-2\"]/section[1]")
    WebElement suggestionsResults;

    @FindBy(xpath = "//*[@id=\"ui-id-2\"]/section[2]")
    WebElement productsResults;

    @FindBy(css = "div.product-list-wrapper")
    WebElement searchResults;

    @FindBy(xpath = "//*[@id=\"js-site-search-input\"]")
    WebElement inputSearchAgain;







    public MainPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> checkMenu() {
        List<WebElement> menu = navigationMenu.findElements(By.cssSelector("a.collapsed"));
        return menu;
    }

     public List<WebElement> checkSubMenu(){
         List<WebElement> who = checkMenu();
         Actions actions = new Actions(webDriver);
         actions.moveToElement(who.get(0));
         actions.perform();
         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.dropdown-items.ps-container.ps-theme-default")));
         List<WebElement> elements = dropDownMenu.findElements(By.tagName("a"));
         return elements;
     }

     public void checkStudent(){
         List<WebElement> who = checkMenu();
         Actions actions = new Actions(webDriver);
         actions.moveToElement(who.get(0));
         actions.perform();
         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.dropdown-items.ps-container.ps-theme-default")));
         List<WebElement> elements = dropDownMenu.findElements(By.tagName("a"));
         elements.get(0).click();
     }

     public List<WebElement> checkLearnMore(){
        List<WebElement> elements = webDriver.findElements(By.partialLinkText("Learn More"));
        return elements;
     }


     public List<WebElement> checkPageEducation() {
        List<WebElement> who = checkMenu();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(who.get(1));
        actions.perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.dropdown-items.ps-container.ps-theme-default.ps-active-y")));
        getDropDownMenuSubjects.findElement(By.xpath("//*[@id=\"Level1NavNode2\"]/ul/li[9]/a")).click();
        List<WebElement> elementsSubjects = leftMenuPageEducation.findElements(By.tagName("li"));
        return elementsSubjects;
    }


    public void checkLogo() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",logoWiley);
    }


    public void checkSearch() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",btnSearch);
    }


    public void checkSearchInput() {
        inputSearch.sendKeys("Java");
//        webDriver.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
//        List<WebElement> elements = suggestionsResults.findElement(By.xpath("//*[@id=\"ui-id-2\"]")).findElement(By.xpath("//*[@id=\"ui-id-2\"]/section[1]")).findElement(By.cssSelector("div.search-list")).findElements(By.cssSelector("div.searchresults-item ui-menu-item"));
//        System.out.println(elements.size());
//        for (WebElement el: elements) {
//            System.out.println(el.getText());
//        }
    }


    public List<WebElement> search() {
        inputSearch.sendKeys("Java");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",btnSearch);
        results = searchResults.findElements(By.cssSelector("h3.product-title"));
        return results;
    }

    public void searchAgain() {
        inputSearch.sendKeys("Java");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",btnSearch);
        results = searchResults.findElements(By.cssSelector("h3.product-title"));
        inputSearchAgain.clear();
        inputSearchAgain.sendKeys("Java");
        inputSearchAgain.sendKeys(Keys.RETURN);
        resultsAgain = searchResults.findElements(By.cssSelector("h3.product-title"));
    }
}