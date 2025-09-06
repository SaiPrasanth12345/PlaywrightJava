import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import java.awt.*;

public class ScreenMaximize {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        //SetViewportSize - set as int values in 1080*720
        //BrowserContext context = browser.newContext( new Browser.NewContextOptions().setViewportSize(1080, 720));

        // To get the screensize dynamically, we need to use Toolkit
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        BrowserContext context = browser.newContext( new Browser.NewContextOptions().setViewportSize(width, height));

        Page page = context.newPage();
        page.navigate("https://www.amazon.in/");
        page.waitForLoadState(LoadState.LOAD);
        Thread.sleep(5000);

    }
}
