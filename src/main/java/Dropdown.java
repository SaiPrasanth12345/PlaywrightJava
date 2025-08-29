import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import  com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.Page.LocatorOptions;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Dropdown {

    public static void main(String[] args) {

        Page page = Playwright.create().chromium().launch( new LaunchOptions().setHeadless(false)).newPage();
        page.navigate("https://letcode.in/dropdowns");

        Locator fruits = page.locator("#fruits");

        //select by value
        fruits.selectOption("3");

        Locator selectedFruit = page.locator("p.subtitle").first();
        System.out.println("Selection Option: "+ selectedFruit.textContent());
        assertThat(selectedFruit).containsText("Banana");

        //select by Label/visible Text
        fruits.selectOption("Mango");
        System.out.println("Selection Option: "+ selectedFruit.textContent());
        assertThat(selectedFruit).containsText("Mango");

        //select by Label/visible Text - 2nd method
        fruits.selectOption(new SelectOption().setLabel("Orange"));
        System.out.println("Selection Option: "+ selectedFruit.textContent());
        assertThat(selectedFruit).containsText("Orange");

        //select by Index
        fruits.selectOption(new SelectOption().setIndex(5));
        System.out.println("Selection Option: "+ selectedFruit.textContent());
        assertThat(selectedFruit).containsText("Pine Apple");

        // to get all values present in the dropdown
        Locator options = fruits.locator("option");
        List<String> optionsValues = options.allInnerTexts();
        optionsValues.forEach(i -> System.out.println("Options : "+ i));


        // Multiple Dropdown
        page.navigate("https://demoapps.qspiders.com/ui/dropdown/multiSelect?sublist=1");
        page.waitForLoadState(LoadState.LOAD);
        Locator multipleSelect = page.locator("#select-multiple-native").first();
        multipleSelect.selectOption(new String[]{"Fjallraven - Foldsac...", "Mens Cotton Jacket..." ,"SanDisk SSD PLUS 1TB..."});

        Locator add = page.locator("//button[.='Add']");
        add.click();

        Locator selectedOptions = page.locator("//tbody/tr/td[1]");
        for(int i=0; i<selectedOptions.count(); i++ ){
            System.out.println("Multiple - Selected Option :"+ selectedOptions.nth(i).textContent());
        }

        //Jquery Dropdown
        page.getByText("Search With Select").click();
        Locator gender = page.locator("//div[@class=' css-t3ipsp-control']");
        gender.click();
        System.out.println("Clicked Gender");
        Locator female = page.locator("//div[@id='react-select-3-listbox']/div", new Page.LocatorOptions().setHasText("female"));
        female.click();

        Locator selectedText = page.locator("//div[@class=' css-1dimb5e-singleValue']");
        // Getting error for selectedText.inputValue() method
        //System.out.println("Selected Text: "+ selectedText.inputValue());
        System.out.println("Selected Text: "+ selectedText.textContent());
        assertThat(selectedText).hasText("female");

    }
}
