package app.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import base_page_obj.BasePage;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import io.appium.java_client.pagefactory.WithTimeout;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Tyler Von Moll on 7/27/16.
 *
 * Description: Example page obj for Google's homepage. Note that actions are annotated with @Step.
 */

public class GoogleMainPage extends BasePage {

    private String homePageUrl;

    @FindBy(id = "lst-ib")
    @WithTimeout(time = 60, unit = TimeUnit.SECONDS) // This is an optional override of the default timeout.
    private WebElement searchField;

    public GoogleMainPage(WebDriverManager webDriverManager, String homePageUrl) {
        super(webDriverManager);
        this.homePageUrl = homePageUrl;
    }

    @Override
    protected void load() {
        driverManager.navigateToUrl(homePageUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertThat(driverManager.getPageTitle(), equalTo("Google"));
    }

    @Step
    public void enterSearchTerm(String searchTerm) {
        searchField.sendKeys(searchTerm);
    }

    public boolean isSearchInputVisible() {
        return searchField.isDisplayed();
    }
}
