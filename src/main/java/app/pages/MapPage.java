package app.pages;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import app.domain_entities.Box;
import base_page_obj.BasePage;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jcramer on 4/27/2017.
 */
public class MapPage extends BasePage {

    private final BasePage parentPage;
    private final String expectedPageTitle = "MAP – Measurement Action Plan | Fusion Alliance";

    private Box mapContactUsBoxItem;

    @FindBy(css = "div[class*='contact-form'] > div")
    private WebElement contactUsBoxHeader;

    @FindBy(css = "a[href='/about']")
    private WebElement aboutPageLink;

    public MapPage(WebDriverManager driverManager, BasePage parentPage) {
        super(driverManager);
        this.parentPage = parentPage;
    }

    @Override
    protected void load() {
        parentPage.get();
        if (parentPage instanceof FusionMainPage)
            ((FusionMainPage) parentPage).navigateToMapPage();
        else {
            throw new RuntimeException("Page Obj type not recognized: " + parentPage.getClass());
        }
        waitManager.waitForPageTitleToBe(expectedPageTitle);

        mapContactUsBoxItem = new Box(doesContactUsBoxHeaderDisplay(), getContactUsBoxHeaderText(), false);
    }

    @Override
    protected void isLoaded() throws Error {
        assertThat(driverManager.getPageTitle(), equalTo(expectedPageTitle));
    }

    public boolean doesContactUsBoxHeaderDisplay() {
        return contactUsBoxHeader.isDisplayed();
    }

    public String getContactUsBoxHeaderText() {
        return contactUsBoxHeader.getText();
    }

    public Box getContactUsBox() {
        return mapContactUsBoxItem;
    }
}
