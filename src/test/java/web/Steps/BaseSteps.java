package web.Steps;

import web.Base.HookImp;
import web.helper.Context;
import web.helper.ElementHelper;
import web.helper.ScenarioContext;
import web.helper.StoreHelper;
import web.model.ElementInfo;
import com.thoughtworks.gauge.Step;

import java.util.*;
import java.util.NoSuchElementException;
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

  private static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
          .getLogger(BaseSteps.class);

  public ScenarioContext context;
  private Actions actions = new Actions(driver);

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

  public WebElement findElementWithKey(String key) {
    return findElement(key);
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









