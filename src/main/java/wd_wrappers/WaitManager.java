package wd_wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Tyler Von Moll on 7/27/16.
 *
 * Description: A wrapper class with some common waits to supplement the standard @WithTimeout
 * annotation.
 */

public class WaitManager {

    private WebDriverWait wait;

    public WaitManager(WebDriverWait webDriverWait) {
        this.wait = webDriverWait;
    }

    /**
     * Use this when you want to wait for at least a specified number of elements to be in the list.
     *
     * @param locator          By object
     * @param numberOfElements minimum number of elements desired.
     * @return the list of elements that was found if it is >= the numberOfElements arg.
     */
    public List<WebElement> waitForElementListFound(By locator, final Integer numberOfElements) {
        try {
            return wait.until((ExpectedCondition<List<WebElement>>) d1 -> {
                List<WebElement> elements = d1.findElements(locator);
                return elements.size() >= numberOfElements ? elements : null;
            });
        } catch (TimeoutException te) {
            throw new Error("Timed out after waiting for at least "
                    + numberOfElements + " elements found: '" + locator + "'");
        }
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeFoundAndVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitUntilNotVisible(By locator) {
        // wait until the element exists to avoid an immediate break from the expected condition
        try {
            waitForElementFound(locator);
        } catch (TimeoutException te) {
            return true; // if it can't be located, break out of this quickly
        }
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForElementFound(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForPageTitleToBe(String expectedPageTitle) {
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
    }
}
