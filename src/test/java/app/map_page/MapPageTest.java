package app.map_page;

import app.domain_entities.Box;
import app.pages.FusionMainPage;
import app.pages.MapPage;
import base_test.BaseTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by jcramer on 4/27/2017.
 */
public class MapPageTest extends BaseTest {

    private MapPage mapPage;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        FusionMainPage fusionMainPage = new FusionMainPage(driverManager, homePageUrl);
        mapPage = new MapPage(driverManager, fusionMainPage);
        mapPage.get();
    }

    @Test
    public void givenMapPage_thenContactUsBoxIsVisible() {
        Box expectedBox = new Box(true, "Contact Us", false);
        Box actualBox = mapPage.getContactUsBox();
        assertThat(actualBox, equalTo(expectedBox));
    }
}
