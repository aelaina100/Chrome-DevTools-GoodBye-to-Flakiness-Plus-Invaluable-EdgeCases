package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.testng.Assert;
import org.testng.annotations.Test;

public class H_Blocking_Network_Requests {
	/*
	 * In the last example, I've shown how to fail a network request(s), where you DO get a response back that
	 * renders a certain UI behavior that you want to validate.
	 * 
	 * .However; in this example- I will explicitly BLOCK network (API) request(s) from the browser. Meaning that,
	 *  these request(s) will not be made in the first place from the browser = won't appear in the Network tab.
	 * 
	 * So, when do you need to BLOCK network request(s) ?
	 * 		You need that, to prevent CSS & images from loading in the browser. For example: There is a big banner
	 *      image that takes a few seconds to load on the page: When the line of driver.get() is executed [By the way,
	 *      its code implementation already includes synchronization/wait time], the controller will not move to the 
	 *      next line of code UNLESS this web page has entirely finished loading.
	 *      
	 *      Also/or there are also lots of images on the page, it may take at least 4-5 seconds for the page to finish
	 *      loading. Thereby and also if you happen to have multiple pages in the application, you can multiply these
	 *      4-5 seconds by factor. In all cases, this testNg method/test case is going to take a relatively longer time
	 *      to finish executing.
	 *      
	 *      And to make things worse, as if things aren't already, all of this is with the assumption of having a good
	 *      network speed (On a side note: A good QA not only assumes that this alleged fast/good network speed
	 *      may get plummeted during execution, but they also take measures by writing lines of code to counter act this 
	 *      potential issue), 
	 *      where if you have slow/bad network speeds, then loading these images could take longer as in 5-10 seconds.
	 *      
	 *      Nevertheless ! our automation does NOT require images loading at all, as I'm performing functional
	 *      validations with Selenium. Images render a slower execution of our automation scripts. This is especially
	 *      true/ more applicable for e-commerce websites such as Amazon which contains tons of images. Therefore,
	 *      go ahead and block all of these images (Blocking = no request to API is made in the first place = No request
	 *      is shown in the Network tab becasue it has not been made in the 1st place !).
	 *      
	 *      On: http://www.rahulshettyacademy.com/angularAppdemo/
	 *      Under, the 'Network' tab- As the page is loading or finished loading, you'll notice lots of .js files
	 *      (clicking on each, will show you its associated request). Do not block these as they may constitute functional
	 *      displays of your app. 
	 *      However; when you click on 'Browse products' many .JPG files/.css will be generated.
	 *      we can block their associated requests, so that their respective UI images are not loaded. And
	 *      that will make the app a little faster.
	 *      
	 *      Note: Blocking .css files [= all associated styling you see on the webpage will be gone. Only raw HTML
	 *            without css loading is all what's left.]
	 *      
	 *      Scenario Z to automate:
	 *      Navigate to: http://www.rahulshettyacademy.com/angularAppdemo/ >
	 *      Click on "Browse Products" > "Selenium" > "Add to Cart" > "Cart" (button on top to go inside the cart).
	 *      
	 *      To make the automation script run faster, by not loading the images present in each step, then block
	 *      the requests associated with loading the images in the UI. SImple ! continue 3:15
	 *      
	 * 
	 */
	@Test
	public void blockingApiNetwork() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		//Below- refine the mo3alem notes:
		/* Now enable your network before you perform any operation (mandatory)==>
		 * 	  head to the Chrome Dev Tools Protocol webpage, under the 'Network' domain there is method available
		 *  to block calls (requests):  [Network.setBlockedUrls].
		 *  So, it's the 'Network' domain and not the 'Fetch' domain. Earlier, I utilized the 'Fetch' domain
		 *  to stop my response or fail my response.
		 *  But, not we're not intercepting or stopping requests. In here, we just want to block them !
		 * 
		 * 
		 * 
		 * [Network.setBlockedURLs]
              
            Description: Blocks URLs from loading.
              
            Paremeters:       urls     array[ string ]
                                       URL patterns to block. Wildcards ('*') are allowed.

            My Note: The above wildcards ('*')  = regular experession 
            
            sperate note: remember that an array or any other container could have only one element sometimes.
            Important: without looking at the notes below whatsoever, come up with the FINAL syntax of this parameter.
                              
		 *  My Note: According to the CDP webpage: The parameter array you've to pass is an array of Strings. However;
		 *  when looking at its Selenium library implementation, Selenium developers made it different and a bit more 
		 *  complicated as one has to pass a parameter that is of a 'List' String data type.
		 *  
		 *  So, the syntax is of the parameter is": List.of( one string or more string(s))
		 *  a redundant way of writing the syntax of the parameter: is to create an array of these string(s) and then 
		 *  convert this array into an arrayList.
		 *  
		 *  so, the redundant syntax of the parameter is: Arrays.asList(one string or more string(s))
		 *  
		 *  
		 * 
		 */
		//Below: one HAS to enable the network tracking b4 working on any method under the 'Network' domain (rule).
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//Below: Now, we can fire the command of 'Network.setBlockedURLs'
		 devTools.send(Network.setBlockedURLs(List.of("*.css", "*.jpg"))); 
		//Below line is redundant: 
		// devTools.send(Network.setBlockedURLs(Arrays.asList("*.css", "*.jpg"))); 
		 
		// Crucial: In earlier version, the code implementation behind  '.setBloackedURLs had only 1 thing
		// that was different: 
		// Instead of the current " LinkedHashMap<String, Object> params = new LinkedHashMap<>();"
		// we had:                " ImmutableMap.Builder <String,Object> params= ImmutableMap.builder();
		// In Java, immutable refers to an object whose state cannot be changed after it has been created. This means 
		// that once an immutable object is initialized, its fields cannot be modified.
		// Hence, the instructor line of code was: (Still works !)
		//devTools.send(Network.setBlockedURLs(ImmutableList.of("*.css", "*.jpg"))); // blocks all images and stylings.
		
		 //Now, let's access the target url to start implementing the testcase scenario given above.
		Long startTime= System.currentTimeMillis(); 
		
		driver.get("http://www.rahulshettyacademy.com/angularAppdemo/");
		//Click on 'Browser products':
		//Thread.sleep(10000L);   // to view action slowly (I know that it defeats the purpose of this testNG method/testcase0
		driver.findElement(By.linkText("Browse Products")).click();
		// Click on 'Selenium'
		//Thread.sleep(10000L);
		driver.findElement(By.linkText("Selenium")).click();
		//clicking add to cart:
		//Thread.sleep(10000L);
		driver.findElement(By.cssSelector("button.add-to-cart")).click();
		//Now, on this page:asserting that a certain text message appear in the UI
		//Thread.sleep(10000L);
		String expectedMsg= "This Product is already added to Cart".toLowerCase();
		String actualMsg= driver.findElement(By.cssSelector("p[style='text-align: center;']")).getText().toLowerCase();
		
		Long endTime= System.currentTimeMillis(); 
		
		Assert.assertEquals(actualMsg, expectedMsg, "UI displaying the wrong message");
		//See that the execution is VERY fast with NO images and css stylings on the web page (Only made possible
		// with the integration of Chrome Dev Tools with Selenium as of 4.0 and up)
		// RELATIVE to when images/css stylings are not blocked 
		
		// Now to prove that blocking these images/css makes the execution much faster, I have utilized:
		/*
		System.currentTimeMillis(); //before driver.get()
		System.currentTimeMillis(); // after the last UI interaction was completed.
		*/
		System.out.println("Execution time is: " + (endTime - startTime)); // see the difference when commenting out the 
		//blocking line above.
	}

}
