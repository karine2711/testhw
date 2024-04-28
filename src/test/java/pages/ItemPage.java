package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Locators;

public class ItemPage extends BasePage {

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public ItemPage toggleFavorite() {
        driver.findElement(Locators.ADD_TO_FAVORITES).click();
        return this;
    }

    public boolean itemDetailsContainString(String str) {
//        if (!driver.findElement(Locators.PRODUCT_DETAILS_SECTION).isDisplayed()){
//
//        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PRODUCT_DETAILS_SECTION));
        WebElement productDetails = driver.findElement(Locators.PRODUCT_DETAILS_SECTION);
        return containsTextInDescendant(productDetails, str);
    }

    public void waitToBeInFavorites() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Locators.FAVORITED_ICON));
    }
}
