import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;

public class ShadowDOMElements {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        BrowserContext context = browser.newContext();

        Page page = context.newPage();
        page.navigate("https://books-pwakit.appspot.com/");

        // Below Image is a Shadow DOM element
        page.locator("input[aria-label='Search Books']").fill("Prasanth");

        // to get books text in shadow root element
        String value = page.locator("div[class='books-desc']").textContent();
        System.out.println("Value: "+ value);


    }
}
