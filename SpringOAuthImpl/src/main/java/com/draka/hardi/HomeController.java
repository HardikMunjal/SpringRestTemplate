package com.draka.hardi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;







//import org.omg.DynamicAny.NameValuePair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
//import org.omg.CORBA.NameValuePair;
import org.omg.CORBA.portable.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.http.NameValuePair;
//import com.polestar.cps.model.Contact;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	
	@RequestMapping(value="/token1")
	public String editContact(HttpServletRequest request,RedirectAttributes redirectAttributes) {
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    redirectAttributes.addAttribute("Content-Type","application/x-www-form-urlencoded");
	    redirectAttributes.addAttribute("code", code);
	    redirectAttributes.addAttribute("state", state);
	    redirectAttributes.addAttribute("client_id","1235");
	    redirectAttributes.addAttribute("client_secret","938debca-65d2-4571-a10d-134053f78d51");
	    redirectAttributes.addAttribute("grant_type","authorization_code");
	    //h.setContentType("application/x-www-form-urlencoded");
	    
	   // return token;
	    
	    return "redirect:https://persona.paytm.com/oauth2/token";
	}
	
	@RequestMapping("/listUsers")
	
	     public ModelAndView listUsers() {
		
		
		
		
	
	    RestTemplate restTemplate = new RestTemplate();
	    
	   String url="http://localhost:8080/SpringServiceJsonSample/service/user/"; 
	
	   List<LinkedHashMap> users=restTemplate.getForObject(url, List.class);
	   
	   //restTemplate
	   HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	
	         return new ModelAndView("listUsers", "users", users);
	
	     }
	
	
@RequestMapping("/tokenssasd")
public String getT(HttpServletRequest request1)throws IOException {
	
	String code = request1.getParameter("code");
    String state = request1.getParameter("state");
    
    getToken(code,state);
	
	//String abc=null;
	return null;
}








	
public void getToken(String code,String state) {
    // Create a new HttpClient and Post Header
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://persona.paytm.com/oauth2/token");
    httppost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");

    try {
        // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
        nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nameValuePairs.add(new BasicNameValuePair("code", code));
        nameValuePairs.add(new BasicNameValuePair("state", ""));
        nameValuePairs.add(new BasicNameValuePair("client_id", "api-int"));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", "http://localhost:3000/token"));
        nameValuePairs.add(new BasicNameValuePair("client_secret", "251b2340185643ac7522ceee74287b5b"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Execute HTTP Post Request
        HttpResponse response = httpclient.execute(httppost);

        String responseText = EntityUtils.toString(response.getEntity());

        System.out.println(responseText);

       // JSONObject responseJson = new JSONObject(responseText);

     //   mAccessToken = responseJson.getString("access_token");

       // autorizeWithFullFilment();

    }
    //catch (ClientProtocolException e) {
      //  e.printStackTrace();
   // }
catch (IOException e) {
        e.printStackTrace();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
	
	
	
	
	
	@RequestMapping("/token")
	
    public String listUsers1121(HttpServletRequest request1,HttpSession session)throws IOException {

		String code = request1.getParameter("code");
	    String state = request1.getParameter("state");
		
   RestTemplate restTemplate = new RestTemplate();
   
  String url="http://persona.paytm.com/oauth2/token"; 

  MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
  
  map.add("client_id", "api-int");
  map.add("client_secret", "251b2340185643ac7522ceee74287b5b");
  map.add("grant_type", "authorization_code");
  map.add("code", code);
  map.add("state", state);
  //nameValuePairs.add(new BasicNameValuePair("redirect_uri", "http://localhost:3000/token"))
  map.add("redirect_uri", "http://localhost:3000/token");
  
  /*
  Map<String, String> map = new HashMap<String, String>(5);
  map.put("client_id", "api-int");
  map.put("client_secret", "251b2340185643ac7522ceee74287b5b");
  map.put("grant_type", "authorization_code");
  map.put("code", code);
  map.put("state", state);
  */

  HttpHeaders headers = new HttpHeaders();
  
 //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
 headers.set("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//headers.set("Accept-Encoding","gzip,deflate");
 


  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

  
  
  //byte[] encodedAuth = Base64.getEncoder().encode(request.getBytes(Charset.forName("US-ASCII")));
  
  
 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
  
      
  messageConverters.add(new FormHttpMessageConverter());
  messageConverters.add(new MappingJacksonHttpMessageConverter());
  restTemplate.setMessageConverters(messageConverters);
  
  
  
  LoginResponse response = (LoginResponse) restTemplate.postForObject(url, request, LoginResponse.class); 	
 
  
  
  
  String TokenSession=response.loginToken;
  int TokenExpired=response.xpiredToken;
  
  
  session.setAttribute("Token", TokenSession);
  session.setMaxInactiveInterval(TokenExpired);
  
  System.out.println(response.loginToken);
  System.out.println(response.toString());
  System.out.println(request.getBody().toString());
  System.out.println(request.getHeaders());
  return response.toString();
  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/catalogList")
	
    public String listU(){

		
		
		
   RestTemplate restTemplate = new RestTemplate();
   
  String url="https://catalogadmin-dev.paytm.com/v1/merchant/21492/catalog.json?authtoken=kvGedhP7QeuGkjHUCIPi-A"; 

  
  Catalog cat = (Catalog) restTemplate.getForObject(url,Catalog.class);
	
  System.out.println(cat.sku);
  System.out.println(cat.price);
  
  
  //System.out.println(response.toString());
  //System.out.println(request.getBody().toString());
 // System.out.println(request.getHeaders());
  return cat.toString();
  
	}
	
	
  @RequestMapping("/catalog")
  
       public ModelAndView listUsers1(HttpServletRequest request) {
	  
	  HttpSession session = request.getSession(true);
	  
	  
	  
		String Token = (String)session.getAttribute("Token");
		
		if(Token==null){
			System.out.println("sorry boss");
			return new ModelAndView("home");
		
		}
		
System.out.println(Token);

  
      RestTemplate restTemplate = new RestTemplate();
  
     String url="https://catalogadmin-dev.paytm.com/v1/merchant/21492/catalog.json?authtoken="+Token+"";   
   
     List<LinkedHashMap> catalog=restTemplate.getForObject(url, List.class);
  
           return new ModelAndView("list", "catalog", catalog);
  
       }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @RequestMapping("/dispUser/{userid}")
  
       public ModelAndView dispUser(@PathVariable("userid") int userid) {
  
      RestTemplate restTemplate = new RestTemplate();
  
     String url="http://localhost:8080/SpringServiceJsonSample/service/user/{userid}";
  
     Catalog user=restTemplate.getForObject(url, Catalog.class,userid);
  
           return new ModelAndView("dispUser", "user", user);
  
       }

	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/tokenm")
	
    public ModelAndView listUsers1(ModelAndView model,HttpServletRequest request,RedirectAttributes redirectAttributes)throws IOException {

		String code = request.getParameter("code");
	    String state = request.getParameter("state");
		
   RestTemplate restTemplate = new RestTemplate();
   
  String url="https://persona.paytm.com/oauth2/token"; 

 // List<LinkedHashMap> users=restTemplate.getForObject(url, List.class);
  MultiValueMap<String,String> params=new LinkedMultiValueMap<String,String>();
  //params.set("Accept","application/json");
  //params.set("Content-Type","application/x-www-form-urlencoded");
  MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
  
  map.add("client_id","api-int");
  map.add("client_secret","251b2340185643ac7522ceee74287b5b");
  map.add("grant_type","authorization_code");
  map.add("code",code);
  map.add("state",state);
  
  
  //restTemplate
  /*
  HttpHeaders headers = new HttpHeaders();
  //URI uri = UriComponentsBuilder
	//	    .fromHttpUrl("http://localhost:8080/uno/json")
		//    .queryParam("eso", "tru").build().toUri();
   
headers.set("Accept", "");;
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
*/
  
HttpHeaders requestHeaders=new HttpHeaders();

requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//requestHeaders.set("Accept","*/*");
//requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//requestHeaders.set("Accept-Encoding","deflate");
//requestHeaders.setContentLength(map.size());
//requestHeaders.set("Content-Type","application/x-www-form-urlencoded");


HttpEntity<MultiValueMap<String, String>> entity1 = new HttpEntity<MultiValueMap<String, String>>(map,requestHeaders);

List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

messageConverters.add(new FormHttpMessageConverter());
messageConverters.add(new StringHttpMessageConverter());

messageConverters.add(new MappingJacksonHttpMessageConverter());

restTemplate.setMessageConverters(messageConverters);

//restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

  ResponseEntity<String> result = restTemplate.exchange(url,HttpMethod.POST,entity1,String.class);
//String result=restTemplate.postForObject(url, entity1, String.class);

       System.out.println(result);


        return new ModelAndView("listUsers", "users",listUsers());

    }
	
	
	
	
	
	
	
	
		
	
@RequestMapping (value = "/tokenxx", method = { RequestMethod.GET })
	
public String listUsers111(HttpServletRequest request)throws IOException {

		String code = request.getParameter("code");
	    String state = request.getParameter("state");
        String url="https://persona.paytm.com/oauth2/token";    
        //String Data="code="+URLEncoder.encode(code,"ASCII")+"&client_id="+URLEncoder.encode("api-int","ASCII")+"&client_secret="+ URLEncoder.encode("251b2340185643ac7522ceee74287b5b","ASCII")+ "&grant_type="+ URLEncoder.encode("authorization_code","ASCII")+ "&state="+URLEncoder.encode("a1b2c3d4","ASCII");
        
       

      String Data=URLEncoder.encode("code") + "=" + URLEncoder.encode(code);
      Data += "&" + URLEncoder.encode("state") + "=" + URLEncoder.encode(state);
      Data += "&" + URLEncoder.encode("client_id") + "=" + URLEncoder.encode("api-int");
      Data += "&" + URLEncoder.encode("client_secret") + "=" + URLEncoder.encode("251b2340185643ac7522ceee74287b5b");
      Data += "&" + URLEncoder.encode("grant_type") + "=" + URLEncoder.encode("authorization_code");
      
  URL url1;
  
  HttpURLConnection connection = null;  
  try {
    //Create connection
    url1 = new URL(url);
    
    connection = (HttpURLConnection)url1.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    
    
    connection.setDoInput(true);
    connection.setDoOutput(true);

    //Send request
    DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
    wr.writeBytes (Data);
    wr.flush ();
    wr.close ();

    //Get Response    
    DataInputStream is=new DataInputStream (connection.getInputStream());
    
    //InputStream is = connection.getInputStream();
    
    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    String line;
    StringBuffer response = new StringBuffer(); 
    while((line = rd.readLine()) != null) {
      response.append(line);
      response.append('\r');
    }
    rd.close();
    System.out.println(response.toString());
    return response.toString();

  } catch (Exception e) {

    e.printStackTrace();
    return null;

  } finally {

    if(connection != null) {
      connection.disconnect(); 
    }
  }
      }















@RequestMapping("/tokenccc")

public String editContactsss(HttpServletRequest request)throws Exception{
	
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String out=null;
	        // Create a new HttpClient and Post Header
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://persona.paytm.com/oauth2/token");
	        httppost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
	            nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
	            nameValuePairs.add(new BasicNameValuePair("code", code));
	            nameValuePairs.add(new BasicNameValuePair("state", state));
	            nameValuePairs.add(new BasicNameValuePair("client_id", "api-int"));
	            nameValuePairs.add(new BasicNameValuePair("redirect_uri", "http://localhost:3000/token"));
	            nameValuePairs.add(new BasicNameValuePair("client_secret", "251b2340185643ac7522ceee74287b5b"));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);

	            String responseText = EntityUtils.toString(response.getEntity());

	            System.out.println(responseText);
	            out= responseText.toString();

//	            JSONObject responseJson = new JSONObject(responseText);

	//            mAccessToken = responseJson.getString("access_token");

	  //          autorizeWithFullFilment();

	        } 
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    
	    return out;
	    
	    
	/*    
	String url = "http://persona.paytm.com/oauth2/token";
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));

	nameValuePairs.add(new BasicNameValuePair("client_id","api-int"));
	nameValuePairs.add(new BasicNameValuePair("client_secret", "251b2340185643ac7522ceee74287b5b"));
	//nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
	nameValuePairs.add(new BasicNameValuePair("code", code));
	nameValuePairs.add(new BasicNameValuePair("state", state));
	StringBuffer postResult =  sendPost1(url, nameValuePairs);


return postResult.toString();
}


private StringBuffer sendPost1(String url, List<NameValuePair> postParams)throws Exception {

    HttpPost post = new HttpPost(url);
    HttpClient client = new DefaultHttpClient();

    
    // add header
    //post.setHeader("Host", "accounts.google.com");
    //post.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/
    //q=0.8
    //post.setHeader("Accept-Language", "en-US,en;q=0.5");
    //post.setHeader("Connection", "keep-alive");
    //post.setHeader("Referer", "https://accounts.google.com/o/oauth2/token");
	        /*
    post.setHeader("Content-Type", "application/x-form");
    post.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");
    post.setEntity(new UrlEncodedFormEntity(postParams));

    HttpResponse response = client.execute(post);

    int responseCode = response.getStatusLine().getStatusCode();

    BufferedReader rd = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));

    StringBuffer result = new StringBuffer();
    String line = "";
    while ((line = rd.readLine()) != null) {
        result.append(line);
    }
   
 */   
	        
}

































	
	
	
	
	
	
	
	
	
	
	
@RequestMapping("/token111")
	
    public ModelAndView listUsers11(ModelAndView model) {

		
		
   RestTemplate restTemplate = new RestTemplate();
   
  String url="https://persona.paytm.com/oauth2/authorize"; 

 // List<LinkedHashMap> users=restTemplate.getForObject(url, List.class);
  MultiValueMap<String,String> params=new LinkedMultiValueMap<String,String>();
  //params.set("Accept","application/json");
  //params.set("Content-Type","application/x-www-form-urlencoded");
  //MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
  Map<String, String> map = new HashMap<String, String>();
  map.put("response_type","code");
  map.put("client_id","api-int");
  map.put("Username","praful.kumar@squeakee.com");
  map.put("password","praful");
  map.put("noredirect","true");
  
  
  
  HttpHeaders headers1 = new HttpHeaders();
  headers1.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//HttpEntity<MultiValueMap<String, String>> entity1 = new HttpEntity<MultiValueMap<String, String>>(map,headers1);

List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

messageConverters.add(new FormHttpMessageConverter());
messageConverters.add(new StringHttpMessageConverter());
restTemplate.setMessageConverters(messageConverters);



String result = restTemplate.getForObject(url, String.class,map);
       System.out.println(result);


        return new ModelAndView("listUsers", "users",listUsers());

    }
	
	
	@RequestMapping(value="/token2")
	public ModelAndView list5(ModelAndView model,HttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException{
		String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    
	    
	    
	    model.setViewName("token");
	    
	    model.addObject(code);
	    model.addObject(state);
	
	 
	    return model;
	}
	
	//@RequestMapping(value="/code")
	//public String TokenFetch(HttpServletRequest,HttpSErvletResponse)
	
	
	
	
	//Done by Atul
	
	@RequestMapping("/tokenvbvbvb")
	public String getT1(HttpServletRequest request1)throws IOException {
		
		String code = request1.getParameter("code");
	    String state = request1.getParameter("state");
	 
	    
	    
	    PostMethod post = new PostMethod("https://persona.paytm.com/oauth2/authorize");
	    post.setRequestHeader ("Content-Type", "application/x-www-form-urlencoded");
	    
	    org.apache.commons.httpclient.NameValuePair[] data = {
	    		new org.apache.commons.httpclient.NameValuePair("grant_type", "authorization_code"),
	    		new org.apache.commons.httpclient.NameValuePair("client_id","api-int"),
	    		new org.apache.commons.httpclient.NameValuePair("client_secret", "251b2340185643ac7522ceee74287b5b"),
	    		new org.apache.commons.httpclient.NameValuePair("grant_type", "authorization_code"),
	    		new org.apache.commons.httpclient.NameValuePair("code", code),
	    		new org.apache.commons.httpclient.NameValuePair("state", state)
	    
	    };
		post.setRequestBody(data);    

		org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();
	    int result = httpclient.executeMethod(post);
	    
	    BufferedReader rd = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));

	    StringBuffer buffer = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) 
	        buffer.append(line);
	    
	    System.out.println(line);
	    return null;
	    
	   
	}
		
}
