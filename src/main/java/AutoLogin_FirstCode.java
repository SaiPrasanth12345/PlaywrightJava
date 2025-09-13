import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AutoLogin_FirstCode {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        BrowserContext context = browser.newContext();
        //Page page = context.newPage();


        // Run this script only for the first time when the loginScript.json file is not created.
        /*
        //Login to the website
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByLabel("E-Mail Address").fill("koushik350@gmail.com");
        page.getByPlaceholder("Password").fill("Pass123$");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.waitForLoadState(LoadState.LOAD);

        // store this script to loginscript.json file -
        // Next time on launch, using the loginscript.json, the application will get opened in logged in state - check AutoLogin_SecondCode.java
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("./scripts/loginScript.json")));
        page.waitForLoadState(LoadState.LOAD);
         */


        //Once the loginScript.json is created, then add the script to the Browser context,
        // so that the Login occurs automatically on loading the URL next time in new browser
        BrowserContext context2 = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get("./scripts/loginScript.json")));
        Page page2 = context2.newPage();
        page2.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page2.locator("//li[contains(@class,'dropdown')]//span[contains(.,'My account')]").hover();

        // To validate Logout button is present
        Locator logout = page2.locator("//span[contains(.,'Logout')]");
        System.out.println("Logout is Visible:" + logout.isVisible());
        assertThat(logout).isVisible();

        Thread.sleep(5000);

        page2.close();
        browser.close();
        playwright.close();
    }

}
