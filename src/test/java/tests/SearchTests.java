package tests;

import base.BaseTest;
import listeners.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FavoritesPage;
import pages.ListedItem;
import pages.SearchResultPage;
import util.Locators;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

@Listeners(ScreenshotListener.class)
public class SearchTests extends BaseTest {
    Random random = new Random();

    @Test
    public void searchThroughHomepageBubbles() throws MalformedURLException, InterruptedException {
        homePage.load();
        Thread.sleep(getRandomWait());
        WebElement firstSearchBubble = homePage.getFirstSearchBubble();
        String searchHref = firstSearchBubble.findElement(By.tagName("a")).getAttribute("href");
        String searchQuery = new URL(searchHref).getQuery();
        Thread.sleep(getRandomWait());
        SearchResultPage searchResultPage = homePage.clickFirstSearchBubble();

        assertTrue(searchQuery.contains("ref=hp_bubbles"));
        assertTrue(searchResultPage.shopThisItemExists());
    }


    @Test
    public void searchThroughSearchBarAndSortByPrice() throws InterruptedException {
        Thread.sleep(getRandomWait());

        SearchResultPage searchResultPage = homePage.load()
                .search("earrings");

        Thread.sleep(getRandomWait());


        double averagePriceBeforeSort = searchResultPage.averagePriceOnPage();
        Thread.sleep(getRandomWait());

        double averagePriceAfterSort = searchResultPage
                .sortByPriceAsc()
                .averagePriceOnPage();

        assertTrue(averagePriceAfterSort < averagePriceBeforeSort);
    }

    @Test
    public void addToFavoritesThanRemove() throws InterruptedException {
        Thread.sleep(getRandomWait());

        SearchResultPage earringsSearch = homePage.load()
                .search("earrings");
        ListedItem firstItemForSale = earringsSearch.getFirstItemForSale();

        String id = firstItemForSale.getId();
        String title = firstItemForSale.getTitle();

        Thread.sleep(getRandomWait());
        String windowHandle = earringsSearch.getHandle();
        firstItemForSale
                .expand()
                .toggleFavorite()
                .waitToBeInFavorites();

        Thread.sleep(getRandomWait());

        earringsSearch.switchTo(windowHandle);

        List<ListedItem> favorites = earringsSearch
                .goToFavorites()
                .getFavorites();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.LISTED_ITEM));
        assertTrue(favorites.parallelStream().anyMatch(e -> e.getId().equals(id)));
        Thread.sleep(getRandomWait());

        FavoritesPage favoritesPage = homePage.goToFavorites();

        List<ListedItem> newFavorites = favoritesPage
                .getItemById(id)
                .expand()
                .toggleFavorite()
                .goToFavorites()
                .getFavorites();

        assertFalse(newFavorites.parallelStream().anyMatch(e -> e.getId().equals(id)));

    }

    @Test
    public void testFilterByPrice() throws InterruptedException {
        Thread.sleep(getRandomWait());
        SearchResultPage searchResultPage = homePage.load()
                .search("earrings")
                .filterByPriceUnder25();
        assertTrue(searchResultPage.isPriceInRange(0, 25));
    }

    @Test
    public void testFilterByType() throws InterruptedException {
        Thread.sleep(getRandomWait());
        SearchResultPage searchResultPage = homePage.load()
                .search("earrings")
                .filterByTypeVintage();
        boolean isVintageInItemDetails = searchResultPage
                .getFirstItemForSale()
                .expand()
                .itemDetailsContainString("Vintage");

        assertTrue(isVintageInItemDetails);
    }

    //    @Test
    // use for testing screenshots
    public void failingTest() {
        homePage.load();
        assertTrue(false);
    }

//    make robot situation a little better
    public long getRandomWait() {
        return Math.abs(random.nextLong() % 500 + 1);
    }
}
