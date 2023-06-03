import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;
import java.util.Random;

public class PrimryClass {
	
	static WebDriver driver = new ChromeDriver();
	
	Assertion myAssert = new Assertion();
	
	Random Rand = new Random();
	
	String WebSiteLink = "https://www.almosafer.com/en";
	
	String ExpectedLanguageArabic = "العربية";
	String ExpectedLanguageEnglish = "English";
	
	String ExpectedCurrency =  "SAR";
	
	String ExpectedContactNumber = "+966554400000";
	
	Boolean ActualValueOfQitafIfIsThere;
   

}
