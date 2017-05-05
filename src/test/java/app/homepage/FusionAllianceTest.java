package app.homepage;

import app.pages.FusionMainPage;
import base_test.BaseTest;
import org.junit.Test;

/**
 * Created by jcramer on 4/26/2017.
 */
public class FusionAllianceTest extends BaseTest {

    @Test
    public void whenNavToHomepage_thenTitleIsCorrect() {
        FusionMainPage fusionMainPage = new FusionMainPage(driverManager, homePageUrl);
        fusionMainPage.get();

    }
}
