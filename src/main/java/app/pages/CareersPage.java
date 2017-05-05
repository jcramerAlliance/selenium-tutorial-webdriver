package app.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import base_page_obj.BasePage;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;

/**
 * Created by jcramer on 4/28/2017.
 */
public class CareersPage extends BasePage {

    private final String expectedTitle = "Human-driven careers | Fusion Alliance";
    private final FusionMainPage fusionMainPage;

    public CareersPage(WebDriverManager driverManager, FusionMainPage fusionMainPage) {
        super(driverManager);
        this.fusionMainPage = fusionMainPage;
    }

    @Override
    protected void load() {
        fusionMainPage.get();
        fusionMainPage.navigateToCareersPage();
        waitManager.waitForPageTitleToBe(expectedTitle);
    }

    @Override
    protected void isLoaded() throws Error {
        assertThat(driverManager.getPageTitle(), equalTo(expectedTitle));
    }
}
