import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MainTests extends WebDriverSettings {


    @DataProvider(name = "LoginAndPassword")
    public static Object[][] credentials() {
        return new Object[][]  {{ "TesterNikita" , "Test#Test" }};
    }

    @DataProvider(name = "Comment")
    public static Object[] message() {
        return new Object[]  {"so interesting"};
    }

    @Test(groups = "userTest", dataProvider = "LoginAndPassword")
    public void testLogin(String userName, String password)  {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.fillLoginField(userName);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSignInButton();
        loginPage.assertLogin();
    }

    @Test(groups = "userTest",
            priority = 1,
            dependsOnMethods = "testLogin")
    public void upvote(){
         HomePage homePage = PageFactory.initElements(driver, HomePage.class);
         homePage.upvote();
         homePage.assertUpvote();
    }

    @Test(groups = "userTest",
            priority = 1,
            dependsOnMethods = "testLogin")
    public void downvote(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.downvote();
        homePage.assertDownvote();
    }

    @Test(groups = "userTest",
            priority = 2,
            dependsOnMethods = "testLogin")
    public void toSeeSubreddits(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.toSeeSubreddits();
        homePage.assertToSeeSubreddits();
    }

    @Test(groups = "userTest",
            priority = 3,
            dependsOnMethods = "testLogin")
    public void viewOneOfSubreddits(){
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickOnCommunity();
        homePage.assertViewOneOfSubreddits();
    }

   @Test(groups = "userTest",
           priority = 4,
           dependsOnMethods = "testLogin",
           dataProvider = "Comment")
    public void createComment(String commentMessage) {
        CommentPage commentPage = PageFactory.initElements(driver, CommentPage.class);
        commentPage.clickCommentButton();
        commentPage.fillCommentField(commentMessage);
        commentPage.sendCommentButtonClick();
        commentPage.openCommentInNewTab();
        commentPage.getDataOfComment();
        commentPage.assertOfCreatedComment();

        
    }
}
