import com.microsoft.playwright.*;

public class PlaywrightFirstTest {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        // to Launch chromium browser - Runs in Headless Mode
        //Browser browser =  playwright.chromium().launch();

        // to Launch chromium browser - Runs in non-Headless Mode
        //Browser browser =  playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //browser.newContext(new Browser.NewContextOptions().setViewportSize(1920,1080));

        // to Launch Chrome browser instead of chromium
        BrowserType.LaunchOptions options  = new BrowserType.LaunchOptions();
        options.setChannel("chrome");
        options.setHeadless(false);
        Browser browser = playwright.chromium().launch(options);

        // Open a new page in browser
        Page page = browser.newPage();

        //go to a particular URL
        Response res = page.navigate("https://www.amazon.in/");
        // response of get request from Amazon.in -> System.out.println("Response text from URL is: "+res.text());

        // get the current page title
        String title = page.title();
        System.out.println("Page title is : "+title);

        //get the current URL
        String url = page.url();
        System.out.println("Page URL is : "+url);

        //close browser & playwright server
        browser.close();
        playwright.close();



    }

}
