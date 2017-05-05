package app.careers_page;

import app.pages.CareersPage;
import app.pages.FusionMainPage;
import base_test.BaseTest;
import org.junit.Test;

/**
 * Created by jcramer on 4/28/2017.
 */
public class CareersPageTest extends BaseTest {

    @Test
    public void givenOnCareersPage_thenPageTitleIsCorrect() {
        FusionMainPage fusionMainPage = new FusionMainPage(driverManager, homePageUrl);
        CareersPage careersPage = new CareersPage(driverManager, fusionMainPage);

        careersPage.get();
    }
}
