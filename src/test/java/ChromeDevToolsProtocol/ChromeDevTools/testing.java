package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.Optional;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.Request;
import org.openqa.selenium.devtools.v133.network.model.Response;
import org.testng.annotations.Test;

public class testing {
	
	@Test
	public void chromeDevTools() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		
devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); 
		
		/* Below: The event of "Network.requestWillBeSent" (According to web page description):
		 * is "Fired when page is about to send HTTP request"   == Fired when the request is ready to be sent
		 * (As data to make up the request is being gathered prior to that)
		 */
		 devTools.addListener(Network.requestWillBeSent(), request ->
		 {  // The event of "Network.requestWillBeSent" emits 'request' object.
			System.out.println("************Fetching all the requests************");
			Request req= request.getRequest();
			System.out.println(req.getUrl());
			System.out.println(req.getHeaders());
			System.out.println("************All requests fetched*****************");
			 
		 });	 
		 /* Below: The event of "Network.responseReceived" (According to web page description):
		  * is "Fired when HTTP response is available." == Fired after the response has been received.
		  */
  
	 devTools.addListener(Network.responseReceived(), response ->
	 {
		 //response.getResponse().getStatus();      // so now EVERY response status will be LOGGED.
		 System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
	    System.out.println("************Fetching all the responses************");
		Response resp= response.getResponse();
		resp.getUrl(); // the request url   (to prove that Selenium could get it from the response as well ? counter intuitive !)
		resp.getStatus(); //status code     (same for below !).
		System.out.println(resp.getUrl());
		System.out.println(resp.getStatus());
		System.out.println("************ALl resposnes fetched************");
	 });
	 
		driver.get("http://www.rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(2000L); // replace with explicit wait.
		//driver.findElement(By.cssSelector("button[routerlink*= 'library']")).click()
		
		
		
	}

}
