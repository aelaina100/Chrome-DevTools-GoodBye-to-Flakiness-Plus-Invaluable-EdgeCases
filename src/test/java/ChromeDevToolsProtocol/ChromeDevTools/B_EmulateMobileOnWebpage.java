package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.emulation.Emulation;
import org.openqa.selenium.devtools.v132.network.Network;
import org.testng.annotations.Test;

// This class has 2 methods. Each has the EXACT SAME code.
// The difference is: The first one is with no/minimal comments while the 2nd one is very heavily commented.
// Keep in mind that these comments are simply intended to make the reader understand as much as possible. In real-time,
// the comments are substituted
// with Log4J logs containing concise & straight-to-the point messages.
public class B_EmulateMobileOnWebpage {                      // UNDER CONSTRUCTION
	
	@Test
	public void B_EmulateMobileOnWebPage() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver(); 
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true, Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), 
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), 
				java.util.Optional.empty(), java.util.Optional.empty()));
		
		//Below: This web page should now emulate the specific mobile device represented by the first 3 method arguments above.
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");  
		//Below: clicks on the 'hamburger menu' only available in mobile view
        driver.findElement(By.cssSelector(".navbar-toggler")).click(); 
        Thread.sleep(3000L);                                          // to view slow execution.
        driver.findElement(By.linkText("Library")).click();           // Clicks on the library tab.
        // this is MERELY A demo test case. In real time explicit waits and log4j comments plus assertions are utilized.

	}
	// This second test case is EXACTLY the same as the above one BUT with very detailed comments.
	@Test(enabled=false)
	public void emulateMobileOnWeb_withComments() throws InterruptedException                      // UNDER CONSTRUCTION
	{
		//Utilizing Selenium Manager (A feature of Selenium 4.0 where System.setProperty() line is no longer needed):
		ChromeDriver driver= new ChromeDriver(); 
		
		/* Instead of:  WebDriver driver =new ChromeDriver()   because the 'WebDriver' interface does NOT have the methods of Chrome DevTools.
		 * Hence, the 'ChromeDriver' is used instead as it extends/inherits from the ChromiumDriver which contains all the Chrome DevTools methods as shown below:
		 * _________________________________________________________________________________________________________________________________________________
		    Object
                 └─ WebDriver 
                          └─ Remote WebDriver 
                                         └─ Chromium Driver
                                                       └─ Chrome Driver / Edge Driver  (In addition to Brave,Opera, & other chromium-based browsers).
		 __________________________________________________________________________________________________________________________________________________ 
		 * 
		 * However; if one wants to launch the Fire Fox browser then they could ONLY use:  WebDriver driver = new FireFoxDriver(); */
		 
		//2nd step: initiate an object for Chrome DevTools:
		DevTools devTools= driver.getDevTools(); //This object allows you to send the built-in Selenium commands to CDP (Chrome DevTools protocol).
		
		//3rd step: Instantiate a session that's the link between your Selenium code and the browser.
		devTools.createSession(); // of void type.
	
		//4th step: Now, one is able to send a command (event) as an argument in : devTools.send(null)
		// this command/event will invoke its respective CDP method that will access the Chrome DevTools and perform the wanted action.
			
		/* Where can I find these commands ?
		     *Answer: Go to the official website of "Chrome DevTools Protocol" as of now it is: https://chromedevtools.github.io/devtools-protocol/
		     * 	On the left-hand side, there exists items under 'Domain'- These correspond to the different tabs in Chrome DevTools UI in browser.
		     *  Now, click on the item of 'Emulation' which corresponds to the tab of "Toggle Device Emulation" [As I want to emulate a specific
		     *  device (Iphone 12, Ipad, Samsung Gallaxy,etc.] web page on my web browser]
		     *  
		     *  Once you click on 'Emulation', you'll notice a big list of different methods that CDP uses for accessing the 
		     *  Chrome DevTools in browser and performing the specific action wanted.
		     *  	So if I click on "Emulation.SetDeviceMetricsOverride", a web page with an explanation of what this method does
		     *      along with all of its argument/parameter(s) is provided. In this specific case it mentions:
		     *      
		     *      "Emulation.SetDeviceMetricsOverride": "Overrides the values of device screen dimension".
		     *      As, by default, your browser will be on full screen. So based on the parameters such as "Width", "Height", "Device scale factor" etc.
		     *      my browser's default screen dimensions will be changed to exactly emulate the specific device of choice:
		     *      so if it is iphone, then give the CORRECT iphone's width, height, Device scale factor, etc.
		     *      
		     *      In conclusion:                                                                      */
		  //devTools.send(Emulation.setDeviceMetricsOverride(" tons of arguments here");
		  /* The argument above "Emulation.clearDeviceMetricsOverride()" is called a command/event.
		   * Selenium developers have given this command/event, the EXACT SAME name of the method that CDP will programmatically
		   *  invoke in order to
		   *  access the Chrome Dev Tools and perform the wanted action of emulating a specific device browser onto the web browser.
		   * 
		   */ 
		  //Keep in mind that the package to be imported for "Emulation" has to be the correct one:
		    // As of now, 4 different imports for packages are listed: v130, v131, v132, v85 
		      // (example: Import 'Emulation' (import org.openqa.selenium.devtools.v130.emulation.Emulation)
		       // where the correct one to import is based on the installed version of browser in your system (each browser version
		        // has a unique backend code)
		  
		// Below line: The initial/automatically generated arguments are: 'null', 'null', 'null', etc.
		 // Hence- to know what they are, double click on this method to land on the .java class file of 'Emulation' 
		  // (Thereby- you're going inside the jars reading the documentation)
			// where one is going to view the implementation of this method PLUS know the required arguments.
				// do this concurrently with: On the CDP official page- examine this CDP method (Has the EXACT same name)
		devTools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true, Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
		/* To understand how these arguments were provided, read the notes below:
		 * 
		 * What these arguments are:
		 * Having studied the jar documentation concurrently with the CDP method page=====>
		 * 1- width:
		 * 2- height:
		 * 3- deviceScalefactor:       How much zoom in/out a web page fits in the specific device of choice.
		 * 4- mobile:                  True or False ? (Want in on mobile or not ?)    
		 * 5- The rest is optional:    Optional.empty() is the required parameter. 'Null' is NOT. 
		 *                             [Optional.empty() is illustrated in the Jar documentation & also on the CDP specific we page
		 *                              so always study both]		 
		 *                              
		 * Additional note: The values of these arguments were made clear by the CDP specific method info web page. 
		 *                  & not really from the Jar documentation code in Emulation.java class file in Selenium]
		 *                  .Nevertheless, you still have to examine them both.
		 *                  
		 * 
		 * Now, these specific method arguments were readily provided. You should research each a specific device's 3 first arguments
		 * for example, what are they for: iPhone 13 Pro,  iPhone SE (2022), Samsung Galaxy S21 Ultra, etc.etc.
		 * 
		 * But to manually simulate this on a webpage:
		 * 	1- navigate to "https://rahulshettyacademy.com/angularAppdemo/"
		 *  2- Simply, examine its, by-default, page view (Notice the 3 tabs situated in the top left corner)
		 *  3- Right click > Inspect > Chrome DevTools UI opens up > Click on "Toggle Device Emulation":
		 *    
		 *     Note that !: On top you will have the following available"
		 *                  A- Dimensions Responsive (A list of tons of devices to emulate !)
		 *                  B- Width X Height fields (Adjusted according to the device selected or can be custom adjusted)
		 *                  C- Percentage of: zoom in/out (= deviceScaleFactor).
		 *                    	 meaning, on the already selected device view- by how many pixels you'd like to shrink/expand.
		 *                  
		 *  4- From "Dimensions Responsive", select iphoneSE (or any other device)
		 *  5- Notice that: The web view shrinks to that of a mobile & the 3 tabs are now accessible from a drop down !
		 *  6- So now, you can verify that you're getting a hamburger menu where when clicking on it, all options should be listed.
		 *     				
		 *      So people are relying on Appium/Other mobile automation tools to automate such features. However;
		 *      with the advent of ChromeDevTools integration with Selenium this is no longer needed !.
		 *      And this is the FUTURE for web based mobile application (Why have an additional automation framework
		 *      for mobile when your already existing UI automation framework can do the job ! & this is the future !)
		 * 
		 */
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");  // now this web page should emulate the specific mobile device represented by the values of the
		                                          // arguments provided above. Now, we are ready to start automating !
		  
		/*  Now, I'm going to validate the "hamburger menu" (That only appears when you have a mobile view !)
		 *	using Selenium (Possible only with the ChromeDev Tools Integration made possible as of Selenim 4.0 !)
		 *  
		 *  By:          */
		 
		 //1- identify it and click on it:
		driver.findElement(By.cssSelector(".navbar-toggler")).click();
		Thread.sleep(3000L);    // To view everything slowly. Note: this code is only demonstrational 
		                         // where in real-time explicit waits are utilized, assertions, along with more meaningful scenarios/styles.
		
		driver.findElement(By.linkText("Library")).click();
		
		
		
		
		  /* Final Tip: Have a readily access to this Chrome DevTools protocol webpage which lists all the methods CDP uses to
		    programmatically access and interact with Chrome DevTools. 
		    Decide which methods are helpful for you automation !.
		    */
		
		  // For example, navigate to the CDP web page: https://chromedevtools.github.io/devtools-protocol/
		  // and randomly pick any CDP method such as:  Network.getRequestPostData
		 //  Now, check its equivalent Selenium custom command: Write 'Network' and select the method.
		Network.getRequestPostData(null); // this is the equivalent Selenium custom command.
			
			//To understand what arguments it takes and the code behind it:
		     // Click on it + Concurrently examine the CDP method web page (https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-getRequestPostData)
			
		
		// Now, how to invoke a CDP method that does NOT have an equivalent Selenium custom command available ?
		     // We will see that in the next .java class file !
		     // where we are going to directly invoke the CDP method from our Selenium code by writing our own Selenium custom command.
	}

}
