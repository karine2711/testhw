package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Locators;

import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean shopThisItemExists() {
        try {
            driver.findElement(By.xpath(" //a[contains(text(),'Shop this item')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public SearchResultPage sortByPriceAsc() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SORT_BY_OPTION_MENU));
        WebElement sortOptions = driver
                .findElement(Locators.SORT_BY_OPTION_MENU);
        sortOptions.click();
        sortOptions
                .findElement(Locators.SORT_BY_ASC_PRICE)
                .click();
        return this;
    }

    public double averagePriceOnPage() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.RELEVANT_PRICES_ON_PAGE));
        return driver
                .findElements(Locators.RELEVANT_PRICES_ON_PAGE)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().isBlank() ? "0" : e.getText()))
                .average()
                .getAsDouble();
    }

    public SearchResultPage filterByPriceUnder25() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.FIlTER_BUTTON));
        driver.findElement(Locators.FIlTER_BUTTON).click();
        String id = driver.findElement(Locators.PRICE_FILTER_RADIO_LESS_THAN_25).getAttribute("id");
        driver.findElement(By.cssSelector(String.format(Locators.LABEL_FOR_ID_TEMPLATE, id))).click();
        driver.findElement(Locators.APPLY_FILTER_BUTTON).click();
        return this;
    }

    public SearchResultPage filterByTypeVintage() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.FIlTER_BUTTON));
        driver.findElement(Locators.FIlTER_BUTTON).click();
        String id = driver.findElement(Locators.TYPE_FILTER_RADIO_VINTAGE).getAttribute("id");
        driver.findElement(By.cssSelector(String.format(Locators.LABEL_FOR_ID_TEMPLATE, id))).click();
        driver.findElement(Locators.APPLY_FILTER_BUTTON).click();
        return this;
    }

    public boolean isPriceInRange(int min, int max) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.RELEVANT_PRICES_ON_PAGE));
        return driver
                .findElements(Locators.RELEVANT_PRICES_ON_PAGE)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().isBlank() ? "0" : e.getText()))
                .allMatch(d -> d >= min && d <= max);
    }


}
