package com.client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.database.Database;
import com.database.Project;
import com.model.PatientsCascade;
import com.model.Providers;
import com.model.TreatmentItem;

public class ProjectManager {
	
	
	public Providers InsertProvider(Providers p_provider) throws Exception {
		Providers t_items= new Providers();
		try {
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			t_items=project.InsertProvider(connection, p_provider);
	
	} catch (Exception e) {
		throw e;		
	}
		return t_items;
	}	
	public List<PatientsCascade> getPatientsByProvider(int PatientID) throws Exception {
		List<PatientsCascade> t_items= null;
		try {
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			t_items=project.getPatientsByProvider(connection, PatientID);
	
	} catch (Exception e) {
		throw e;		
	}
		return t_items;
	}
	
	public boolean AddContactPatient(Integer providerid, String telephoneNumber, String firstName, String lastName) throws Exception {
		boolean flag;
		try {
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.AddContactPatient(connection, providerid, telephoneNumber, firstName, lastName);
	
	} catch (Exception e) {
		throw e;
	}
	return flag;
	}	
	
	public boolean AddContactDoctor(Integer providerid, String telephoneNumber, String firstName, String lastName) throws Exception {
		boolean flag;
		try {
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.AddContactDoctor(connection, providerid, telephoneNumber, firstName, lastName);
	
	} catch (Exception e) {
		throw e;
	}
	return flag;
	}
	
	public List<TreatmentItem> getTreatmentItemsByTreatment(int TreatmentID, String TT) throws Exception {
		List<TreatmentItem> t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getTreatmentItemsByTreatment(connection, TreatmentID, TT);
		
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}			
	
}	
