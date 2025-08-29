import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class AlertOnceDialog {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

        // we need to handle alert before the click action/handler -> as playwright emits dialog synchronously with dialog appearance
        page.onceDialog( alert -> {
            System.out.println("Message is :" +alert.message());
            alert.accept();
        });
        page.getByText("Click Me").first().click();

        //Confiramtion Alert Box
        page.onceDialog( alert -> {
            System.out.println("Message is :" +alert.message());
            alert.dismiss();
        });
        page.getByText("Click Me").nth(1).click();
        Locator dismissAlert = page.getByText("You pressed Cancel!");
        System.out.println("Message visible :" +dismissAlert.isVisible());
        assertThat(dismissAlert).isVisible();

        //Prompt Box
        page.onceDialog( alert -> {
            System.out.println("Message is :" +alert.message());
            System.out.println("Default value inside alert input box is :" +alert.defaultValue());
            System.out.println("Type of alert is :" +alert.type());
            alert.accept("Casablanca");
        });
        page.getByText("Click Me").last().click();
        Locator alertVal = page.getByText("You have entered 'Casablanca' !");
        System.out.println("Message visible :" +alertVal.isVisible());
        assertThat(alertVal).isVisible();

       // Set HTTP credentials in browser context & open a page
        Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();
        newContextOptions.setHttpCredentials("admin", "admin");
        BrowserContext HttpAutherticationContext = browser.newContext(newContextOptions);
        Page page2 = HttpAutherticationContext.newPage();

        page2.navigate("https://the-internet.herokuapp.com/");
        page2.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Digest Authentication")).click();

        Locator digestAuthHeader = page2.getByText("Digest Auth");
        System.out.println("Message visible :" +digestAuthHeader.isVisible());
        assertThat(digestAuthHeader).isVisible();

        Locator successMessage = page2.locator("div.example p");
        System.out.println("Message visible :" +successMessage.textContent());
        assertThat(successMessage).containsText("Congratulations! You must have the proper credentials");

        context.close();
        HttpAutherticationContext.close();
        browser.close();
        playwright.close();

    }
}
