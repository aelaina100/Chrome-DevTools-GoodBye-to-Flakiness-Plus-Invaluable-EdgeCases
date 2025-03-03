package ChromeDevToolsProtocol.ChromeDevTools;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

                                     // UNDER CONSTRUCTION
public class D_GeoLocation_Emulation {
	/*
	 * Let's look up: "Emulation.setGeoLocationOverride" on CDP web page.
	 * As of now it is at: https://chromedevtools.github.io/devtools-protocol/tot/Emulation/
	 * 		Description: "Overrides the Geolocation Position or Error."
	 * 
	 * Generally, one's search results are based on their geographical location.
	 *            Also, the language (While browsing) is based on that.
	 *            Ex- When in Spain, browsing results are in Spanish. This is especially true for
	 *            the most popular e-commerce web sites such as netflix.com where if you're in
	 *            USA, it will be displayed in English. In Greece, it will be in Greek and so on.
	 *            
	 * So, localization testing ===> is testing whether a web site is changing its language with 
	 *                               respect to the location of the user.
	 * Hence, earlier if one wanted to verify whether the content of netflix.com is displayed
	 * in Mandarin, then they had to log into the Chinese server to verify the language change. However;
	 * 
	 * from your own usual location ONLY, you can emulate the wanted geolocation by manually changing the chromium
	 * based browser's language from the chrome DevTools UI. This manual process could be automated as the integration
	 * of Chrome DevTools with Selenium is now possible as of version 4.0.
	 *         
	 * Note: The method used will need the longitude and altitude:
	 * acquire them from Google earth: Search the geo-location. Data will be provided in the right-hand corner.
	 */
	@Test
	public void geoLocationEmulate()                          // UNDER CONSTRUCTION    
	{
		//EdgeDriver driver= new EdgeDriver();
		ChromeDriver driver= new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		/* Now, for "Emulation.setGeolocationOverride" we should use .send() since
		 * setGeolocationOverride() exists in the Selenium library (Under 'Emulation' class).
		 * Nevertheless, I'm going to use .executeCDPCommand() just for the sake of practicing 
		 * some coding & especially since the arguments are only 3.
		 *
		 */
		
	   Map<String,Object> coordinates= new HashMap<String,Object>(); 
	   coordinates.put("latitude",37);
	   coordinates.put("longitude",22);
	   coordinates.put("accuracy", 4); // accuracy by how many meters ? (They used 4m)
	   //Tip: consult CDP web page plus the already developed Selenium 
	   //setGeolocationOverride method by double-clicking on it in editor after typing in: Emulation.setGeolocationOverride
	   
	  // devTools.send(Emulation.setGeolocationOverride(java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
	  driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);
       // Editor method suggestion: the 2nd argument is: MAP<String,Object> parameters
       
       //Now, values have been set, programmatically, in ChromeDev tools.
       driver.get("https://www.google.com/");
       
       //Interview question: Automation challenges (List them + the inability to test whether a web page can 
        // display multiple languages as that require logging to the server of the specific country Nevertheless, it is now
          ///possible ith the integration of Chrome DevTools where geolocations can be mocked utilizing the method
             // of Emulation.setGeolocationOverride
		
	}

}
