import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;

public class Locator {

        public static void main(String[] args) {

            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            com.microsoft.playwright.BrowserContext brcx = browser.newContext();

            Page page = brcx.newPage();
            page.navigate("https://www.amazon.in/");

            //for single element:
            com.microsoft.playwright.Locator mobile =  page.locator("text = Mobiles");
            mobile.click();

            // for Multiple elements:
            //com.microsoft.playwright.Locator nothingMobiles =  page.locator("text = Nothing");

            //gets exceptions - due to multiople elements present
            //nothingMobiles.click();

            //int eleCount = nothingMobiles.count();
            //System.out.println("Nothing - element count = "+eleCount);

            //List<String> text = nothingMobiles.allTextContents();
            //text.forEach(i -> System.out.println(i));

            com.microsoft.playwright.Locator allBtn =  page.locator("//span[.='All']");
            allBtn.click();

            com.microsoft.playwright.Locator trendingOptions =  page.locator("//div[.='Trending']/following-sibling::ul/li");

            System.out.println("Trending = "+trendingOptions.first().textContent());

            // Count of elements
            int eleCount = trendingOptions.count();
            System.out.println("Trending - element count = "+eleCount);

            // getting text of all elements
            List<String> text = trendingOptions.allTextContents();
            text.forEach(i -> System.out.println(i));

            //getting text using loops
            for(int i=0; i<eleCount ; i++) {
                System.out.println("Trending - element count using loop= "+trendingOptions.nth(i).textContent());
            }

            //close browser & playwright server
            browser.close();
            playwright.close();




        }

}
