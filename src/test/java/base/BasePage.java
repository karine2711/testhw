package base;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FavoritesPage;
import pages.ListedItem;
import pages.SearchResultPage;
import util.Locators;

import java.time.Duration;
import java.util.List;

import static util.Locators.SEARCH_BUTTON;
import static util.Locators.SEARCH_INPUT;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofSeconds(5));
        actions = new Actions(driver);
    }

    protected void load(String endPoint) {
        driver.get("https://www.etsy.com" + endPoint);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public SearchResultPage search(String input) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        driver.findElement(SEARCH_INPUT).sendKeys(input);
        driver.findElement(SEARCH_BUTTON).click();
        return new SearchResultPage(driver);
    }


    public FavoritesPage goToFavorites() {

        driver.findElement(Locators.GO_TO_FAVORITES).click();
        return new FavoritesPage(driver);
    }

    public ListedItem getFirstItemForSale() {
        return new ListedItem(driver.findElement(Locators.LISTED_ITEM), driver);
    }

    public ListedItem getItemById(String id) {
        By idLocator = By.cssSelector(String.format(Locators.ITEM_ID_TEMPLATE, id));
        return new ListedItem(driver.findElement(idLocator), driver);
    }


    protected boolean containsTextInDescendant(WebElement element, String searchText) {
        List<WebElement> descendants = element.findElements(By.xpath(".//*[contains(text(),'" + searchText + "')]"));
        return !descendants.isEmpty();
    }

    public void switchTo(String handle) {
        driver.switchTo().window(handle);
    }
    public String getHandle() {
       return driver.getWindowHandle();
    }
}
