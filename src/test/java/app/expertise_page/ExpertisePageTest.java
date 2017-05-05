package app.expertise_page;

import app.pages.ExpertisePage;
import app.pages.FusionMainPage;
import base_test.BaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by jcramer on 5/1/2017.
 */
public class ExpertisePageTest extends BaseTest {

    private ExpertisePage expertisePage;

    @Before
    public void setUp() {
        super.setUp();
        FusionMainPage fusionMainPage = new FusionMainPage(driverManager, homePageUrl);
        expertisePage = new ExpertisePage(driverManager, fusionMainPage);
        expertisePage.get();
    }

    @Test
    public void givenExpertisePage_whenExamineCategoriesDropDown_thenOptionsAreAccurate() {
        List<String> expectedDropdownOptions = Arrays.asList("View all", "Insights", "Experiences", "Foundations");
        List<String> actualDropdownOptions = expertisePage.getCategoriesDropDown();

        assertThat(actualDropdownOptions, equalTo(expectedDropdownOptions));
    }

    @Test
    public void givenExpertisePage_whenExamineTopicsDropDown_thenOptionsAreAccurate() {
        List<String> expectedDropdownOptions = Arrays.asList("Digital", "Data", "Technology", "Cloud");
        List<String> actualDropdownOptions = expertisePage.getTopicsDropdownOptions();

        assertThat(actualDropdownOptions, equalTo(expectedDropdownOptions));
    }
}
