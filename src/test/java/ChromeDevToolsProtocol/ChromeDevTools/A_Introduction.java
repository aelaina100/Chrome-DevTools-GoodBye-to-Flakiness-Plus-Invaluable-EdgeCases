package ChromeDevToolsProtocol.ChromeDevTools;

public class A_Introduction {
	
	/* Start by reading the [ReadMe-ChromeDevTools] file situated on the project level.
	 * Then, read the below notes: 
	 * 
	 *  * As of Selenium 4.0, Chromium Driver class has been devised.
	 * 		It has pre-defined methods to access the Chrome DevTools (As opposed to accessing it manually by right-clicking > Inspect > Chrome DevTools 
	 *      UI interface appears with the tabs of "Elements", "Network", "Console", "Memory", "Toggle Device Emulation", and many many more tabs.)
	 *      So instead of manually performing actions from the Chrome DevTools UI interface in browser, one can do them programmatically. 
	 *      
	 *      This chrome DevTools feature is only available in Chromium-based browsers such as Google Chrome & Edge browsers.
________________________________________________________________________________________________________________________________________________________________________________________________________
              ********************************  Class Hierarchy in Selenium 4.0 and onwards:     **************************************** 

 A- For Chrome Driver / Edge Driver  (In addition to Brave,Opera, & other chromium-based browsers):=========>
  
  
   Object
       └─ WebDriver 
                └─ Remote WebDriver 
                               └─ Chromium Driver**
                                             └─ Chrome Driver / Edge Driver  (In addition to Brave,Opera, & other chromium-based browsers).
   
  ChromeDriver driver= new ChromeDriver();
  DevTools devTools= driver.getDevTools();
  DevTools.createSession(); 
  
  Now
                                        
                   [Remember: In the above structure (and always), "WebDriver" is the ONLY interface & "Remote WebDriver" is the class that implements this interface.]
                                        
**Chromium Driver has been introduced since Selenium 4.0: It contains all the automation methods of Chrome DevTools. Where  Chrome Driver / Edge Driver inherits directly from "Chromium Driver".

   		
 ______________________________________________________________________________________________________________________________________________________________
 B- For FireFox driver ( & any other non chromium-based browsers):========================>   
                               
   Object
       └─ WebDriver
                └─ Remote WebDriver  
                                └─ FireFox Driver
                                
           
FireFox Driver inherits directly from "Remote Web Driver".
__________________________________________________________________________________________________________________________________________________________________________________________________________        
                 **********************************************************    Class Hierarchy PRIOR to Selenium 4.0:    ********************************************************** 
                               
   Prior to Selenium 4.0, the above hierarchy is true MINUS "Chromium Driver":
   
     Object
       └─ WebDriver
                └─ Remote WebDriver  
                                └─ ChromeDriver
                                └─ InternetExplorerDriver
                                └─ SafariDriver
                                └─ EdgeDriver
                                └─ OperaDriver
                                           
    Where all drivers inherit directly from "Remote Web Driver".
_________________________________________________________________________________________________________________________________________________________________________________________________

	 *      This class, ChromiumDriver, has never existed prior to Selenium 4.0 amongst the other still existing familiar classes of:
	 *      ChromeDriver, EdgeDriver, FireFixDriver, etc.
	 *      
	 *      On the Chromium engine, runs the chromium-based browsers that are: Google Chrome & Microsoft Edge browsers (Plus: Brave, Opera, Vivaldi, & many more).
	 *      Meaning, as of Selenium 4.0:
	 *      the ChromeDriver & EdgeDriver/ Chromium-based classes extend/inherit from ChromiumDriver. While other classes such as FireFoxDriver still inherit 
	 *      from the RemoteWebDriver parent class.
	 *      
	 *      
	 *      
	 *
	 *
	 */

}
