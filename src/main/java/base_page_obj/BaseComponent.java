package base_page_obj;

import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;

/**
 * Created by Tyler Von Moll on 9/8/16.
 *
 * Description: A component is defined as a section of the UI that can be broken off, is instantiated in its page object class, and is indirectly accessed in test classes. load() and isLoaded() are ignored because all navigation is happening on the page object class to which the component belongs.
 */

public abstract class BaseComponent extends BasePage {

    public BaseComponent(WebDriverManager driverManager) {
        super(driverManager);
    }

    @Override
    protected void load() {
        //ignored
    }

    @Override
    protected void isLoaded() throws Error {
        //ignored
    }
}
