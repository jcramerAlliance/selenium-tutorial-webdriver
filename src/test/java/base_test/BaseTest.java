package base_test;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import com.fusionalliance.driver_and_server.WebDriverFactory;
import com.fusionalliance.driver_and_server.browser_and_device.BrowserOrDeviceSelectionProperties;
import com.fusionalliance.driver_and_server.webdriver_manager.WebDriverManager;
import com.fusionalliance.properties.final_assembly.FinalProperties;
import com.fusionalliance.reporting.allure.AllureEnvPropertiesInitializer;
import com.fusionalliance.reporting.allure.PageSourceHtmlCapture;
import com.fusionalliance.reporting.allure.Screenshot;
import com.fusionalliance.reporting.sauce.SauceTestProvider;
import com.fusionalliance.reporting.sauce.SauceTestWatcher;
import com.fusionalliance.requirements_coverage.RequirementsCoverage;
import com.fusionalliance.test_context.FullTestContext;
import com.fusionalliance.test_context.eyes.EyesInitializer;
import com.fusionalliance.test_context.eyes.EyesOpener;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

/**
 * Created by Tyler Von Moll on 7/27/16.
 * Description: Abstract base class that generates the test context based upon requested properties.
 *
 * Included Steps:
 * 1. Compiles requested properties from xml, cli, and your environment variables into the PropertiesCache singleton.
 * 2. Creates WebDriver instance for requested run location (local, browserstack, sauce).
 *
 * Optional Steps:
 * 1. Initialize driver for use with Applitools Eyes
 * (to include, new up an instance of EyesInitializer in BaseTest constructor and make the call to .createEyes();
 * then in setUp(), re-bind driver as such:
 * driver = eyesInitializer.initEyesWithDriver(eyes, driver, testName.getMethodName());
 * 2. Initialize the SauceJobManager (along with the test watcher to mark passes/fails).
 */
@RunWith(Parameterized.class)
@ContextConfiguration(classes = FullTestContext.class)
public abstract class BaseTest implements SauceTestProvider {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestName testName = new TestName();

    private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();
    private SauceREST sauceREST = new SauceREST(authentication.getUsername(), authentication.getAccessKey());

    @Rule
    public SauceTestWatcher sauceTestWatcher = new SauceTestWatcher(sauceREST, this);

    @Autowired
    private FinalProperties finalProperties;

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Parameterized.Parameter()
    public String browserName;

    @Parameterized.Parameter(1)
    public String browserVersion;

    @Parameterized.Parameter(2)
    public String osName;

    @Parameterized.Parameter(3)
    public String osVersion;

    @Parameterized.Parameter(4)
    public String device;

    protected RequirementsCoverage requirementsCoverage = new RequirementsCoverage();
    protected WebDriverManager driverManager = WebDriverManager.empty();
    protected Eyes eyes = new Eyes();
    protected String homePageUrl;
    private BrowserOrDeviceSelectionProperties currentTestBrowserOrDeviceProperties;
    private String sessionId;

    @Parameterized.Parameters(name = "{0} {1}")
    public static Collection generateParameters() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FullTestContext.class);
        context.getBean(AllureEnvPropertiesInitializer.class).initAllureEnvironmentProps();
        return context.getBean(FinalProperties.class).getBrowserOrDeviceConfigurations();
    }

    @Before
    public void setUp() {
        currentTestBrowserOrDeviceProperties = BrowserOrDeviceSelectionProperties
                .generate(browserName, browserVersion, osName, osVersion, device, finalProperties.getAppType());
        WebDriver driver = webDriverFactory.getSession(currentTestBrowserOrDeviceProperties);
        sessionId = ((RemoteWebDriver) driver).getSessionId().toString(); // this has to be grabbed before the .openEyes() call.
        EyesOpener eyesOpener = getEyesOpener(driver);
        driver = eyesOpener.openEyes();
        driverManager = WebDriverManager.createInstance(driver);
        sauceTestWatcher.addTestName(testName.getMethodName());
        homePageUrl = finalProperties.get("home-page-url");
    }

    private EyesOpener getEyesOpener(WebDriver driver) {
        EyesInitializer eyesInitializer = new EyesInitializer(finalProperties);
        eyes = eyesInitializer.createEyesWithMatchLevel(MatchLevel.LAYOUT2);
        return new EyesOpener(eyes, driver, testName.getMethodName(), currentTestBrowserOrDeviceProperties);
    }

    @After
    public void tearDown() {
        attachScreenshotAndPageSource();
        eyes.abortIfNotClosed();
        driverManager.quitBrowser();
    }

    private void attachScreenshotAndPageSource() {
        new Screenshot(driverManager.getScreenshotShooter(), "After test window").captureScreenshot();
        new PageSourceHtmlCapture(driverManager.getPageSource()).capturePageSource();
    }

    @Override
    public boolean isSauce() {
        return finalProperties.get("runLocation").toUpperCase().equals("SAUCE");
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }
}
