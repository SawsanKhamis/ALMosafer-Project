import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
// import org.apache.*;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.support.ui.Select;

public class TestCasesClass extends PrimryClass {

	@BeforeTest
	public void PreTest() {
		driver.get(WebSiteLink);
		driver.manage().window().maximize();
	}
	
	
	@Test (description = "Test Number 2.1")
	public void CheckDefaultLanguageIsEnglish() {
		String ActualLanguage = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]"))
				.getText();
		myAssert.assertEquals(ActualLanguage, ExpectedLanguageEnglish, "The Actual Language is Arabic and The default Language is English");
	}
	
	@Test(description = "Test Number 2.2")
	public void CheckDefaultCurrencyIsSAR() {
		String ActualCurrency = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/div[1]/div/button"))
				.getText();
		myAssert.assertEquals(ActualCurrency, ExpectedCurrency, "The Default Curreny is SAR");
	}
	
	@Test(description = "Test Number 2.3")
	public void CheckContactNumbersIsCorrect() {
		String ActualContactNumber = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[2]/strong"))
				.getText();
		myAssert.assertEquals(ActualContactNumber, ExpectedContactNumber, "The Contact Number is correct");
	}
	
	@Test(description = "Test Number 2.4")
	public void CheckQitafLogoIsDisplayed() {
		WebElement MyFooter = driver.findElement(By.tagName("footer"));
		
		List <WebElement> TheListOfSVGImages = MyFooter.findElements(By.tagName("SVG"));
		
		for(int i=0; i<TheListOfSVGImages.size(); i++) {
			String CheckAttribute = TheListOfSVGImages.get(i).getAttribute("data-testid");
			
			if (CheckAttribute == null){
				continue;
			}
			
			else if (TheListOfSVGImages.get(i).getAttribute("data-testid").contains("Qitaf")) {
				ActualValueOfQitafIfIsThere = true;
			}
			System.out.println(CheckAttribute);
		}
	//	myAssert.assertEquals(ActualValueOfQitafIfIsThere, true);
	}  
	
	
	
	@Test (description = "Test Number 2.5")
	public void CheckHotelsSearchTabNotSelected() {
		
	        String ExpectedAttributeValue = "false";
	
	      	WebElement HotelTab = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]"));
			String ActaulAttributeValue = HotelTab.getAttribute("aria-selected");
	
	myAssert.assertEquals(ActaulAttributeValue, ExpectedAttributeValue);
		}
	
	@SuppressWarnings("deprecation")
	@Test (description = "Test Number 2.6")
	public void Check_The_Departure_Date() {
		Date todaytTime = new Date();
	
		int NumberOfDaysExtra = 1;
		int ActaulDayOfTheMonth = todaytTime.getDate();
		String TheDayOnTheWebsite = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div/div[1]/span[2]")).getText();
		int UpdatedDateAsInteger = Integer.parseInt(TheDayOnTheWebsite);
		
		myAssert.assertEquals(UpdatedDateAsInteger , ActaulDayOfTheMonth + NumberOfDaysExtra);
	}
	
	@SuppressWarnings("deprecation")
	@Test(description = "Test Number 2.7")
	public void Check_The_Return_Date() {
		Date todayTime = new Date();
		
		int NumberOfDaysExtra = 2;
		int ActualDayOfTheMonth = todayTime.getDate();
		String TheDayOfTheMonthOnTheWebsite = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-flights\"]/div/div[2]/div[1]/div/div[3]/div/div/div/div[2]/span[2]"))
				.getText();
		int UpdatedDateAsInteger = Integer.parseInt(TheDayOfTheMonthOnTheWebsite);
		
	myAssert.assertEquals(UpdatedDateAsInteger, ActualDayOfTheMonth + NumberOfDaysExtra);
		
	}
	
	@Test(description = "Test Number 3" , invocationCount = 3)
	public void RandomCheckTheWebsiteLanguage() {
		String [] myWebSiteURLS = {"https://www.almosafer.com/en" ,"https://www.almosafer.com/ar"};
	int myIndex = Rand.nextInt(0, 2);
	driver.get(myWebSiteURLS[myIndex]);
	
	if (driver.getCurrentUrl().contains("ar")) {
		String Language = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]")).getText();
	myAssert.assertEquals(Language, ExpectedLanguageEnglish);
	}
	else {
		String Language = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[1]")).getText();
		myAssert.assertEquals(Language, ExpectedLanguageArabic);
	}
	}
	
	@Test(description = "Test Number 4", invocationCount = 6)
	public void Test_The_Hotel_Tab() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String [] myWebSiteURLS = {"https://www.almosafer.com/en" ,"https://www.almosafer.com/ar"};
		int myIndex = Rand.nextInt(0, 2);
		driver.get(myWebSiteURLS[myIndex]);

		String [] ArabicCountries = {"دبي", "جدة"};
		int ArabicIndex = Rand.nextInt(0, 2);
		String [] EnglishCountries = {"Dubai", "Jeddah", "Riyadh"};
		int EnglishIndex = Rand.nextInt(0, 3);
		
		if (driver.getCurrentUrl().contains("ar")) {
			WebElement HotelTab =driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]"));
			HotelTab.click();
			Thread.sleep(3);
			WebElement SearchBar = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div[2]/div/div[1]/div/div[1]/div/div/input"));
            WebElement SearchButton = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button"));
			SearchBar.sendKeys(ArabicCountries[ArabicIndex] + Keys.chord(ArabicCountries));
		// SearchBar.sendKeys(Keys.chord(Keys.ARROW_DOWN) + Keys.ENTER);
		SearchButton.click();
		}
		
		else {
			WebElement HotelTab =driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tab-hotels\"]"));
			HotelTab.click();
			Thread.sleep(3);
			WebElement SearchBar = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[1]/div/div/div/div/input"));
            WebElement SearchButton = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button"));
			SearchBar.sendKeys(EnglishCountries[EnglishIndex] + Keys.chord(EnglishCountries)); // Choose First option
		//	SearchBar.sendKeys(Keys.chord(Keys.ARROW_DOWN) + Keys.ENTER); // Choose Second Option from list
			SearchButton.click();
		}
	}

	@Test(description = "Test Number 5")
	public void RandomlySelectNumberOfVisitors() {
		
		WebElement NumberOfVisitors = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div[2]/div/div[3]/div/select"));
	
	Select mySelector = new Select(NumberOfVisitors);
	int RSMyIndex = Rand.nextInt(0, 2);
	mySelector.selectByIndex(RSMyIndex);
	}
	
    @Test(description = "Test Number 6")
    public void SearchHotel() {
    	WebElement SearchButton = driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div[2]/div/div[4]/button"));
    	SearchButton.click();
    }
    
	 @Test (description = "Test Number 7")
	 public void DoneSearching() {
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 WebElement SearchResult = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/span"));
		 String TextOfTheSearchResult = SearchResult.getText();
		 boolean ActualResultInTheWebsite = TextOfTheSearchResult.contains("found") || TextOfTheSearchResult.contains("وجدنا");
		 myAssert.assertEquals(ActualResultInTheWebsite, true);
	 }
	 
	 @Test (description = "Test Number 8")
	 public void Sorting_From_Low_To_High_Price() throws InterruptedException {
		 WebElement LowestPriceButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/section[1]/div/button[2]"));
		 LowestPriceButton.click();
		 Thread.sleep(3000);
		 
		 WebElement RightSide = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/section[2]/div[1]"));
		 List<WebElement> ThePrice = RightSide.findElements(By.className("Price__Value"));
		 
		 for(int i=0; i <ThePrice.size(); i++) {
			// String PriceOnTheWebsite = ThePrice.get(i).getText();
			// int EditedPrice = Integer.parseInt(PriceOnTheWebsite);
			 
			 String PriceOnTheWebsiteForFirstItem = ThePrice.get(0).getText();
			 String PriceOnTheWebsiteForLastItem = ThePrice.get(ThePrice.size()-1).getText();
			 int FirstItemAsInteger = Integer.parseInt(PriceOnTheWebsiteForFirstItem);
			 int LastItemAsInteger = Integer.parseInt(PriceOnTheWebsiteForLastItem);
			 
			 myAssert.assertEquals(FirstItemAsInteger<LastItemAsInteger, true);
		 }
	 }

	
	
	@AfterTest
	public void AfterTest() {}
	}
		
	

