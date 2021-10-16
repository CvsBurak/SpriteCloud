package web.Steps;

import web.Base.HookImp;
import web.helper.Context;
import web.helper.ElementHelper;
import web.helper.ScenarioContext;
import web.helper.StoreHelper;
import web.model.ElementInfo;
import com.thoughtworks.gauge.Step;

import java.sql.*;
import java.util.*;
import java.util.NoSuchElementException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;
import static org.junit.Assert.assertTrue;

public class BaseSteps extends HookImp {

  public static int DEFAULT_MAX_ITERATION_COUNT = 150;
  public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;

  private static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
          .getLogger(BaseSteps.class);

  private static String SAVED_ATTRIBUTE;

  public ScenarioContext context;

  private Actions actions = new Actions(driver);
  private String compareText;

  public BaseSteps() {

    PropertyConfigurator
            .configure(BaseSteps.class.getClassLoader().getResource("log4j.properties"));
  }

  private WebElement findElement(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
    WebElement webElement = webDriverWait
            .until(ExpectedConditions.presenceOfElementLocated(infoParam));
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
            webElement);
    return webElement;
  }

  public List<WebElement> findElements(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    return driver.findElements(infoParam);
  }

  private void clickElement(WebElement element) {
    element.click();
  }

  private void clickElementBy(String key) {
    findElement(key).click();
  }

  private void hoverElement(WebElement element) {
    actions.moveToElement(element).build().perform();
  }

  private void hoverElementBy(String key) {
    WebElement webElement = findElement(key);
    actions.moveToElement(webElement).build().perform();
  }

  private void sendKeyESC(String key) {
    findElement(key).sendKeys(Keys.ESCAPE);

  }

  private boolean isDisplayed(WebElement element) {
    return element.isDisplayed();
  }

  private boolean isDisplayedBy(By by) {
    return driver.findElement(by).isDisplayed();
  }

  private String getPageSource() {
    return driver.switchTo().alert().getText();
  }

  public static String getSavedAttribute() {
    return SAVED_ATTRIBUTE;
  }

  public String randomString(int stringLength) {

    Random random = new Random();
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz0123456789".toCharArray();
    String stringRandom = "";
    for (int i = 0; i < stringLength; i++) {

      stringRandom = stringRandom + chars[random.nextInt(chars.length)];
    }

    return stringRandom;
  }

  public WebElement findElementWithKey(String key) {
    return findElement(key);
  }

  public String getElementText(String key) {
    return findElement(key).getText();
  }

  public String getElementAttributeValue(String key, String attribute) {
    return findElement(key).getAttribute(attribute);
  }

  public void javaScriptClicker(WebDriver driver, WebElement element) {

    JavascriptExecutor jse = ((JavascriptExecutor) driver);
    jse.executeScript("var evt = document.createEvent('MouseEvents');"
            + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
            + "arguments[0].dispatchEvent(evt);", element);
  }

  public void javascriptclicker(WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);
  }

  public void setTextToContextFromRandom(String type, WebElement elem) {
    context = new ScenarioContext();
    String text = elem.getText();
    logger.info("Getting " + type + " to compare, the " + type + " is: " + text);
    switch (type) {
      case "TITLE":
        context.setContext(Context.PRODUCT_TITLE, text);
        break;
      case "DESC":
        context.setContext(Context.PRODUCT_DESC, text);
        break;
    }
  }

  public String getTextFromContextToCompare(String key){
    switch (key) {
      case "DESC":
        return (String) context.getContext(Context.PRODUCT_DESC);
      case "TITLE":
        return (String) context.getContext(Context.PRODUCT_TITLE);
      default:
        return null;
    }
  }



  private JavascriptExecutor getJSExecutor() {
    return (JavascriptExecutor) driver;
  }

  private Object executeJS(String script, boolean wait) {
    return wait ? getJSExecutor().executeScript(script, "") : getJSExecutor().executeAsyncScript(script, "");
  }

  private void scrollTo(int x, int y) {
    String script = String.format("window.scrollTo(%d, %d);", x, y);
    executeJS(script, true);
  }

  public WebElement scrollToElementToBeVisible(WebElement elem) {
    if (elem != null) {
      scrollTo(elem.getLocation().getX(), elem.getLocation().getY() - 100);
    }
    return elem;
  }

  @Step("Send <text> to <key> by one by")
  public void sendKeyOneByOne(String text, String key) throws InterruptedException {

    WebElement field = findElement(key);
    field.clear();
    if (!key.equals("")) {
      for (char ch: text.toCharArray())
      findElement(key).sendKeys(Character.toString(ch));
      Thread.sleep(10);
      logger.info(key + " elementine " + text + " texti karakterler tek tek girlilerek yazıldı.");
    }
  }





}









