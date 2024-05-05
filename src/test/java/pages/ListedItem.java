package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Locators;


public class ListedItem extends BasePage {

    private final WebElement item;

    public ListedItem(WebElement item, WebDriver driver) {
        super(driver);
        this.item = item;
    }

    public String getTitle() {
        return item
                .findElement(By.tagName("a"))
                .getAttribute("title");
    }

    public String getId() {
        return item.getAttribute("data-palette-listing-id");
    }

    public ItemPage expand() {
        wait.until(ExpectedConditions.elementToBeClickable(item.findElement(By.tagName("a"))));
        item
                .findElement(By.tagName("a"))
                .click();
        return new ItemPage(driver);
    }

    // can you please advise in feedback how to deal with this? I was never able to add it to favorites
    public void toggleFavorite(WebDriverWait wait) {
//        Actions element = actions.moveToElement(item);
//        wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_FAVORITES));
        wait.until(ExpectedConditions.attributeToBe(Locators.ADD_TO_FAVORITES, "data-favorited-label", "Remove from Favorites"));
        actions.moveToElement(item).click(item.findElement(Locators.ADD_TO_FAVORITES)).perform();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("$(\"[data-accessible-btn-fave]\").click()");
//        wait.until(ExpectedConditions.elementToBeClickable(item.findElement(Locators.ADD_TO_FAVORITES)));

//        item.findElement(Locators.ADD_TO_FAVORITES).click();
        wait.until(ExpectedConditions.visibilityOf(item.findElement(By.cssSelector("span[data-favorited-icon]"))));
    }
}
