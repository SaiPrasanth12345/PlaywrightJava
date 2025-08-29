import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;

public class Frame {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        BrowserContext context = browser.newContext();

        Page page = context.newPage();

        page.navigate("https://www.formsite.com/templates/registration-form-templates/appointment-form-template/");

        // to click on image to get iframe
        page.locator("//img[@title='Appointment-Forms-and-Examples']").click();

        // to get into iframe
        FrameLocator frame = page.frameLocator("//iframe[contains(@id,'frame-one')]");

        // Filling Name inside Name field in iframe
        Locator nameField = frame.locator("//input[@id='RESULT_TextField-1']");
        nameField.fill("Prasanth Sai");


        //browser.close();
        //playwright.close();

    }
}
