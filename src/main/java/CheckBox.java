import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class CheckBox {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        LaunchOptions options = new LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        Locator singleCheckBox = page.getByText("Click on check box");

        // validate checkbox is not checked
        assertThat(singleCheckBox).not().isChecked();

        // click checkbox & validate
        singleCheckBox.check();
        assertThat(singleCheckBox).isChecked();
        assertThat(page.locator("//h2[.='Single Checkbox Demo']/following-sibling::p")).hasText("Checked!");


        // MultipleCheckBox
        Locator checkAll = page.getByText("Check All");
        checkAll.click();

        System.out.println("Multiple checkbox Validation");
        assertThat(page.locator("//h2[.='Multiple Checkbox Demo']/..//input[@name='option1']")).isChecked();
        assertThat(page.locator("//h2[.='Multiple Checkbox Demo']/..//input[@name='option2']")).isChecked();
        assertThat(page.locator("//h2[.='Multiple Checkbox Demo']/..//input[@name='option3']")).isChecked();
        assertThat(page.locator("//h2[.='Multiple Checkbox Demo']/..//input[@name='option4']")).isChecked();
        System.out.println("Multiple checkbox Validation Done");

        //DisabledCheckBox
        Locator disabledOption3 = page.locator("//h2[.='Disabled Checkbox Demo']/following-sibling::div//label[.=' Option 3']//input");

        System.out.println("Check option 3 is disabled:" +disabledOption3.isDisabled());
        // Throws exception when try to perform click() / check() an disabled checkbox
        //disabledOption3.check();
        //disabledOption3.click();
        
        // Doesnt throw exception for isChecked() method for disabled checkbox
        System.out.println("Check option 3 is checked: "+ disabledOption3.isChecked());








    }
}
