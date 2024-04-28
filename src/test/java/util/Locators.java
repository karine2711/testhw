package util;

import org.openqa.selenium.By;

public class Locators {

    // Homepage Locators
    public static final By HOMEPAGE_SEARCH_BUBBLES = By.className("homepage_search_bubble");
    public static final By SEARCH_INPUT = By.cssSelector("form#gnav-search input[type='text']");
    public static final By SEARCH_BUTTON = By.cssSelector("form#gnav-search button[type='submit']");
    public static final By SORT_BY_OPTION_MENU = By.cssSelector("#sortby");
    public static final By SORT_BY_ASC_PRICE = By.cssSelector("a[data-sort-by='price_asc']");
    public static final By RELEVANT_PRICES_ON_PAGE = By.cssSelector(".n-listing-card__price .lc-price .currency-value");

    public static final By LISTED_ITEM = By.className("v2-listing-card");
    public static final By ADD_TO_FAVORITES = By.cssSelector("button[data-accessible-btn-fave]");
    public static final By FAVORITED_ICON = By.cssSelector("button[data-accessible-btn-fave] span[data-favorited-icon]");

    public static final By PRODUCT_DETAILS_SECTION = By.cssSelector("ul[data-selector=\"product-details-highlights\"]");
    public static final By GO_TO_FAVORITES = By.cssSelector("li[data-favorites-nav-container]");
    public static final String ITEM_ID_TEMPLATE = "div[data-palette-listing-id=\"%s\"]";

    public static final By FIlTER_BUTTON = By.id("search-filter-button");
    public static final By FIlTER_CLOSE_BUTTON = By.className("search-filters-close");

    public static final By FAVORITES_SECTION = By.cssSelector("ul[data-favorites-section]");
    public static final By PRICE_FILTER_RADIO_LESS_THAN_25 = By.cssSelector("div[data-appears-component-name=\"price\"] input[value=\"_25\"]");
    public static final By TYPE_FILTER_RADIO_VINTAGE = By.cssSelector("div[data-appears-component-name=\"item_type\"] input[value=\"vintage\"]");

    public static final String LABEL_FOR_ID_TEMPLATE = "label[for='%s']";
    public static final By APPLY_FILTER_BUTTON = By.cssSelector("button[data-filter-form-apply-button]");
}
