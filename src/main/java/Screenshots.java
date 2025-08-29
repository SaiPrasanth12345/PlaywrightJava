import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Screenshots {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo");


        ScreenshotOptions scrnshtOps = new ScreenshotOptions();
/*
        // page view screenshot
        page.screenshot(scrnshtOps.setPath(Paths.get("./screenshots/viewPage.png")));

        // Full Page Screenshot
        page.screenshot(scrnshtOps.setFullPage(true)
                                   .setPath(Paths.get("./screenshots/fullPage.jpg")));

        //Locator Screenshot
        Locator country = page.locator("//label[.='Select Country :']").last();
        Locator.ScreenshotOptions locatorScrnscht = new Locator.ScreenshotOptions();
        country.screenshot(locatorScrnscht.setPath(Paths.get("./screenshots/locatorScrnscht.png")));

        // Take whole header Screenshot
        Locator header = page.locator("#header");
        header.screenshot(locatorScrnscht.setPath(Paths.get("./screenshots/header.jpg")));

        // Mask Header & locator in page screenshot
        List<Locator> maskList = new ArrayList<>();
        maskList.add(header);
        maskList.add(country);
        maskList.add(page.locator("//div[.='Select Multiple Values with search']/following-sibling::div[@class='py-20 px-10']"));

        page.screenshot(scrnshtOps.setPath(Paths.get("./screenshots/maskedHeaders.jpg"))
                .setMask(maskList));



        // Mask locator in page screenshot
        Locator maskLoc = page.locator("//label[.='Select State :']/following-sibling::span");
        maskLoc.scrollIntoViewIfNeeded();

        page.screenshot(scrnshtOps.setPath(Paths.get("./screenshots/maskedLocator.jpg"))
                        .setFullPage(false)
                        .setMask(Arrays.asList(maskLoc)));
        */

        //Caret Hide Screenshot
        Locator carotLink = page.locator("//select[@id='country']/following-sibling::span");
        carotLink.click();
        page.screenshot(scrnshtOps.setCaret(ScreenshotCaret.HIDE).
                        setPath(Paths.get("./screenshots/carotHideScrnscht.png")));

        //Caret show Screenshot
        page.screenshot(scrnshtOps.setCaret(ScreenshotCaret.INITIAL).
                setPath(Paths.get("./screenshots/carotShowScrnscht.jpg")));



        page.close();
        playwright.close();




    }
}
