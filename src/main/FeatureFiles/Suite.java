import Excel.DataCollection;
import Excel.ExcelLib;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Suite {


    private  final Helper helpObj;
    private final Map<String, String> SuiteXml;
    private String browser = "chrome";
    private WebDriver driver;
    
    private ExcelLib ExcelLib;
    private String pathToExcelFiles = "src/main/FeatureFiles/ExcelFiles/";
    private String ExcelFileName = "Login.xlsx";
    private String URL;
    private String userName;
    private ExtentTest test;

    private String password;
    List<DataCollection> dataCol;


    public Suite( Helper helpObj, Map<String, String> SuiteXml ) {
        this.helpObj = helpObj;
        this.SuiteXml = SuiteXml;
        this.browser=browser;
        this.ExcelFileName = pathToExcelFiles + ExcelFileName;
    }

    public String getXmlValue(String key){

        return SuiteXml.get(key);

    }

    private void setValue(String field, String value, ExtentTest test) {
        if (value == null || value.trim().isEmpty()) {
            driver.findElement(By.xpath(SuiteXml.get(field))).clear();
        } else {
            driver.findElement(By.xpath(SuiteXml.get(field))).clear();
            helpObj.sendKeys(driver, By.xpath(SuiteXml.get(field)), value, test);
            helpObj.pageLoad(driver);

        }
    }

    public String loginCase() throws IOException {

        helpObj.logInfo("Login test case");
        dataCol = ExcelLib.populateInCollection(ExcelFileName, "Credentials");
        userName = ExcelLib.readData(dataCol, 1, "UserName");
        password = ExcelLib.readData(dataCol, 1, "Password");
        URL = ExcelLib.readData(dataCol, 1, "URL");
        String returnVal = "";

        try {
            driver = helpObj.setBrowser(browser);

            driver.get(URL);

            helpObj.pageLoad(driver, By.xpath(getXmlValue("userName_input")));
            setValue("userName_input", userName,test);
            setValue("password_input", password,test);

            helpObj.buttonClickSimple(driver, By.xpath(getXmlValue("submit_button")));
           //helpObj.pageLoad(driver);

        } catch (Exception ex) {
            returnVal = "Error occurred while logging in Suite Dashboard." + System.lineSeparator() + "" + ex.toString();
            helpObj.logError(returnVal);
        }
        return returnVal;
    }








}
