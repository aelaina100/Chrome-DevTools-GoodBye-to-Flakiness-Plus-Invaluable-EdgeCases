package ChromeDevToolsProtocol.ChromeDevTools;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.fetch.Fetch;
import org.openqa.selenium.devtools.v133.fetch.model.RequestPattern;
import org.testng.annotations.Test;

public class G_Failing_API_Requests {
	/*
	 * I will show how to fail a specific request(s) to a specific API(s).
	 * 		Sometimes, when a request fails a specific message should appear in the UI. 
	 *      such message should be validated (1- It does appear 2- its content is the expected one).
	 * 
	 * For example, LinkedIn shows such messages when certain requests fail denoting that their server is encountering
	 * an issue, hence prompting you to try again.
	 * 
	 * We need to have the knowledge of creating scripts that fail certain request(s) of certain API(s), so 
	 * that their associated warning/error message(s) can be validated. Now, prior to Selenium 4.0 such a feat
	 * was not possible. Nevertheless, as of Selenium 4.0 the integration of Chrome DevTools with Selenium is made possible
	 * Hence, one can write a script that fails a certain request(s) of certain API(s), so that their associated warning/error
	 * message(s) could be validated.PLUS tons of other features which is what this tutorial project is all about.
	 * 
	 * 
	 * 
	 */
	
	@Test
	public void failingApiRequests()
	{
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		/*
		 * On (As of now): https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#method-failRequest
		 * "Fetch.failRequest"   where one could fail a request for a reason of their choice.
		 * 
		 * Description: "Causes the request to fail with specified reason."
           parameters:
                            requestId:    RequestId
                                          An id the client received in requestPaused event.

                            errorReason:  Network.ErrorReason
                                          Causes the request to fail with the given reason.

		 *  [Note: click on the above 'Network.ErrorReason', to view the list of errors one could utilize==>
		 * 
		 *  """ Network.ErrorReason #
                Network level fetch failure reason.
                Allowed Values: Failed, Aborted, TimedOut, AccessDenied, ConnectionClosed, ConnectionReset, ConnectionRefused,
                                ConnectionAborted, ConnectionFailed, NameNotResolved, InternetDisconnected, AddressUnreachable,
                                 BlockedByClient, BlockedByResponse""" 
                Where for each & every single error, developers could show a certain message on the UI web page:
                
                for example, for the error "TimeOut" in facebook ===> the UI message is: "We are having a problem with our servers"
                for example, for the error "AccessDenied" =====> the UI message is: "You don't have a permission to access this".
                
                so, we could mock any of these errors to validate its message in the UI.
                 
		 *  
		 *  In the last lecture, we continued the request (Fetch.continueRequest) after we finished mocking by passing the request
		 *  In this lecture, instead of continuing we're going to fail the request(Fetch.failRequest) after passing the request
		 */
		
		//Below- Pause the request before it goes to server, enable the method of "Fetch.enable". So, to call it from Selenium:
		
		// ?  
		// ? https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty
		 
		/*devTools.send(Fetch.enable(Optional<List<RequestPattern>> patterns, 
        Optional<Boolean> handleAuthRequests):Command<void>- Fetch  */
		
		
				/* what's the 1st argument of "patterns" ?:
				 * 		Well, instead of monitoring each & every request (There could be 20-30+ of them), you 
				 *      can monitor only a specific one by looking for a certain pattern. 
				 *      
				 *       So, I want to pause and fail a call with the URL request of (as of now):
				 *       https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty.
				 *       so, one could give the pattern of the substring of "AuthorName". Hence, 
				 *       ONLY this request will be stopped so that, ON IT, we can perform the actions
				 *       that we want to perform.
				 *       
				 *       and, if instead, in case you want to perform the actions on ALL the requests (20-30+),
				 *       you could leave the 1st argument of patterns empty "Optional.empty()".
				 * 
				 *       Now, what is the syntax of the 1st argument 'patterns' ?:
				 *       	you can only know it by viewing the code behind the .enable() method
				 *          (In the line provided above of:  devTools.send(Fetch.enable(null, null));
				 *          in order, to specifically, understand how that parameter's return type IS DESIGNED
				 *          .So: CTRL + Left-click on 'enable' method==> Fetch.class file pops up containing
				 *          many methods including the .enable() one.
				 *          
				 *    Where the 1st argument is: (Line %):
				 *    java.util.Optional<java.util.List<org.openqa.selenium.devtools.v133.fetch.model.RequestPattern>> patterns
				 *          
				 *          Notice: ".RequestPattern" is a class, so basically you need to create an object for this class
				 *          and send it here "patterns" (after the >>. shown above).
				 *          and that object should be wrapped under 'List' shown in the same one line above.
				 *          and 'List', in turn, is wrapped under 'Optional'.
				 *          
				 *          Yes, it was made complicated by the chromeDevTools developers and not the Selenium ones
				 *          because on the corresponding ChromeDev Tools web page of: 
				 *          [https://chromedevtools.github.io/devtools-protocol/tot/Fetch/#method-enable]
				 *          The argument is in the format of: 
				 *          
				 *          array[ RequestPattern ]
				 *          where the 'RequestPattern' is the class.
				 *          
				 *          so to read the line % backwards:
				 *          Create 'RequestPattern' class object (filling all the details of what you want to catch):
				 *          
				 *          so the syntax should be: new RequestPattern() 
				 *          & import the package of: Network.model ? or Fetch.model ??
				 *          well, line % clearly tell you that it's 'Fetch.model'
				 */
		 //RequestPattern rp= new RequestPattern(java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty());
	            /*
	             * and when clicking on the 'RequestPattern' class and accessing it:
	             * 		When you want to create an object, that takes arguments
	             *      because the constructor of its class is parameterized
	             *      with the 3 arguments of (All marked as optional) 
	             *      urlPattern (marked as String), resourceType, & requestStage
	             *      
	             * let me show you: 
	             * public RequestPattern(java.util.Optional<java.lang.String> urlPattern, 
	             *                       java.util.Optional<org.openqa.selenium.devtools.v133.network.model.ResourceType> resourceType, 
	             *                       java.util.Optional<org.openqa.selenium.devtools.v133.fetch.model.RequestStage> requestStage)
	             * 
	             */
		//So now, the 1st argument the object of the class 'RequestPattern' is: "*GetBook*". So that any URL request
		// containing this regular expression is ONLY tracked. (* means anything replacing it, so in here: anything before/after 'GetBook').
		// code above tells you, yes it's a string but it SHOULD also wrapped under 'Optional'
		
		/*Therefore, first parameter should be: Optional.of("*GetBook*").
		 * AND we have to convert this RequestPattern in the immediate line above to a list type (even if its 1 value !)according to:
		 * 
		 *  devTools.send(Fetch.enable(Optional<List<RequestPattern>> patterns, 
            Optional<Boolean> handleAuthRequests):Command<void>- Fetch 
        // continue immediately at: 11:58
         
		 * Now, The 2nd and the 3rd parameters: we don't need them in this example. so mark them as optional (as they are optional).
		 * 
		 */
	}

}
