import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;

public class NestedFrameAndVideoRecord {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);

        // To record a video
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./videos")));
        Page page = context.newPage();


        //single Frame
        page.navigate("https://www.formsite.com/templates/registration-form-templates/appointment-form-template/");
        page.click("#imageTemplateContainer");

        page.waitForLoadState(LoadState.LOAD);

        FrameLocator frame = page.frameLocator("#frame-one338769475");
        frame.locator("#RESULT_TextField-1").fill("sjdbfjds");

        Locator email = frame.locator("#RESULT_TextField-2");
        email.fill("sjdbfjds@mail.com");

        // We can directly switch to main frame without doing a switchTo() in playwright
        Locator formsite = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Why Formsite"));
        formsite.scrollIntoViewIfNeeded();
        formsite.click();

        //For Multiple frames
        page.navigate("https://letcode.in/frame");

        FrameLocator mainFrame = page.frameLocator("#firstFr");
        mainFrame.getByPlaceholder("Enter name").fill("jhfbhsdf");
        mainFrame.getByPlaceholder("Enter email").fill("jhfbhsdf@mail1.com");

        //Even though the placeholder name is same as 'Enter email', no exception is thrown, as it is in different frames
        FrameLocator nestedFrame = mainFrame.frameLocator("iframe[src='innerframe']");
        nestedFrame.getByPlaceholder("Enter email").fill("jhfbhsdf@mail2.com");

        //Hover on the products
        page.getByText("Products").first().hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Ortoni Report ")).click();

        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
