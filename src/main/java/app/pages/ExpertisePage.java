package app.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import base_page_obj.BasePage;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcramer on 5/1/2017.
 */
public class ExpertisePage extends BasePage {

    private final FusionMainPage fusionMainPage;

    private final String expectedPageTitle = "Cross-disciplinary expertise | Fusion Alliance";

    @FindBy(css = "#expertise_category")
    private WebElement categoriesDropDown;

    @FindBy(css = "#expertise_topic_toggle")
    private WebElement topicsDropdownToggle;

    @FindBy(css = "#expertise_topic_select")
    private WebElement topicsDropDown;

    public ExpertisePage(WebDriverManager driverManager, FusionMainPage fusionMainPage) {
        super(driverManager);
        this.fusionMainPage = fusionMainPage;
    }

    @Override
    protected void load() {
        fusionMainPage.get();
        fusionMainPage.navigateToExpertisePage();
        waitManager.waitForPageTitleToBe(expectedPageTitle);
    }

    @Override
    protected void isLoaded() throws Error {
        assertThat(driverManager.getPageTitle(), equalTo(expectedPageTitle));
    }

    public List<String> getCategoriesDropDown() {
        List<WebElement> ddOptions = categoriesDropDown.findElements(By.cssSelector("option"));
        List<String> ddOptionsText = new ArrayList<>();

        ddOptions.forEach(x -> ddOptionsText.add(x.getText()));

        return ddOptionsText;
    }

    public List<String> getTopicsDropdownOptions() {
        topicsDropdownToggle.click();

        List<WebElement> ddOptions = topicsDropDown.findElements(By.cssSelector(".text"));
        List<String> ddOptionsText = new ArrayList<>();

        ddOptions.forEach(x -> ddOptionsText.add(x.getText()));

        return ddOptionsText;
    }
}
