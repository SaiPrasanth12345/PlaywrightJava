import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

public class WindowHandling {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");

        // click on Follow on Twitter Link -> opnes a new popup
        Page linkedInPage = page.waitForPopup(() -> {
                                    page.getByText("  Follow us On Linkedin ").click();
                                });

        // wait for new window to load - LambdaTest (@lambdatesting) / X
        linkedInPage.waitForLoadState(LoadState.LOAD);
        System.out.println("LinkedIn Page Title : "+ linkedInPage.title());

        linkedInPage.locator("//a[contains(text(),'Sign in')]").click();

        browser.close();
        playwright.close();

    }
}
