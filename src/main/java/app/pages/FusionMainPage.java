package app.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import base_page_obj.BasePage;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jcramer on 4/26/2017.
 */
public class FusionMainPage extends BasePage {

    private String homepageUrl;

    @FindBy (css = "a[href='/careers']")
    private WebElement mainMenuCareersLink;

    @FindBy (css = "a[href='/products']")
    private WebElement mainMenuProductsItem;

    @FindBy (css = "a[href='/expertise']")
    private WebElement mainMenuExpertiseLink;

    @FindBy (css = "a[href='/products/map")
    private WebElement mainMenuProductsMapLink;

    public FusionMainPage(WebDriverManager webDriverManager, String homePageUrl) {
        super(webDriverManager);
        this.homepageUrl = homePageUrl;
    }

    @Override
    protected void load() {
        driverManager.navigateToUrl(homepageUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertThat(driverManager.getPageTitle(), equalTo("Digital | Data | Technology | Cloud | Fusion Alliance"));
    }

    public void navigateToExpertisePage() {
        mainMenuExpertiseLink.click();
    }

    public void navigateToMapPage() {
        mainMenuProductsItem.click();
        mainMenuProductsMapLink.click();

        waitManager.waitForElementFound(By.cssSelector("img[src='/wp-content/uploads/2016/03/map-logo.svg']"));
    }

    public void navigateToCareersPage() {
        mainMenuCareersLink.click();

        waitManager.waitForElementFound(By.cssSelector("img[src='/wp-content/uploads/2016/10/careers_fusion.jpg']"));
    }
}
