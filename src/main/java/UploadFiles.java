import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import  com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class UploadFiles {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();


        page.navigate("https://www.pdfgear.com/pdf-to-word/");

        //Upload files only works for types with input[type = file]

        //single file upload
        page.setInputFiles("input#file1", Paths.get("./sampleUploadFiles/samplePDFDocUpload.pdf"));
        System.out.println("Single File uploaded");

        page.waitForLoadState(LoadState.LOAD);
        Locator fileUploadsuccessful = page.locator("//div[@class='pdfcvert-complete-ready']/h4");

        // waiting for locator to be present
        fileUploadsuccessful.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        System.out.println("File uploaded: "+ fileUploadsuccessful.textContent());
        assertThat(fileUploadsuccessful).hasText("Your file is ready");

        //Multiple files upload
        Path[] multiplefilesUpload = new Path[] {Paths.get("./sampleUploadFiles/sampleUpload.txt"),
                Paths.get("./sampleUploadFiles/samplePDFDocUpload.pdf"),
                Paths.get("./sampleUploadFiles/carotHideScrnscht.png")};

        page.navigate("https://www.htmlelements.com/demos/fileupload/multiple/index.htm");
        page.setInputFiles("input[smart-id=browseInput]", multiplefilesUpload);

        Locator multiplefileUpload = page.locator("div[role='listitem'] span[class='smart-item-name']");

        System.out.println("Files uploaded: "+ multiplefileUpload.count());
        multiplefileUpload.allTextContents().forEach(file -> System.out.println("File Name: "+file));
        // validate if 3 files are uploaded
        assertThat(multiplefileUpload).hasCount(3);

        Thread.sleep(5000);

        // Clear all files Uploaded, new Path[0] - new array with 0 elemnts
        // Currently this method is not working, as setInputFiles method is just adding files in this URL, but not removing & adding again
        page.setInputFiles("input[smart-id=browseInput]", new Path[0]);

        Locator emptyFiles = page.locator("div[role='listitem'] span[class='smart-item-name']");
        System.out.println("Files uploaded: "+ emptyFiles.count());
        //assertThat(emptyFiles).hasCount(0);

        Thread.sleep(5000);

        //Runtime File Upload
        page.reload();
        page.setInputFiles("input[smart-id=browseInput]",
                                    new FilePayload("runtimefile.txt", "text/plain",
                                            "This is the COntent of the file".getBytes(StandardCharsets.UTF_8) ));

        Locator runtimefileUpload = page.locator("div[role='listitem'] span[class='smart-item-name']");
        System.out.println("Files uploaded: "+ runtimefileUpload.count());
        System.out.println("File uploaded: "+ runtimefileUpload.textContent());
        assertThat(runtimefileUpload).hasText("runtimefile.txt");

        page.close();
        browser.close();
        playwright.close();

    }
}
