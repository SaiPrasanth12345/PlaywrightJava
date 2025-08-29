import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;

public class Selector {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        BrowserContext context = browser.newContext();

        Page page = context.newPage();

        page.navigate("https://www.amazon.in/");

        //Selector using visible text - "'visible text value'"
        Locator returns2 = page.locator("'Fashion'");
        System.out.println("Returns2 Text Content : "+returns2.textContent());
        // returns.click();

        //Selector using visible text - "text=visible text value"
        Locator returns = page.locator("text = Returns & Orders");
        System.out.println(returns.textContent());
        returns.click();

        page.goBack();

        //Selector using visible text - "text= Partial Visible text"
        Locator returns3 = page.locator("text = Returns &");
        System.out.println("Returns3 Text Content : "+returns3.textContent());

        //selector using ancestor -child -> li/div/<a>Fashion</a>
        Locator fashion = page.locator("li a:has-text('Fashion')");
        System.out.println("Total Fashion elements count: "+fashion.count());
        System.out.println(fashion.first().textContent());
        //fashion.click();

        //selector using parent -child -> li/div/<a>Fashion</a>
        Locator fashion2 = page.locator("li div a:has-text('Fashion')");
        System.out.println("Total Fashion2 elements count: "+fashion2.count());
        System.out.println(fashion2.first().textContent());

        //selector using child -> li/div/<a>Fashion</a>
        Locator fashion3 = page.locator("a:has-text('Fashion')");
        System.out.println("Total Fashion3 elements count: "+fashion3.count());
        System.out.println(fashion3.first().textContent());

        //selector using parent attribute - child -> li/div class = "nav-div" /<a>Fashion</a>
        Locator fashion4 = page.locator("li div.nav-div a:has-text('Fashion')");
        System.out.println("Total Fashion4 elements count: "+fashion4.count());
        System.out.println(fashion4.first().textContent());

        //selector using child class attribute -> li/div class = "nav-div" /<a data-csa-c-slot-id="nav_cs_9">Fashion</a>
        Locator fashion5 = page.locator("a.nav-a  ");
        System.out.println("Total Fashion5 elements count: "+fashion5.count());
        System.out.println(fashion5.nth(17).textContent());

        //selector using child attribute - class attribute not working
        Locator fashion6 = page.locator("button.false");
        System.out.println("Total Fashion6 elements count: "+fashion6.count());







    }
}
