package ChromeDevToolsProtocol.ChromeDevTools;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;
   
                               // UNDER CONSTRUCTION 
public class Mocking_API_Requests_Responses {
	
	/* In Selenium, mocking network requests/responses is only possible with the integration 
	 * of the Chrome DevTools (As of Selenium 4.0 and up).
	 * 
	 * Let's demonstrate a scenario:
	 * On "https://rahulshettyacademy.com/angularAppdemo/", click on "Virtual Library":
	 * Many records will be displayed. Notice that each record corresponds to a section 
	 * in the Json response of the GET request. [Happens to be a GET request].
	 * 
	 * Now, I want to verify a warning message that only appears when 
	 * there is only one record displayed.
	 * 
	 * The traditional approach (Before mocking APIs, in Selenium, was possible) is to obtain 
	 * a profile that only displays one record. There exists 2 major issues in such approach:
	 * 
	 * 	1- Data acquisition is, in and by itself, one of the common challenges in QA 
	 *     (Who to ask, will you get it on time/ will you have access to database, etc.)
	 *  
	 *  2- Even if the data is acquired: What if you one day, login back to that profile to 
	 *     only discover that the one record [associated with a specific message that I need to validate]
	 *     is accompanied by other record(s) so that the warning message is no longer displayed and hence
	 *     can not be verified ?.
	 *     
	 * The solution is quite simple: Mock the request or response of the associated API, so that
	 * only one record appears in the UI.
	 * 
	 * Now, the message associated with only 1 record existing in the UI should appear, where we're going to 
	 * verify:  1- That the message appears 2- That its content is the expected one.
	 * 
	 *  Start here:  
	 *  
	 *  For mocking the Request:=======>
	 *  								In the request, is there data that could be changed
	 *                                  so that, as a result, the inevitable changes in the Json reponse
	 *                                  renders the desired outcome in the UI ?
	 *                                  
	 *  For mocking the Response:======>
	 *                                 In the Json body reponse, is there data that could be changed
	 *                                 so that, as a result, the desired outcome is rendered in the UI.
	 *                                 
	 *   Notice: 'Data' here could really mean anything. it could be a value(s) or section(s). As simple as that !.
	 *                                 
	 *  So In Postman: Upon examining the Associated API, one concludes the following (Do that b4 proceeding in reading the notes):
	 * 
	 *              The GET Request of: https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty
	 *              will only return one record if you change the author name, to something else.
	 *              so one could mock the request this way. *****************
	 *              
	 *              Also, modifying the Json response body so that it ONLY has one section, will make the 
	 *              UI display only one record.
	 *              So one could mock the response this way.  *****************
	 *              
	 * So the ability to mock the request/ response in this way, opens the door to incorporating many many
	 * edge cases/ negative scenarios test cases. 
	 * 
	 *		for example: you should experiment to find out/ or simply ask ? about the max numb of records allowed in the UI
	 * 		that will cause a warning message to be displayed ?
	 *	    and once you find out about this number, then you can validate this message 
	 *      (1- is triggered 2- the content is the expected one).
	 * 
	 *      and this will be done- by mocking the request or the response (Whatever that works and makes sense !).
	 */
	
	/* Before, we used the 'Network' domain to track the REAL network activities of the page. 
	 *  = to log 'the REAL' everything related to them: Which included, but not limited to, logging the REAL API requests/ responses.
	 *  
	 *  But, if you want to intercept & then mock your existing calls then there exists the  "Fetch Domain"
	 *  Description: "A domain for letting clients substitute browser's network layer with client code."
	 *  
	 *  Note: 'clients' here are us Selenium users (Or any other automation tool that's compatible with these protocol methods).
	 *  Note: 'Substituting browser's network layer" = Intercepting the desired API(s) and substituting (=replacing =mocking) data 
	 *  in the request or the response.
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void mockingApi()
	{
		 ChromeDriver driver = new ChromeDriver();
	 	//EdgeDriver driver= new EdgeDriver(); 
		 DevTools devTools= driver.getDevTools();
		 devTools.createSession();
		 
		 // First I have to enable:
		   //Just I've done earlier with the 'Networking Domain', I have used 'enable' 
		   // so that I can listen from my client (Selenium) to the network activity.
		 
		 // Under the 'Fetch Domain', 
		 // there exists the method of 'Fetch.enable':===> it will help Selenium listen to the network
		 // activities of the API(s) that we're going to mock. 
		 
		 // Description: "Enables issuing of requestPaused events. A request will be
		 // paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth."
		 //devTools.send(null) // we're going to send the command of  'Fetch.enable'   //8:20
		 
		 
		
		
	}

}
