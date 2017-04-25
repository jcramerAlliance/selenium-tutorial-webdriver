package base_page_obj;

import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.LoadableComponent;
import wd_wrappers.ElementActions;
import wd_wrappers.WaitManager;

/**
 * Created by Tyler Von Moll on 7/27/16.
 *
 * Description: Abstract base class from which all page objects inherit.
 */

public abstract class BasePage extends LoadableComponent<BasePage> {

    protected WebDriverManager driverManager;
    protected WaitManager waitManager;
    protected ElementActions elActions;

    public BasePage(WebDriverManager driverManager) {
        this.driverManager = driverManager;
        this.waitManager = new WaitManager(driverManager.createWait(30));
        Actions actions = driverManager.getActionsObj();
        this.elActions = new ElementActions(waitManager, actions);
        driverManager.initPageElementsWithImplicitWait(this, 30);
    }
}
