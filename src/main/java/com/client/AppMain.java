package com.client;

import com.database.Project;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean flag=false;
		Project project= new Project();
		
		Twilio.init("AC5dc88a4ccce105c4e7a0335d3d95b6d8", "eeb9e4447f5ec91bdd60cf479b780633");
		//Twilio.init("AC389ed5b9f9f774b7383cbf88af065f46", "0559b9f3af2fa00c93cd25285deb036b");
		
	    Message message = Message.creator(new PhoneNumber("+38971248256"),
	        new PhoneNumber("+12242316531"), 
	        "This is the ship that made the Kessel Run in fourteen parsecs?").create();

	    System.out.println(message);			

	}

}
