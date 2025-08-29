import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;

public class AlertOnDialog {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

        page.onDialog( alert -> {
            System.out.println("Message is :" +alert.message());
            alert.accept();
        });

        // For all the alerts generated, the 'OnDialog' script gets triggered
        page.getByText("Click Me").first().click();
        System.out.println("Clicked 1st alert box");

        page.getByText("Click Me").nth(1).click();
        System.out.println("Clicked 2nd alert box");

        page.getByText("Click Me").first().click();
        System.out.println("Clicked 1st alert box");

        // Using Once Dialog, after using OnDialog() will throw an exception saying Alert already Handled
        /*
        page.onceDialog( alert -> {
            System.out.println("Message is :" +alert.message());
            alert.accept();
        });
        page.getByText("Click Me").first().click();
        */

        playwright.close();

    }
}
