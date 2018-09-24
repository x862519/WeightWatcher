package Question3;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Question2 {
	public static void main(String[] args) {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.gecko.driver","C:\\eclipse\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
    	
		/*
         * #Step 1
         *  launch Fire fox and direct it to the Base URL
         */
        String baseUrl = "https://www.weightwatchers.com/us/";
        driver.get(baseUrl);
        
        /*
         * #Step 2
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        String expectedTitle = "Weight Loss Program, Recipes & Help | Weight Watchers";
        String actualTitle = "";
        
        // get the actual value of the title
        actualTitle = driver.getTitle();
        
        if (actualTitle.contentEquals(expectedTitle)){
        	System.out.println("Test Passed. Title matched");
        } else {
            System.out.println("Test Failed.");
            System.out.println("actualTitle:"+actualTitle);
        }
        
        /*
         * #Step 3
         * Find the "find a meeting" link and click it
         */
        WebElement FindAMeeting = driver.findElement(By.className("find-a-meeting"));
        
        FindAMeeting.click();
        
        /*
         * #Step 4
         * compare the actual title of the page of Find a Meeting with the expected one and print
         * the result as "Passed" or "Failed"
         */        
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
        
        // get the actual value of the title
        expectedTitle = "Get Schedules & Times Near You";

        actualTitle = driver.getTitle();
        
        
        if (actualTitle.contains(expectedTitle)){
            System.out.println("Test Passed. Title matched");
        } else {
            System.out.println("Test Failed.");
            System.out.println("expectTitle:"+expectedTitle);
            System.out.println("actualTitle:"+actualTitle);
        }
        
        /*
         * #Step 5
         * Find the search field and enter zip code
         */
        
        WebElement SearchField = driver.findElement(By.id("meetingSearch"));
        WebElement SearchButton = driver.findElement(By.id("ela-mfsr:mf-find-btn"));
        
        SearchField.sendKeys("10011");
        SearchButton.click();
       
        /* 
         * #Step 6
         * Print the title of the first result and the distance
         */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
        
		WebElement SearchResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/ui-view/ui-view/div/div[2]/div/div[1]/div/div[1]/result-location/div/div[1]/a"));		
		WebElement Location = SearchResult.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/ui-view/ui-view/div/div[2]/div/div[1]/div/div[1]/result-location/div/div[1]/a/location-address/div/div/div[1]/div[1]/span"));
		WebElement Distance = SearchResult.findElement(By.className("location__distance"));
		String locationInSearchPage=Location.getText();
		System.out.println("Location: "+locationInSearchPage);
		System.out.println("Distance: "+Distance.getText());
		
		
        
        /* 
         * #Step 7
         * Click on the first search result
         * verify displayed location name matches with the name of the first searched result that was clicked.
         */
        Location.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
        
        //find the first search result
        WebElement LocationTitle = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/ui-view/ui-view/div[1]/div/div/div[1]/div[2]/div[2]/div/location-address/div/div/div[1]/div/span"));
        
        //Verify the location name is matched or not
        if (LocationTitle.getText().equals(locationInSearchPage)){
            System.out.println("Test Passed. Location name matched");
        } else {
            System.out.println("Test Failed.");
            System.out.println("location name in search page:"+locationInSearchPage);
            System.out.println("location name in detail page:"+LocationTitle.getText());
        }
        
        /* 
         * #Step 8
         * print TODAY¡¯s hours of operation
         */
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); 
        WebElement OPHours = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/ui-view/ui-view/div[1]/div/div/div[2]/div[2]/div[1]/hours-list/ul"));
        
        //Get the hour from the table. Store in a list, the first element starting from Sunday.
        List<WebElement> OPTime=OPHours.findElements(By.className("hours-list-item-hours"));
        
        System.out.println("Today's Hour is:");
        System.out.println(OPTime.get(day-1).getText());
        
        
        
        
        //close Fire fox
        driver.close();
       
    }

}
