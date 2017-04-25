package wd_wrappers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Tyler Von Moll on 7/28/16.
 *
 * Description: Wrapper to house general WebElement actions.
 */

public class ElementActions {

    private WaitManager waitManager;
    private Actions actions;

    public ElementActions(WaitManager waitManager, Actions actions) {
        this.waitManager = waitManager;
        this.actions = actions;
    }

    public String getTextFrom(WebElement element) {
        waitManager.waitForElementToBeVisible(element);
        return element.getText();
    }

    public void mouseOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
}
