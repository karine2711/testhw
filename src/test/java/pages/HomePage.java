package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.Locators;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage clickFirstSearchBubble() {
        WebElement firstSearchBubble = driver.findElements(Locators.HOMEPAGE_SEARCH_BUBBLES).get(0);
        firstSearchBubble.click();
        return new SearchResultPage(driver);
    }

    public WebElement getFirstSearchBubble() {
       return driver.findElements(Locators.HOMEPAGE_SEARCH_BUBBLES).get(0);
    }

    public HomePage load(){
        super.load("/");
        return this;
    }

}
