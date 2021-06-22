package TestCases;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BasePackage.Apis;
import BasePackage.BaseClass;
import junit.framework.Assert;
import pojo.getSingleUser.getSingleUserRoot;

public class TC_Test_001 extends BaseClass{
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.serializeNulls().setPrettyPrinting().create();
	
	@Test
	public void get() {
		
		// Executing get request
		GetRequest(Apis.getUser);   
		
		//Verify Status code
		Assert.assertEquals(200, response.getStatusCode());  
		
		//Get object of respective pojo class
		getSingleUserRoot getSingleUserBody = gson.fromJson(response.asString(), getSingleUserRoot.class);  
		
		//Lets play with json response data
		int id = getSingleUserBody.getData().getId();  
		String email = getSingleUserBody.getData().getEmail();
		String firstName = getSingleUserBody.getData().getFirstName();
		String lastName = getSingleUserBody.getData().getLastName();
		String avatar = getSingleUserBody.getData().getAvatar();
		
		String url = getSingleUserBody.getSupport().getUrl();
		String text = getSingleUserBody.getSupport().getText();
		
		Assert.assertEquals(2, id); //Validating id
		Assert.assertEquals("janet.weaver@reqres.in", email);  //Validating email id
		
		System.out.println("id : "+id);
		System.out.println("Email : "+email);
		System.out.println("First Name : "+firstName);
		System.out.println("Last Name : "+lastName);
		System.out.println("Avatar : "+avatar);
		
		System.out.println("URL : "+url);
		System.out.println("Txt : "+avatar);
		
		
	}

}
