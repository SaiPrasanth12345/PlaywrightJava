import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.Locator;

public class InputFunctions {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        //page.pause();
        page.waitForLoadState(LoadState.LOAD);
        // Enter text into the Single Input Field
        page.locator("//input[@id='user-message']").type("This is a Tester");

        // Enter text into the two input fields
        page.locator("id=sum1").fill("This is a Tester - first value");
        page.locator("#sum2").pressSequentially("This is a Tester");

        // To get text context
        page.locator("id=showInput").click();
        Locator singleInputText = page.locator("id=message");
        PlaywrightAssertions.assertThat(singleInputText).hasText("This is a Tester");

        page.locator("//button[.='Get Sum']").click();
        Locator DoubleInputText = page.locator("id=addmessage");
        PlaywrightAssertions.assertThat(DoubleInputText).hasText("Entered value is not a number");

        // To validate the Entered Text Fields-
        String inputVal = page.locator("//input[@id='user-message']").inputValue();
        System.out.println("INput Value- Single Text: " + inputVal);

        //textContecnt() - giving empty value
        String inputVal2 = page.locator("id=sum1").textContent();
        System.out.println("Text Content: " + inputVal2);
        String inputVal3 = page.locator("id=sum1").inputValue();
        System.out.println("INput Value- DOuble Text: " + inputVal3);

        //innerText() - giving empty value
        String inputVal4 = page.locator("id=sum2").innerText();
        System.out.println("Inner Text: " + inputVal4);
        String inputVal5 = page.locator("id=sum2").inputValue();
        System.out.println("INput Value- DOuble Text: " + inputVal5);

        //playwright.close();

    }
}
