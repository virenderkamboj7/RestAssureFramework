package TestCases;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BasePackage.ApisUtil;
import BasePackage.BaseClass;
import junit.framework.Assert;
import pojo.createuser.CreateUserRoot;

public class TC_Test_002 extends BaseClass {

	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.serializeNulls().setPrettyPrinting().create();
	
	@Test
	public void CreateUser() {
	
		//Excute post request with valid parameter
		ApisUtil.CreateUser();
		
		Assert.assertEquals(201, response.getStatusCode());
		
		CreateUserRoot CreateUserBody = gson.fromJson(response.asString(), CreateUserRoot.class);
		
		
		
		System.out.println("New user with \""+CreateUserBody.getId()+ "\" is created!");
		
		
		
	}
}
