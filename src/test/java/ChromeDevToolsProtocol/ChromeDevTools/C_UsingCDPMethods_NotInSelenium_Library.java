package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;

public class C_UsingCDPMethods_NotInSelenium_Library{
	
	//                 How to write custom CDP methods ?
	/*
	 * Selenium library contains methods equivalent to the methods present on the CDP web page.
	 * However; not all CDP methods are present in Selenium library. 
	 * 
	 * In this case, we've to learn how to write our own customized CDP methods when NEEDED.
	 * 
	 * Hint: A true senior automation QA is capable of writing their own: 
	 *  1- Customized explicit wait methods 2- Customized Chrom DevTools protocol methods.
	 *  .And this is considered the core basic ability to reduce flakiness.
	 *  
	 *  The following architecture is vital to our understanding in this regard:
	 *  

+-----------------+               +-----------------------------------+    +---+   +-------------------------------------+
| WEB Driver code | ⟶ .send() ⟶ |Selenium Server interprets commands| ⟶ |CDP| ⟶|Mimics behaviour of browser under test|    
+-----------------+    command    +-----------------------------------+    +---+   +-------------------------------------+               
       |                    |                                                ^
       |                    |                                                |(==Invoking the CDP method)
       |                    v                                                |
       |                    When using the already developed                 |
       |                    Chrome DevTools methods in Selenium              |
       |                                                                     |
       |                                                                     |       
       |-------------->> .executeCDPCommand()--------------------------------| 
                          [When using custom CDP methods bypassing the server step above]
                          [ and directly invoking the CDP method]
                     
	 * 
	 * 
	 * Now, to write our own customized CDP method, 
	 * let's do that for setDeviceMetricsOverrride() method that we've already used in our previous example
	 * 
	 * Now, I know that this CDP method happens to be an already existing method in Selenium library. So
	 * The question is, why are you doing this ? as you could do this for a CDP method that's
	 * not already developed by Selenium ( Wait and see)
	 *___________________________________________________________________________________________________________________________*/
	
	// This class has the EXACT same 2 methods/ test cases:
	// The only difference is: The first one has no comments. 
    //	                       & The 2nd one is heavily commented (This is for educational purposes/demo only.
	// In real-time utilize Log4J log messages, explicit waits, and assertions !.
	
	@Test(enabled=false)
	public void customCDP_commentedOut() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",setDeviceMetricsOverrideCustomMe(600,1000,50,true)); 
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
	    Thread.sleep(5000L);
	    //Below: This web page should now emulate the specific mobile device represented by the first 3 method arguments above.
	  	driver.get("https://rahulshettyacademy.com/angularAppdemo/");  
	  	//Below: clicks on the 'hamburger menu' only available in mobile view
	     driver.findElement(By.cssSelector(".navbar-toggler")).click(); 
	     Thread.sleep(3000L);                                          // to view slow execution.
	     driver.findElement(By.linkText("Library")).click();           // Clicks on the library tab.
	     // this is MERELY A demo test case. In real time explicit waits and log4j comments plus assertions are utilized.
	}
	
	// the comments of the program above are included below:
	@Test(enabled=false)
	public void customCDP() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();	
/* Now use: 
 * 
 * driver.executeCdpCommand(String commandName, Map<String,Object> parameters: Map<String,Object>- ChromiumDriver)) 
 * 
 * instead of.send()which is ONLY 4 already developed CPD methods present in Selenium Library already
 *
 * Now, you can not continue unless you go on the specific CDP method web page,that
 * we want to create an equivalent selenium method for (A custom CDP method).
 * so go on the CDP web page of "Emulation.setDeviceMetricsOverride"
 * As of now it happens to be at: "https://chromedevtools.github.io/devtools-protocol/tot/Emulation/#method-setDeviceMetricsOverride"
 *  
 *  and this is where you have all the info required to build this custom CDP method.
 *  
 *  Exercise for you !: Try to build this custom method by utilizing the provided CDP web page without ever looking
 *  at the below lines of code !
 *
 */
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",setDeviceMetricsOverrideCustomMe(600,1000,50,true)); 
		// the 2nd argument is for passing an object of a collection class that implements the interface of
		// MAP. There 3 three classes that implement this interface: HashMap, LinkedHashMap, TreeMap.
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
	    Thread.sleep(5000L);
	    //Below: This web page should now emulate the specific mobile device represented by the first 3 method arguments above.
	  	driver.get("https://rahulshettyacademy.com/angularAppdemo/");  
	  	//Below: clicks on the 'hamburger menu' only available in mobile view
	     driver.findElement(By.cssSelector(".navbar-toggler")).click(); 
	     Thread.sleep(3000L);                                          // to view slow execution.
	     driver.findElement(By.linkText("Library")).click();           // Clicks on the library tab.
	     // this is MERELY A demo test case. In real time explicit waits and log4j comments plus assertions are utilized.
	
	}
	// Note: Sometimes some of the created-by-us Selenium CDP methods might not 
	// work as they may require additional tweaks. One could learn about these tweaks by examining
	// the code behind CDP methods already existing/developed in Selenium library (Looking up their jar documentation).
	public static Map<String, Object> setDeviceMetricsOverrideCustomMe(Integer width, Integer height, Integer deviceScaleFactor, boolean mobile)
	{
		Map<String, Object> hashMap= new HashMap<String, Object>();
		hashMap.put("width", width);
		hashMap.put("height", height);
		hashMap.put("deviceScaleFactor", deviceScaleFactor);
		hashMap.put("mobile", mobile);
		return hashMap;
	// In the next branch of this GiutHub repository, I will try to improve on this code. Stay tuned.
		
}
	
	
	
	// Below is a slightly different way of writing: In the 2nd argument directly provide the MAP object that already
	// contains all the parameters.
	@Test
	public void customCDP_DifferentWay() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		
		Map<String, Object> hashMap= new HashMap<String, Object>();
		hashMap.put("width", 600);
		hashMap.put("height", 1000);
		hashMap.put("deviceScaleFactor", 50);
		hashMap.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",hashMap); 
	 	driver.get("https://rahulshettyacademy.com/angularAppdemo/");  
	     driver.findElement(By.cssSelector(".navbar-toggler")).click(); 
	     Thread.sleep(3000L);                                        
	     driver.findElement(By.linkText("Library")).click();          
	}
}	

