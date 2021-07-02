package testcases.pages;

import testcases.pageObjects.SamplePageElements;
import util.FetchElement;

public class SamplePage {

    public void input(){
        FetchElement fetchElement = new FetchElement();

        fetchElement.getWebElement("XPATH", SamplePageElements.searchInputBox).sendKeys("what is google");
    }
}
