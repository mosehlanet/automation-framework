package testcases.tests;

import framework.WebCore;
import org.testng.annotations.Test;
import testcases.pages.SamplePage;

public class SampleTest extends WebCore {

    @Test
    public void sampleTestMethod(){
        SamplePage samplePage = new SamplePage();
        samplePage.input();
    }

}

