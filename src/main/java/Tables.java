import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Locator;

import java.util.List;

public class Tables {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();

        page.navigate("https://primeng.org/");

        //table all rows locator
        Locator rows = page.locator("table#pn_id_1-table tr");

        //to get the row of name having 'Art Venere' , :scope - checks for whole table, setHasText search for locator having text
        Locator targetRow = rows.locator(":scope", new Locator.LocatorOptions().setHasText("Art Venere"));

        //and perform select checkbox - Give locator from the target row itself
        targetRow.locator("input[type='checkbox']").click();

        //check status, balance, Agent
        String status = targetRow.locator("span[class='p-tag-label']").textContent();
        System.out.println("Status: "+ status);

        String balance = targetRow.locator("td").nth(4).textContent();
        System.out.println("Balance: "+ balance);

        String Agent = targetRow.locator("td").nth(5).textContent();
        System.out.println("Agent: "+ Agent);
        System.out.println("-------------------------------------------");

        // Print all the table
        Locator allRows = page.locator("table#pn_id_1-table tr");

        List<String> tableDetails = allRows.locator(":scope").allInnerTexts();
        tableDetails.forEach(data -> System.out.print(data));

        page.close();
        browser.close();
        playwright.close();
    }

}
