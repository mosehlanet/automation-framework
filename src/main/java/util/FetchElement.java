package util;

import framework.WebCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FetchElement {
    public WebElement getWebElement(String identifierType,String identifierValue){
        switch (identifierType){
            case "ID":
                return WebCore.driver.findElement(By.id(identifierValue));
            case "XPATH":
                return WebCore.driver.findElement(By.xpath(identifierValue));
            case "TAGNAME":
                return WebCore.driver.findElement(By.tagName(identifierValue));
            case "CSS":
                return WebCore.driver.findElement(By.cssSelector(identifierValue));
                default:
                    return null;
        }
    }

    public List<WebElement> getListWebElements(String identifierType, String identifierValue){
        switch (identifierType){
            case "ID":
                return WebCore.driver.findElements(By.id(identifierValue));
            case "XPATH":
                return WebCore.driver.findElements(By.xpath(identifierValue));
            case "TAGNAME":
                return WebCore.driver.findElements(By.tagName(identifierValue));
            case "CSS":
                return WebCore.driver.findElements(By.cssSelector(identifierValue));
            default:
                return null;
        }
    }
}
