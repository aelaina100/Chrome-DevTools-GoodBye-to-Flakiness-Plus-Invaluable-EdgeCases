package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.fetch.Fetch;
import org.testng.annotations.Test;
   
                               // STILL UNDER CONSTRUCTION lecture # 247
public class F_Mocking_Network_Requests_Responses {
	
	/* In Selenium, mocking network requests/responses is only possible with the integration 
	 * of the Chrome DevTools (As of Selenium 4.0 and up). You do it by intercepting the call & then doing the mocking.
	 * 
	 * Let's demonstrate a scenario:
	 * On "https://rahulshettyacademy.com/angularAppdemo/", click on "Virtual Library":
	 * Many records will be displayed. Notice that each record corresponds to a section 
	 * in the Json response of the GET request of the associated API. [Happens to be a GET API].
	 * 
	 * Now, I want to verify a warning message that only appears when only 1 record is displayed.
	 * 
	 * The traditional approach (Before handling APIs was a possible thing in Selenium) is to obtain 
	 * a profile that only displays one record. However; There exists 2 major issues in such an approach:-
	 * 
	 * 	1- Data acquisition, in and by itself, is one of the common challenges in QA: 
	 *     (Who to ask, will you get it on time/ will you have access to database, will it change in future etc.).
	 *  
	 *  2- Even if the data is finally acquired: What if one day, you login back to that profile to 
	 *     only discover that this one record [associated with the specific message that I need to validate]
	 *     is accompanied by other record(s) so that the warning message is no longer displayed and hence
	 *     can not be verified ? (or any other scenario, one could think of, that prevents us from this).
	 *     
	 * The solution is quite simple: Mock the request or the response of the associated API, so that
	 * only one record appears in the UI.
	 * 
	 * Now, the message associated with only 1 record existing in the UI should appear, where we're going to 
	 * verify:  1- That the message appears 2- That its content is the expected one.
	 * 
	 *  Start here:  
	 *  In very general terms for mocking requests & responses:
	 *  For mocking the Request:=======>
	 *  								In the request, is there data that could be changed
	 *                                  so that, as a result, the inevitable changes in the Json reponse
	 *                                  renders the desired outcome in the UI ?
	 *                                  
	 *  For mocking the Response:======>
	 *                                 In the Json body reponse, is there data that could be changed
	 *                                 so that, as a result, the desired outcome is rendered in the UI ?
	 *                                 
	 *   Notice: 'Data' here could really mean anything. it could be a value(s) or section(s). As simple as that !.
	 *                                 
	 *  So In Postman: Upon examining the Associated API, one concludes the following (Do it in postman b4 proceeding in reading the notes):
	 * 
	 *              The GET Request of: https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty
	 *              will only return one record in the UI if you change the author name in url, to something else.
	 *              so one could mock the request this way. ***************** (One can only know that using Postman)
	 *              
	 *              and is this possible ? {do it at the very end once you're done this program}===> (Experiment in POSTMAN).
	 *              Also, modifying the Json response body so that it ONLY has one section, will make the 
	 *              UI display only one record. (Is this possible ?).
	 *              So one could mock the response this way.  *****************
	 *              
	 * So the ability to mock the request/ response in this way, ALSO opens the door to incorporating many many
	 * edge cases/ negative scenarios test cases. 
	 * 
	 *		for example: you should experiment to find out/ or simply ask ? about the max numb of records allowed in the UI
	 * 		& will that render a warning message to be displayed ?
	 *	    and once you find out about this number, then you can validate this message 
	 *      (1- is triggered 2- the content is the expected one).
	 * 
	 *      and this will be simulated by mocking the request or the response (Whatever possible, works and makes sense !), 
	 *      instead of acquiring a profile that automatically displays a maximum # of records in the UI, as such 
	 *      acquisition of data is difficult to start with. And even if accomplished, then data associated with this profile
	 *      could change since, usually, one does NOT have a control on the source of data being displayed, etc.
	 *
	 * Now, for mocking, we have to:  1- Intercept the call. 2- DO the mocking. For this there exists:
	 * 
	 * "Fetch Domain"
	 *  Description: "A domain for letting clients substitute browser's network layer with client code."
	 *  
	 *  It simply means: Intercepting the desired API(s) and substituting (=replacing =mocking) data 
	 *  in the request or the response.
	 *  
	 *  Note: 'clients' here are us Selenium users (Or any other automation tool that's compatible with these protocol methods).
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	// Below: are 2 methods/ test cases. The only difference is that the first one in minimally commented on. 
	// The second one is heavily commented.
	
	@Test
	public void mockingApi()
	{
		ChromeDriver driver = new ChromeDriver();
		// EdgeDriver driver= new EdgeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		devTools.send(Fetch.enable(Optional.empty(), Optional.empty())); // values for arguments are absent but not
																			// null.

		devTools.addListener(Fetch.requestPaused(), request -> // Additional: Check the code implementation of
																// .addListener().
		{ // this Event emits 'request' object.
			if (request.getRequest().getUrl().contains("shetty")) {
				String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(mockedUrl);
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));
				// Optional.empty() since it's a GET request (Does not have a pay load = doesn't
				// have 'Post Data'). Hence.
				// no headers exist either.

				// argument(s) used to mock the data.
				// Network.getRequestPostData(null)

			} else { // if the request with the URL containing "shetty" not found, then send the
						// requests as they are WITHOUT mocking anything

				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(),
						Optional.empty()));
			}

		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*= 'library']")).click();

		/*
		 * Now verify that: 1- the warning exists (Associated with 1 record only
		 * existing) 2- The actual message is the expected one.
		 * 
		 */
		 // continue building the code here.
		  	
	}
	@Test(enabled= false)
	public void mockingApiWithComments()
	{
		 ChromeDriver driver = new ChromeDriver();
	 	//EdgeDriver driver= new EdgeDriver(); 
		 DevTools devTools= driver.getDevTools();
		 devTools.createSession();
		 
		 // First I have to enable:
		   //Just as I've done earlier with the 'Networking Domain', I have used 'enable', so that:
		 // Network traffic is now flowing from the browser's Chrome DevTools and into our client (Selenium)
		 // This network traffic consists of events. Events are nothing but activities that are triggered.
		 
		 // Under the 'Fetch Domain', 
		 // there exists the method of 'Fetch.enable':===> it will help Selenium listen to ALL of the network
		 // activities of the API(s) that we're going to mock. 
		 
		 /* Fetch.enable
		  * Description: "Enables issuing of requestPaused events. A request will be
		  * paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth."
		  * 
		  *               parameters:
		  *               
                           patterns             array[ RequestPattern ]
                           (Optional)
                                                 If specified, only requests matching any of these patterns will 
                                                 produce fetchRequested event and will be paused until clients response. If not 
                                                 set, all requests will be affected.
                     
                           handleAuthRequests    boolean
                           (Optional)
                                                 If true, authRequired events will be issued and requests will be paused 
                                                 expecting a call to continueWithAuth.
		  * 
		  * Selenium developers have made the use of this method possible as it already exists in library. Shown below:
		  * ( No need to write our own custom method for it, unless you want to do it for fun !!):
		  * 
		  * devTools.send(Fetch.enable(Optional<List<RequestPattern>> patterns, 
				                    Optional<Boolean> handleAuthRequests):Command<void>- Fetch
				                    )
				                    
		    The 1st argument 'patterns':
		                                Indicates that, one can narrow down the listening by the client (Selenium)
		                                by targeting specific request(s) via providing a pattern of request(s) that
		                                you want to listen to such as:
		                                
		                                .css .JPG, a request call(s) with a specific authorName. So, if you mention
		                                regular expressions then Selenium will only listen to that request. But for
		                                now in our example, I'm going to leave it empty so that Selenium will listen
		                                to each and every call made on our web app.
		  */
		 
		 
		
		 
		 
		 //driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		 devTools.send(Fetch.enable(Optional.empty(), Optional.empty())); // values for arguments are absent but not null.
		
		 /*Now, on the Chrome DevTools Protocol web page:
		  *(https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/learn/lecture/29061646#questions)
		  *
		  *There is an event called 'Fetch.requestPaused':
		  *		In our example, when I click on 'Virtual Library', a request will be sent. Subsequently, a response is received.
		  *     Now before this request is sent, I can manipulate it.
		  *     And this is made possible by utilizing-     The Event of "Fetch.requestPaused"
		  *     This event will be fired whenever the application is READY to send any request to the API.
		  *     
		  *     If we read the DESCRIPTION from the web page:
		  *     
		  *    "Fetch.requestPaused"   Description: =====>
		  * 
              ""Issued when the domain is enabled and the request URL matches the specified filter. The request is 
                paused until  the client responds with one of continueRequest, failRequest or fulfillRequest.""
                
               It's basically saying: 
               - The request will be paused (= From being sent to the API).
               - We will: A- Fire this Event    B- Make the changes    C- Explicitly mention 'continueRequest's so that
                 the paused request proceeds ( or mention  failRequest or fulfillRequest or/are whatever mentioned in description)
                
                So we are going to send the modified request instead of the original one.
		  *    
		  */
		 // Now, from Selenium I want to listen till that EVENT is FIRED. This is implemented in code as follows:
		  // The lambda operation -> is applied on ALL requests made, all at the same time [Diff. between it and 'for-loops'].
		 
		 // if .contains() returns true: Then, we successfully got hold of that specific GET request [made when
		 // clicking on the button of "Virtual Library", which returns a response rendering the UI records, I 
		 // extensively  talked about in this example], that I am now going to mock.
		 // 
		 devTools.addListener(Fetch.requestPaused(), request ->     // Additional: Check the code implementation of .addListener().
		 { // this Event emits request object.
		if( request.getRequest().getUrl().contains("shetty"))
		{
			String mockedUrl= request.getRequest().getUrl().replace("=shetty", "=BadGuy");
			System.out.println(mockedUrl);
			
			//Now,  C- Explicitly mention 'continueRequest's so that the paused request proceeds
			// AND this is where one MOCKS the data !
			/*
			 * [https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#method-continueRequest]:
			 * 
			 * Fetch.continueRequest #
               DESCRIPTION: ""Continues the request, optionally modifying some of its parameters""
			 * 
			 * PLUS Study/ look up all of its parameters (To mock the data) on the web page that are:
			 * 
			 * 1- requestId       RequestId
                  (Mandatory)     An id the client received in requestPaused event.
                  
               The rest are optional: 
               2- url  3- method  4- postData  5- headers  6- interceptResponse
			 */
			// How to give the mandatory value of "requestId" ?: Well, it belongs to the matching condition of .contains():
			  // got get it: request.getRequestId().
			//Now, replace the original url with the mocked url [PLUS EXACT same info] to now be sent as a Request to the API
			
			// Note, code implementation behind .continueRequest() shows that some arguments have the 'Optional'
			// data type. So, use the formula .Optional.of() to covert the data from 'String' to the 'Optional' data type, that's
			//going to be returned. This is not to be confused with having optional argument(s).
			
			// Now, why do you think that the data has to return the optional data type ?
			//
			 devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()), 
					 Optional.empty(), Optional.empty(), Optional.empty())); 
			 // Optional.empty() since it's a GET request (Does not have a pay load = doesn't have 'Post Data'). Hence.
			  // no headers exist either.
			
			 // argument(s) used to mock the data.
			 // Network.getRequestPostData(null)
			
		}
		else {  // if the request with the URL containing "shetty" not found, then send the requests as they are WITHOUT mocking anything
			
			 devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of( request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()), 
					 Optional.empty(), Optional.empty(), Optional.empty())); 
		}
		
		});
		 driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		 driver.findElement(By.cssSelector("button[routerlink*= 'library']")).click();
		 
		 /* Now verify that: 1- the warning exists (Associated with 1 record only existing) 
		                     2- The actual message is the expected one.
		                     
		                     */
		 // STILL UNDER CONSTRUCTION 
		 
		
		 
	}

}
