import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class Playwright_Pause_Tracing {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setChannel("chrome");
        options.setHeadless(false);
        Browser browser = playwright.chromium().launch(options);
        com.microsoft.playwright.BrowserContext context = browser.newContext();

        // Start tracing -> setting sreenshots & snapshots options
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));


        Page page = browser.newPage();

        //go to a particular URL
        Response res = page.navigate("https://www.amazon.in/");

        //page.pause() - Open the debugger for Record & playback
        page.pause();

        // code taken from recording
        page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).click();
        page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).fill("apple");
        page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).press("ArrowDown");
        page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).press("Enter");
        //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sponsored Ad - Apple Watch SE")).first().click();
        //page.getByText("Apple Watch SE (2nd Gen, 2023").click();

        //Stop tracing & save it to tracing.zip files
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("tracing.zip")));

        browser.close();
        playwright.close();
    }
}
