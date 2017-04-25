package app.examples;

import static org.junit.Assert.assertTrue;

import app.pages.GoogleMainPage;
import base_test.BaseTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Created by Tyler Von Moll on 7/27/16.
 *
 * Description:
 */

public class GoogleExampleTest extends BaseTest {

    @Features("GOOG-1001")
    @Stories("GOOG-1001-F001")
    @Test
    public void whenNavToHomepage_thenSearchBarIsVisible() {
        requirementsCoverage.writeToFile("GOOG-1001-F001 -- search bar is visible on homepage");
        GoogleMainPage googleMainPage = new GoogleMainPage(driverManager, homePageUrl);
        googleMainPage.get();

        //eyes.checkWindow("Google Main Page");
        //eyes.close(); // assertion that we made it to the page using Applitools

        assertTrue("Search input is not visible on homepage", googleMainPage.isSearchInputVisible());
    }
}
