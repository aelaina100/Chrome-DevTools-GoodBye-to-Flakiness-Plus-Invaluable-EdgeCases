package ChromeDevToolsProtocol.ChromeDevTools;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.emulation.Emulation;
import org.testng.annotations.Test;

// This class has 2 methods. Each has the EXACT SAME code.
// The difference is: The first one is with no/minimal comments while the 2nd one is very heavily commented.
// Keep in mind that these comments are simply intended to make the reader understand as much as possible. In real-time, the comments are substituted
// with Log4J logs containing concise straight to the point messages.
public class B_EmulateMobileOnWebpage {                      // UNDER CONSTRUCTION
	
	@Test
	public void emulateMobileOnWeb()                      // UNDER CONSTRUCTION
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
		 
		//2nd step: Create the object for Chrome DevTools:
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
		   * This command/event has the EXACT SAME name of some method that CDP will programmatically invoke in order to
		   *  access the Chrome Dev Tools and perform the wanted action of emulating a specific device browser onto the web browser.
		   * 
		   */ 
		  //Keep in mind that the package to be imported for "Emulation" has to be the correct one:
		    // As of now, 4 different imports for packages are listed: v130, v131, v132, v85 
		      // (example: Import 'Emulation' (import org.openqa.selenium.devtools.v130.emulation.Emulation)
		       // where the correct one to import is based on the installed version of browser in your system (each browser version
		        // has a unique backend code)
		  
		  
		  
		  /* Final Tip: Have a readily access to this Chrome DevTools protocol webpage which lists all the methods CDP uses to
		    programmatically access and interact with Chrome DevTools. */
		
		  // UNDER CONSTRUCTION TO BE CONTUNUED
	}

}
