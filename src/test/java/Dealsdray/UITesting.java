package Dealsdray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UITesting {

    public WebDriver driver;
    public String folderPath;

    
	@BeforeClass
    public void setUp() throws InterruptedException {
    	/*String [] browsers= {"Chrome","FireFox","Edge"};
		WebDriver driver=null;
		for(int i=0;i<browsers.length;i++) 
		{
			//capture position data
			 String browsername=browsers[i];
			 if(browsername.equalsIgnoreCase("Chrome"))
			 {
				 driver= new ChromeDriver();
				 Thread.sleep(5000);
			 }
			 else if(browsername.equalsIgnoreCase("FireFox"))
			 {
				 driver= new FirefoxDriver();
			 }
			 else if(browsername.equalsIgnoreCase("Edge"))
			 {
				  driver=new EdgeDriver();
			 }
		*/
        driver = new ChromeDriver();

        // Set browser resolution (Example: Desktop 1920x1080)
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Folder to save screenshots
       folderPath = "screenshots/Chrome/1920x1080/";
        new File(folderPath).mkdirs();
    }
    
    @Test
    public void testSitemapScreenshots() throws IOException {
        // Parse the sitemap and get the first 5 URLs
        String sitemapUrl = "https://www.getcalley.com/page-sitemap.xml";
        Document doc = Jsoup.connect(sitemapUrl).get();
        Elements locElements = doc.select("url > loc");
        List<String> urls = locElements.eachText().subList(0, 5);  // Get the first 5 URLs

        // Open each URL and take a screenshot
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            driver.get(url);

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "screenshot-" + (i + 1) + ".png";
            FileUtils.copyFile(screenshot, new File(folderPath + fileName));
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
