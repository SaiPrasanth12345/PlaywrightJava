import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class BrowserContext {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Browser Context 1:
        com.microsoft.playwright.BrowserContext brcx1 = browser.newContext();
        Page page1 = brcx1.newPage();
        page1.navigate("https://www.amazon.in/");
        System.out.println("Browser context 1 title: "+ page1.title());

        // Browser Context 2:
        com.microsoft.playwright.BrowserContext brcx2 = browser.newContext();
        Page page2 = brcx2.newPage();
        page2.navigate("https://www.flipkart.com/");
        System.out.println("Browser context 2 title: "+ page2.title());

        // click search bar from context 1
        page1.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).click();
        page1.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).fill("apple");

        //Close browser context 1
        page1.close();
        brcx1.close();

        //Close browser context 2
        page2.close();
        brcx2.close();

    }
}