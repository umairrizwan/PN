package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class HelperUtils {
	
	public static RequestSpecification req;
	public RequestSpecification res;
	public Response response;	
	
	
	public static RequestSpecification requestSpecifications()
	{
		
		//ADDING THIS IF CHECK BECAUSE IN LOGGING FILE THE RECORD IS OVER WRITE, SO IF THERE ARE TWO RECORDS SO ONLY LAST ONE WAS SHOWING 
		//WE HAVE TO MAKE SURE THAT IF REQ OBJECT ALREADY EXECUTED IT SHOULD NOT SET AS NULLL
		if(req==null)
		{
		try
		{
		PrintStream logging = new PrintStream(new File("logging.txt"));
		 req = new RequestSpecBuilder().setContentType(ContentType.JSON)
					.setBaseUri(readGlobalProperties("baseUri"))
					.addFilter(RequestLoggingFilter.logRequestTo(logging))
					.addFilter(ResponseLoggingFilter.logResponseTo(logging))
					//.addQueryParam("key", "qaclick123")
					.build();
		 
		
		}
		
			catch(FileNotFoundException e)
			{
				System.out.println("Yourlogging file is not properly created please check HelperUtils.java class");
			}
		
		}
		
		 return req;
	}
	
	public static String readGlobalProperties(String key)
	{
		 
		try
		{
		 Properties prop = new Properties();
		FileInputStream inp = new FileInputStream("C:\\Users\\Malta\\eclipse-workspace\\Place-BDD\\src\\main\\java\\resources\\global.properties");
		prop.load(inp);
		
		return prop.getProperty(key);
		
		}
		
		
		catch(FileNotFoundException e)
		{
			System.out.println("The file is not present or link to another file");
		}
		
		catch(IOException e)
		{
			System.out.println("There is IO exception please check your global properties file");
		}
		return key;
		
		
	}
	
	public  String getJson(Response response, String key)
	{
		String res = response.asString();
		JsonPath jpath = new JsonPath(res);
		
		return jpath.get(key).toString();
	}
	
}
