import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//button[contains(@id, 'upvote')]")
    private WebElement upvoteButtonLocator;

    @FindBy(xpath = "//button[@aria-label='downvote']")
    private WebElement downvoteButtonLocator;

    @FindBy(xpath = "//div[contains(@class, '_3r')]")
    private WebElement headerButtonLocator;

    private By communityLocator = By.id("focus-subt5_2qh1i");

    private By wallWithPostsLocator = By.xpath("//div[contains(@class, 'rpB')]");

    public HomePage(WebDriver driver){
        this.driver = driver;
        wait =  new WebDriverWait(driver, 30);
    }

    public void upvote(){
        upvoteButtonLocator.click();
    }

    public void downvote(){
        downvoteButtonLocator.click();
    }

    public void toSeeSubreddits(){
        headerButtonLocator.click();

    }

    public void clickOnCommunity(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(communityLocator));
        driver.findElement(communityLocator).click();
    }

    public void assertViewOneOfSubreddits(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(wallWithPostsLocator));
        Boolean actualSubreddits = driver.findElement(wallWithPostsLocator).isDisplayed();
        Assert.assertEquals(actualSubreddits, Boolean.TRUE);
    }

    public void assertUpvote(){
        String actualUpvote = upvoteButtonLocator.getAttribute("aria-pressed");
        Assert.assertEquals(actualUpvote, "true");
    }

    public void assertDownvote(){
        String actualDownvote = downvoteButtonLocator.getAttribute("aria-pressed");
        Assert.assertEquals(actualDownvote, "true");
    }

    public void assertToSeeSubreddits(){
        Boolean actualSubreddits = driver.findElement(communityLocator).isDisplayed();
        Assert.assertEquals(actualSubreddits, Boolean.TRUE);
    }
}
