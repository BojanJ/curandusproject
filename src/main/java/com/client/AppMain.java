package com.client;

import java.sql.Timestamp;

import com.database.Project;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		Project project= new Project();
		
//		boki		
//		  string AccountSid = "AC09897ed28e3ca84c002dc7597151d9b3";
//          string AuthToken = "244af8201cd17bbf3000dcabd8e2b01b";
//          +13478092620
          
	//	Twilio.init("AC5dc88a4ccce105c4e7a0335d3d95b6d8", "eeb9e4447f5ec91bdd60cf479b780633");
		
        //  American Account 	// Twilio.init("AC5dc88a4ccce105c4e7a0335d3d95b6d8", "eeb9e4447f5ec91bdd60cf479b780633");
		//Vanja ("+16692260877"),
        //Twilio.init("AC389ed5b9f9f774b7383cbf88af065f46", "0559b9f3af2fa00c93cd25285deb036b");
		
//	    Message message = Message.creator(new PhoneNumber("+16692260877"),
//	        new PhoneNumber("+12242316531"), 
//	        "Test za sms poraka preku twilio?").create();
		
		
	    
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toString().replace(":", "").replace("-", "").replace(".", "").replace(" ", ""));
        
		int randomNumber = (int) Math.floor(Math.random() * 1000000);
	    System.out.println("Vlezeeee i ova e random number: "+randomNumber);

	  //  System.out.println(message);	
	    
//          American Account 	// Twilio.init("AC5dc88a4ccce105c4e7a0335d3d95b6d8", "eeb9e4447f5ec91bdd60cf479b780633");
//		Vanja ("+16692260877"),
        Twilio.init("AC3cdf4cf57d4c7d9b1e99fe5b5317af5c", "fb8e434eff426f4807559adfe04b9542");
		
	    Message message = Message.creator(new PhoneNumber("+16692260877"),
	        new PhoneNumber("+15043157825"), 
	        "Test za sms poraka preku twilio?").create();	    

	}

}
