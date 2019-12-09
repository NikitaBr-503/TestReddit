import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String expectedResultOfComment;
    private String actualResultOfComment;

    private By commentButtonLocator = By.xpath("//a[contains(@data-click-id, 'comments')]");
    private By commentFieldLocator = By.xpath("//div[contains(@class, 'not')]");
    private By dateOfComment = By.className("_1sA-1jNHouHDpgCp1fCQ_F");

    @FindBy(xpath = "//div[contains(@class, '_3SN')]")
    private WebElement sendCommentButtonLocator;

    @FindBy(xpath = "//a[@class ='_23wugcdiaj44hdfugIAlnX ']")
    private WebElement nameOfCommenterLocator;

    @FindBy(className = "HQ2VJViRjokXpRbJzPvvc")
    private WebElement actualDateLocator;

    public CommentPage(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(driver, 30);
    }

    public void clickCommentButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentButtonLocator));
        driver.findElement(commentButtonLocator).click();
    }

    public void fillCommentField(String message){
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentFieldLocator));
        driver.findElement(commentFieldLocator).sendKeys(message);
    }

    public void sendCommentButtonClick(){
        sendCommentButtonLocator.click();
    }

    public void openCommentInNewTab(){
        Actions action = new Actions(driver);
        action.pause(5000).build().perform();
        driver.findElement(dateOfComment).click();
    }

    public void getDataOfComment(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfComment));
        String nameOfCommenter = nameOfCommenterLocator.getText();
        Actions action = new Actions(driver);
        WebElement dateElement = driver.findElement(dateOfComment);
        action.moveToElement(dateElement).pause(5000).build().perform();
        String actualDate = actualDateLocator.getText();
        actualResultOfComment = formatActualDate(actualDate, nameOfCommenter);
    }

    public void assertOfCreatedComment(){
        expectedResultOfComment = formatExpectedDate();
        Assert.assertEquals(actualResultOfComment, expectedResultOfComment);
    }

    //Formatting date and time of created comment
    private String formatActualDate(String Date, String name){
        String[] parts = Date.split("[:]{1}[0-9]{2}\\s");
        return parts[0] + " " + name;
    }

    //Taking current date of time from computer
    private String formatExpectedDate(){
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E MMM dd yyyy kk:mm",  Locale.ENGLISH);
        String result = formatForDateNow.format(date) + " TesterNikita";
        return result;
    }
}
