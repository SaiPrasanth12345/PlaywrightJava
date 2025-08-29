import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

public class VisibleElements {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        BrowserContext context = browser.newContext();

        Page page = context.newPage();
        page.navigate("https://www.amazon.in/deals?ref_=nav_cs_gb");


        // check for visible elements - Using xpath
        //int mobilesList =  page.locator("//div[contains(@class,'GridRow-module')]//p[contains(@id,'title')] >> visible = true").count();

        int btnCOunt =  page.locator("button:visible").count();
        System.out.println("btnCOunt: "+ btnCOunt);

        // to get text of a button
        String text =  page.locator("xpath = //button[@data-csa-c-element-id='filter-bubble-deals-collection-lightning-deals'] >> visible=true").textContent();
        System.out.println("text: "+ text);

        // to get all visible mobiles list
        Locator loc = page.locator("xpath = //div[@data-testid='virtuoso-item-list']//a >> visible=true");
        System.out.println("Mobiles List Count : "+ loc.count());

        for(int i=0; i<loc.count(); i++){
            System.out.println("Mobiles List: "+ loc.nth(i).getAttribute("href"));
        }



    }
}
