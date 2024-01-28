import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;


public class ExecutionSetManager {

    private WebDriver driver;
    private final Helper helpObj;
    private final Map<String, String> SuiteXml;



    public ExecutionSetManager( Helper helpObj, Map<String, String> SuiteXml){

        this.helpObj = helpObj;
        this.SuiteXml = SuiteXml;

    }

    public void run() throws IOException {
        callerMethod();
    }

    private void callerMethod() throws IOException {

        Suite suite = new Suite(helpObj,SuiteXml);
        suite.loginCase();

    }

}
