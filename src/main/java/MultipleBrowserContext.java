import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class MultipleBrowserContext {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        //First browser context
        BrowserContext context1 = browser.newContext();
        Page page1  = context1.newPage();

        page1.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");

        //Login to the website
        page1.getByLabel("E-Mail Address").fill("koushik350@gmail.com");
        page1.getByPlaceholder("Password").fill("Pass123$");
        page1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page1.waitForLoadState(LoadState.LOAD);

        //Verify if Logged in successfully
        Locator loginSuccess = page1.getByText(" Edit your account information");
        System.out.println("Login Visible: "+loginSuccess.isVisible());
        assertThat(loginSuccess).isVisible();

        //create a new tab & verify the login is also successful on opening the URL
        Page page2 = page1.context().newPage();
        page2.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        //Verify if user is Logged in new tab also
        Locator loginSuccess2 = page2.getByText(" Edit your account information");
        System.out.println("Login Visible in new Page: "+loginSuccess2.isVisible());
        assertThat(loginSuccess2).isVisible();

        // open a new Window/context & validate the user is not logged in on opening the URL
        BrowserContext context2 =  browser.newContext();
        Page page3 = context2.newPage();

        page3.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        Locator loginSuccess3 = page3.getByText(" Edit your account information");
        System.out.println("Login Visible in new Context: "+loginSuccess3.isVisible());
        assertThat(loginSuccess3).not().isVisible();

        //we can use count to validate locator not persent
        System.out.println("Login locator count in new Context: "+loginSuccess3.count());
        assertThat(loginSuccess3).hasCount(0);

        context1.close();
        context2.close();
        playwright.close();


    }

}
