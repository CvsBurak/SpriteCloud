package web.Steps;


import org.openqa.selenium.JavascriptExecutor;
import web.Base.HookImp;
import com.thoughtworks.gauge.Step;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;
import web.helper.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class StepImplementation extends BaseSteps {

    private static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
            .getLogger(StepImplementation.class);


    public StepImplementation(){
        PropertyConfigurator
                .configure(StepImplementation.class.getClassLoader().getResource("log4j.properties"));
    }

    @Step("Click element by <key> with index <index>")
    public void clickElementByKey(String key, int index){
        logger.info("Clicking the element that's index is " + index);
        findElements(key).get(index).click();
    }

    @Step("Scroll to <key> with <index>")
    public void scrollToElement(String key, int index) {
        List<WebElement> elem = findElements(key);
        logger.info("Scrolling to elements that's index is " + index);
        scrollToElementToBeVisible(elem.get(index));
    }

    @Step("Check elements <key> title, equals to <text>")
    public void checkElementTextEqualsTo(String key, String text) {
        logger.info("The element's text is " + findElementWithKey(key).getText() + " and the given text is " + text);
        Assert.assertTrue(text.contains(findElementWithKey(key).getText()));
    }

    @Step("Click random <key>")
    public void clickRandomElementByKey(String key){
        List<WebElement> elem = findElements(key);
        Random random = new Random();
        int randNum = random.nextInt(elem.size());
        logger.info("Clicking random element");
        elem.get(randNum).click();
    }

    @Step("Send <text> to element <key>")
    public void sendValuesToElement(String text, String key){
        logger.info("Sending " + text + " to element");
        findElementWithKey(key).sendKeys(text);
    }

    @Step("Click elemetn by <key>")
    public void clickElement(String key){
        logger.info("Clicking element");
        findElementWithKey(key).click();
    }

    @Step("Click element <key> which values equals to <text> in a list")
    public void clickElementEqualsToText(String key, String text){
        List<WebElement> elem = findElements(key);
        logger.info("Looking for " + text + " in a list");
        for (WebElement webElement : elem) {
            if (webElement.getText().contains(text)) {
                logger.info("Clicking element whose value is " + webElement.getText());
                webElement.click();
            }
        }
    }

    @Step("Check element <key> which values equals to <name> in a list")
    public void checkElementEqualsToText(String key, String text){
        List<WebElement> elem = findElements(key);
        logger.info("Looking for " + text + " in a list");
        for (WebElement webElement : elem) {
            if (webElement.getText().contains(text)) {
                logger.info("Element has found");
                Assert.assertTrue(true);
            }
        }
    }

    @Step("Click <key> which the other <key2> values equals to <text> in a list")
    public void clickAnotherElementEqualsToText(String key, String key2, String text){
        List<WebElement> elem = findElements(key);
        List<WebElement> elem2 = findElements(key2);
        logger.info(String.valueOf(elem2));
        for (int i =0; i<elem2.size(); i++ ) {
            logger.info("The record is  " + elem2.get(i).getText());
            if (elem2.get(i).getText().contains(text)) {
                logger.info("The record is deleting which name is " + elem2.get(i).getText());
                elem.get(i).click();
            }
        }
    }

    @Step("Click random <key> and save text to context with <type>")
    public void clickRandomElementSaveToContext(String key, String type){
        List<WebElement> elem = findElements(key);
        Random random = new Random();
        int randNum = random.nextInt(elem.size());
        WebElement finalElem = elem.get(randNum);
        logger.info("Saving the " + finalElem.getText() + " as " + type);
        setTextToContextFromRandom(type, finalElem);
        finalElem.click();
    }

    @Step("Get element <type> by key <key> and compare with context, if <equal>")
    public void getTextAndContextCompare(String type, String key, String equal) {
        String lastText = null;
        int lastCount = 0;
        if(type.equals("COUNT")){
            lastCount = findElements(key).size();
            logger.info(String.valueOf(lastCount));
        } else {
            lastText = findElementWithKey(key).getText();
            logger.info(lastText);
        }

        String text = null;
        int count = 0;
        switch (type) {
            case "TITLE":
                text = (String) context.getContext(Context.PRODUCT_TITLE);
                logger.info("The saved context is: " +text);
                break;
            case "DESC":
                text = (String) context.getContext(Context.PRODUCT_DESC);
                logger.info("The saved context is: " +text);
                break;
            case "COUNT":
                count = (int) context.getContext(Context.COUNT);
                logger.info("The saved context is: " +count);
                break;
        }
        if(type.equals("COUNT")){
            if (equal.equals("EQUAL")) {
                Assert.assertEquals(lastCount,count);
            } else Assert.assertNotEquals(lastCount,count);

        } else {
            if (equal.equals("EQUAL")){
                Assert.assertTrue(lastText.contains(text));
            } else Assert.assertFalse(lastText.contains(text));
        }
    }

    @Step("Wait <value> seconds")
    public void waitBySeconds(int seconds) {
        try {
            logger.info("Waiting " + seconds + " seconds");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Wait <value> milliseconds")
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info("Waiting " + milliseconds + " miliseconds");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Clear text of element <key>")
    public void clearInputArea(String key) {
        findElementWithKey(key).clear();
    }

    @Step("Open new tab")
    public void chromeOpenNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    @Step("Focus on tab number <number>")//Starting from 1
    public void chromeFocusTabWithNumber(int number) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number - 1));
    }

    @Step("Focus on last tab")
    public void chromeFocusLastTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    @Step("Check alerts text equals to <text>")
    public void checkAlertTextEqualsTo(String text){
        Assert.assertEquals(text, driver.switchTo().alert().getText());
    }

    @Step("Accept alert")
    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    @Step("Decline alert")
    public void declineAlert(){
        driver.switchTo().alert().dismiss();
    }

    @Step("Send text with alert <text>")
    public void sendTextWithAlert(String text){
        driver.switchTo().alert().sendKeys(text);
    }

    @Step("Focus on new tab")
    public void chromeFocusNewTab(){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    @Step("Close the current tab")
    public void closeCurrentChromeTab(){
        driver.close();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }
}
