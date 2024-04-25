package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import util.Locators;

import java.util.List;
import java.util.stream.Collectors;

public class FavoritesPage extends BasePage {
    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public List<ListedItem> getFavorites() {
        return driver.findElement(Locators.FAVORITES_SECTION)
                .findElements(Locators.LISTED_ITEM)
                .parallelStream()
                .map(webElement -> new ListedItem(webElement, driver))
                .collect(Collectors.toList());
    }
}
