import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import java.util.List;
import java.util.function.Predicate;

public class MultipleWindowHandle {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");

        Page windowsLoaded = page.waitForPopup(
                new Page.WaitForPopupOptions().setPredicate(pages -> pages.context().pages().size() == 3),
                () -> { page.getByText("Follow Twitter & Facebook").click(); });

        List<Page> tabs = windowsLoaded.context().pages();
        System.out.println("Total Windows Loaded: " + tabs.size());

        for(Page windowPage: tabs) {
            windowPage.waitForLoadState(LoadState.LOAD);
            System.out.println("Title:"+windowPage.title());
        }

        browser.close();
        playwright.close();
    }
}
