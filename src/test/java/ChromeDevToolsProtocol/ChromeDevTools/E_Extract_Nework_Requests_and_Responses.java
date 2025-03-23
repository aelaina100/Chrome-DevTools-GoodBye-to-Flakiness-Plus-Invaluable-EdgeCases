package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.Request;
import org.openqa.selenium.devtools.v133.network.model.Response;
import org.testng.annotations.Test;

                                   // Pre-requisite: Knowledge of API testing (At least the fundamentals).
public class E_Extract_Nework_Requests_and_Responses {
	
	/* Logging all network activities (Capturing requests, responses, & status codes)
	 * on your web application is a GAME CHANGING feature, Selenium community has
	 * been looking for.
	 * 
	 * 			Because this will help you DIRECTLY understand WHY EXACTLY a UI failure occurred
	 * when examining Selenium reports- Because prior to Selenium 4.0 [where the integration of
	 * Chrome DevTools with Selenium wasn't possible] if a UI error, in an automation script is
	 * encountered then one has to manually investigate whether it's a purely UI error or an API
	 * related one by manually debugging the 'Network' tab to detect the failing API(s) 
	 * [The failing API(s) will NOT have an: OK 200 Status OR is going to return a json response different from the expected one
	 * OR the request sent is different from the expected one for whatever reason.].
	 *  
	 *  Nevertheless, all that manual debugging in the 'Network' tab, 
	 *  can now be automated via the integration of Chrome DevTools with Selenium. where one will
	 *  be able to get each and every API request, response, and status code for the response
	 *  [ We call that: Logging network activity].
	 *  
	 *  Thereby from now and on, if test case (TestNG method) fails, then I will be able to know
	 *  whether it's purely a UI failure or an API-related one, just by examining the logs. Where 
	 *  the associated specific API is examined to drill down on what went wrong.
	 *  
	 *  Invaluable note: The mentality of most QA personnel is as follows===>
	 *  Usually when encountering a UI error, our first reaction is to immediately attempt to 
	 *  replicate the error (As the corresponding network(API) activity requires an effort to
	 *  try to manually locate it, amongst the countless other made API requests & responses in the 'Network'
	 *  tab of the Chrome DevTools).
	 *  However; now instead of trying to, immediately as a 1st step, replicate the UI error,
	 *  one could easily & readily pull the Selenium logged API activity of that specific error to know
	 *  what went wrong prior to even trying to first replicate the error ! (Where even if the error is not replicable
	 *  , one can still report the associated already selenium-logged API error that is easily accessible ! where they'd also
	 *  add a note indicating that the issue was not replicable !).
	 *  = never miss on a bug !
	 *  
	 *  So, instead of claiming that a test case was/is flaky (Such a claim is NOT usually followed by any measure or
	 *  practical action other than stating the flakiness), one can go back to the exact moment when the test failed and pull 
	 *  all the associated logged network activities and include them in a bug report emphasizing the flaky behavior.
	 *  Plus including the network data when the test is passing.
	 *          ******************* YOU will appreciate this feature in Selenium *******************************
	 *  
	 *  
	 *  
	 * Note: On CDP web page for the domain of 'Network'
	 * As of now it can be found on: [https://chromedevtools.github.io/devtools-protocol/tot/Network/]
	 * The 'Network' domain consists of: Methods, Events, & Types.
	 * 
	 *  Description is: ""Network domain allows tracking network activities of the page. It exposes
	 *  information about http, file, data and other requests and responses, their headers, bodies, timing, etc.""  
	 *  */

	/*************************************************************************************************
	 *       3 steps to extract all network requests and responses:                    
	 *                                                                                               
	 *               1- Enable the traffic.
	 *               2- Listen to the Event of [Network.requestWillBeSent] until it's fired.
	 *               3- Listen to the Event of [Network.responseReceived] until it's fired.
	 * 
	 * 
	 *************************************************************************************************/
	
	
	// The below 2 methods are exactly the same. One without comments & the other is with.
	
	// STILL BUILDING the test case:
	@Test(enabled= true) // Under construction: Resolving library-related issues
	public void extractNetworkRequestsResponses() throws InterruptedException
	{
		ChromeDriver driver= new ChromeDriver();
		//EdgeDriver driver= new EdgeDriver();
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
		//driver.findElement(By.cssSelector("button[routerlink*= 'library']")).click();
	}

	
	// Below same method but with explanations.
	
	@Test(enabled=false)                   // Under construction: Resolving library-related issues
	public void extractNetworkRequestsResponses_withComments() throws InterruptedException {
		// To start familiarizing yourself with the concept of the'Network' tab in Chrome DevTools, examine the below example:
		
		/* A practical example:
		 * On "
"
		 * Open the chrome DevTools >> 'Network' tab >> Ensure, you're on the sub tab of 'All'.
		 * Now, click on the button of "Virtual Library' in the UI
		 * Notice: One API request is made, where it's Json response body has sections, each corresponding
		 * to a book record displayed in the UI. (On most web sites, you're going to see MANY API requests.)
		 * 
		 * 	
		        So let's say that tomorrow- The UI table supposed to host the book records fail to load 
		        data so that no book record is displayed or that only some records are displayed, etc., then 
		        we can know IMMEDIATELY whether this is a UI or API-related issue. By simply accessing the Chrome DevTools
		        in the browser and examining the related API activity so we can pin down what went wrong.
		        
		        Now, instead of manually performing these steps, one could use automation via the integration of the
		        Chrome DevTools Protocol with Selenium where: We are going to log every single API activity
		        utilizing Selenium.
		        
		        By examining the logged network activity in Selenium, we could pin down the specific API and know
		        exactly when went wrong with it or maybe that, this specific API functions properly so that it is purely a UI issue.
		 
		 * As you can see, One would know right away if this is a UI or API-related issue BEFORE
		 * even trying, as a 2nd step, to replicate the issue to know whether it is reproducible or not.
		 * where one would create a bug report/at least discuss it with developers even if the issue
		 *  is no longer reproducible for one more time !
		 */
		 ChromeDriver driver= new ChromeDriver();
		 DevTools devTools= driver.getDevTools();
		 devTools.createSession();
		 
		  /**********************************HOW TO LOG NETWORK ACTIVITIES********************************** 
		 *
		 * On the page for "Network.enable" [https://chromedevtools.github.io/devtools-protocol/tot/Network/#method-enable]
		 * Its description is: "Enables network tracking, network events will now be delivered to the client."
		 * [Notice that 'client', in our case, is Selenium] 
		 * PLus, study the arguments of this method. */
		 devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); 
		 // the 3 argument are optional as they correspond with the size of the files (if you want to specify).
		 // If you keep them optional this means that it will store the files with their sizes as they are.
		 // Network traffic is now flowing from the browser's Chrome DevTools and into our client (Selenium in this case).
		 // This network traffic consists of events. Events are nothing but activities that are triggered.
		
		 // Now, I need to capture the event of the browser/UI receiving the Json response that's relayed by that specific API.
		 // this event can be captured when triggered via utilizing the  "Network.responseReceived" event (as opposed to method) 
		 // that's provided under the 'Events' section (As opposed to 'Methods') under the 'Network' domain tab.
		 // Description: "Fired when HTTP response is available"
		 
		 //To capture it use: 
		// devTools.addListener(event, consumer)  // 'event', 'consumer' arguments.
		           // 1st argument 'event': "Network.responseReceived" [keep on listening(waiting) until
		          //  this event is triggered ]
		         //   and when it's triggered, the content of this event [Network.responseReceived]is saved in the consumer object (2nd argument).
		        //    meaning: ALL data in the response is saved.
		       //     and the lambda -> denotes that we're acting on this object we named "response"
		      //      whose scope is inside the provided block.
		 
		        // where you include what parts of the response you'd like to log: Status code, response json body, etc.
		
		 //There is also: The event of "Network.requestWillBeSent".
		 // One CDP web page, the description is as follows:  "Fired when page is about to send HTTP request."
		 // Questions: can you tell me, on that web page, what the listed parameters are ?
		 	//Answer: These are the details that I can log for the request just before sending them to the API ==
		   // == Just before clicking on the 'Send' button in POSTMAN (Equivalent to that).
		 
		 
	//Below: This REQUEST EVENT will be fired JUST BEFORE a request is sent:
		 devTools.addListener(Network.requestWillBeSent(), request ->
		 {
			Request req= request.getRequest();
			System.out.println(req.getUrl());
			System.out.println(req.getHeaders());
		 });
		 
		 
     //Below: This RESPONSE EVENT will be fired AFTER the response is received.
	 devTools.addListener(Network.responseReceived(), response ->
	 {
		 //response.getResponse().getStatus();      // so now EVERY response status will be LOGGED.
		Response resp= response.getResponse();
		resp.getUrl(); // the request url
		resp.getStatus(); //status code
		System.out.println(resp.getUrl());
		System.out.println(resp.getStatus());
		 // 'response' is the object + dot + [get the class of this object:  .getResponse()] + dot + methods inside the class of .getResponse()
		 // that are used to log: Request's header, body + Response's status code, time, header, body and more. (request stuff, how ???)
		 // response time, status code, response header, request header,etc.
		 
		 // CNTRL + Left-click on getResponse(): Notice, it is returning the 'response' object back.
		 // and this 'response' object, emits "Network.resposnse" as you can see "Network.resposne" on the CDP webpage
		 // when you type in, response.getResponse() where "Network.resposnse" will have an access to all the methods listed
		 // on that cdp webpage for "Network.resposnse"   12:50
	 });
	
		driver.get("http://www.rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(2000L); // replace with explicit wait.
		driver.findElement(By.cssSelector("button[routerlink*= 'library']")).click();
		
	}
	
	
	/*
	 * re-hash: .addListener(event, consumer) 
	 *          is for an event to be waitede for. Once the event is triggered, then it
	 *          is saved as an object in the 2nd argument. Then,
	 *          a lampda operation is applied on the object in order to only log the info
	 *          wanted from a 'sea' of info such as request URL, headers, pay loads, json response body,status code,
	 *          response time, request headers, and so much more. 
	 * 
	 */
	
	/*
	 Let's clarify the differences between .getResponseBody() and .getPostData():

	 1. getPostData() (Request Body)
	 Purpose: getPostData() is used to retrieve the body of a request that is about to be sent. This is typically used for HTTP methods that send data in the request body, such as POST, PUT, or PATCH.
	 When it's used: It's accessed during the request phase (before the request is sent).
	 What it returns: It returns the payload (e.g., JSON, form data) that is being sent with the request.
	 Common use case: Logging or inspecting the data that will be sent in a POST or PUT request.
	 
	 2. getResponseBody() (Response Body)
	 Purpose: getResponseBody() is used to retrieve the body of the response after a request has been processed. This is typically used to inspect the data received from the server after the request is made.
	 When it's used: It's accessed during the response phase, after the server has responded to the request.
	 What it returns: It returns the body of the response, which could be HTML, JSON, XML, or any other type of content returned by the server.
	 Common use case: Logging or inspecting the data returned from the server, especially when it's in a structured format like JSON.
	
	*/
	
	
	
	
/* Pre-requisite:               <====: UNDERSTAND THE TERMINOLOGY:====>
 * An event in triggered (What does this mean ?)
 * 
 *  In Chrome DevTools, events refer to specific actions or interactions that occur in the browser, such as user actions, 
 *  network requests, DOM changes, and script executions. These events can be observed across multiple DevTools tabs,
 *  but they are not just any type of data.

      Types of Events in Chrome DevTools
      
      Events in DevTools generally fall into different categories, which can be monitored across various panels:

1- DOM Events (Elements Tab)
  - Events like click, mouseover, keydown, etc.
  -Tracked under the Event Listeners section in the Elements panel.
  
2- Network Events (Network Tab)
  - API calls (XHR, Fetch), WebSocket messages, and resource loading.
  -Captured in the Network panel.
  
  3-JavaScript Events (Sources Tab)
    -JavaScript execution events, such as breakpoints on event listeners.
    -Managed under Event Listener Breakpoints.
   
4-  Console Events (Console Tab)
   - Logs from console.log(), errors, warnings, and debug messages.
   
5- Performance Events (Performance Tab)
   - Event timing, rendering, and page load performance.

6- Security Events (Security Tab)
   - Issues like mixed content warnings, certificate problems, and unsafe scripts.
 * 
 * 
 * 

 * Now, for Network Events (various data found under the Network Tab), Chrome DevTools protocol developers 
 * have written methods primarily used for capturing data related to network activities. So for example-
 * the method of (List it here) is used to capture the status code and the Json response body of the relayed
 * json reponse, by the API, sent to UI/browser.
 * 
 */
	// Study this link !: https://chatgpt.com/share/67d47b58-8284-8000-9caa-03b460e5b0f5


}
