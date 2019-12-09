import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By userNameFieldLocator = By.id("loginUsername");
    private By userNameLocator = By.xpath("//span[contains(@class, '_2BM')]");

    @FindBy(id = "loginPassword")
    private WebElement passwordFieldLocator;

    @FindBy(className = "AnimatedForm__submitButton")
    private WebElement signInButtonLocator;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(driver, 30);
    }

    public void fillLoginField(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFieldLocator));
        driver.findElement(userNameFieldLocator).sendKeys(userName);
    }

    public void fillPasswordField(String password){
        passwordFieldLocator.sendKeys(password);
    }

    public void clickOnSignInButton(){
        signInButtonLocator.click();
    }

    public void assertLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator));
        String actualUserName = driver.findElement(userNameLocator).getText();
        Assert.assertEquals(actualUserName, "TesterNikita");
    }

}
