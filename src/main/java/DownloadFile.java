import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadFile {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(false);
        options.setChannel("chrome");
        Browser browser = playwright.chromium().launch(options);
        Page page = browser.newPage();

        page.navigate("https://www.examplefile.com/document/pdf/1-mb-pdf");
        page.waitForLoadState(LoadState.LOAD);

        Download downloadfile = page.waitForDownload(() -> {
            page.locator("a[title='1 MB PDF Download']").click();
        });

        // to cancel the download
        //downloadfile.cancel();

        //to check if any failure during download
        System.out.println("File download failure ? "+ downloadfile.failure());

        //to check if any failure download successful
        System.out.println("File download successful: "+ ((downloadfile.failure()==null)? "true": "false"));

        //to check the temporary path where file is downloaded
        System.out.println("Temporary file download Path: "+ downloadfile.path());

        //to save the download to a particular location
        Path saveFilePath = Paths.get("./fileDownloads/sampleFiledownload.pdf");
        downloadfile.saveAs(saveFilePath);

        //to get the file name
        System.out.println("File name: "+ downloadfile.suggestedFilename());

        // URL of the downloaded
        System.out.println("Download file URL: "+ downloadfile.url());

        page.close();
        browser.close();
        playwright.close();

    }
}
