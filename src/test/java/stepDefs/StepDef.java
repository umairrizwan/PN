package stepDefs;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.HelperUtils;
import resources.UserResources;
import resources.UsersObject;

public class StepDef extends HelperUtils{
	
	public RequestSpecification request;
	public Response response;
	public static String userId;
	public int actualResponseCode;
	
	
	@Given("Add user payload with values {string} {string}")
	public void add_user_payload_with_values(String name, String job) {
		
		request = given().log().all().spec(HelperUtils.requestSpecifications())
				
				//Setting name and job using Serialization
				.body(UsersObject.setUsers(name, job));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
	    UserResources resourceVal = UserResources.valueOf(resource);
	    
	    if(httpMethod.contains("POST"))		
		{
		
		 	response = request.when().post(resourceVal.getResource());
		 	userId = getJson(response, "id");
		}
		
		
		else if (httpMethod.contains("GET"))
		{
			response = request.when().get(resourceVal.getResource());
			actualResponseCode= response.getStatusCode();
			
		}
	    
		else if (httpMethod.contains("PUT"))
		{
			response = request.when().put(resourceVal.getResource());
			
		}
		
		else if(httpMethod.contains("DELETE"))
		{
			response = request.when().delete(resourceVal.getResource());
			actualResponseCode= response.getStatusCode();
			
		}
	    
	    
	}

	@Then("The response in API call is success with status code {int} and {string} and {string} should be verified")
	public void the_response_in_API_call_is_success_with_status_code_and_and_should_be_verified(int responseCode, String ExpectedName, String ExpectedJob) {
				
		String ActualName = getJson(response, "name");
		String ActualJob = getJson(response, "job");
		
		//Asserting response code
		Assert.assertEquals(responseCode, response.getStatusCode());
		
		//Asserting Name and Job from response
		Assert.assertEquals(ActualName, ExpectedName);
		Assert.assertEquals(ActualJob, ExpectedJob);
	}


	@Given("User calls {string} with {string} http requests")
	public void user_calls_with_http_requests(String resource, String httpMethod) {
		//GIVEN
	  	request = given().log().all().spec(HelperUtils.requestSpecifications()).queryParam("page","2");
	  	
	    //WHEN
	  	user_calls_with_http_request(resource, "GET");
	  	
	  	//THEN
	  	response.then().log().all();
	   String pageVal =	getJson(response, "page");
	   Assert.assertEquals(2, Integer.parseInt( pageVal));
	   
	   
	   
	  	
	}


	
	@Given("Update user payload with values {string} {string}")
	public void update_user_payload_with_values(String name, String job) {

     request = given().log().all().spec(HelperUtils.requestSpecifications())
				
				//Setting name and job using Serialization
				.body(UsersObject.setUsers(name, job)).pathParam("id",userId);     
 
	}
	
	
	@Given("Delete user and calls {string} with {string} http requests")
	public void delete_user_and_calls_with_http_requests(String resource, String string2) {
		  request = given().log().all().spec(HelperUtils.requestSpecifications())					
					.pathParam("id",userId);
		  
		  //WHEN
		  user_calls_with_http_request(resource, "DELETE");
	}


	@Then("The response in API call is success with status code {int}")
	public void the_response_in_API_call_is_success_with_status_code(int responseCode) {	   
		
		Assert.assertEquals(responseCode, actualResponseCode);
	
	}
	
	
	
	

}
