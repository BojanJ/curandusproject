package com.client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.database.Database;
import com.database.Project;
import com.model.ActiveTreatment;
import com.model.Patients;
import com.model.PatientsCascade;
import com.model.ProviderProvider;
import com.model.Providers;
import com.model.SavedTemplate;
import com.model.SubTreatment;
import com.model.TreatmentItem;
import com.model.TreatmentItemList;
import com.model.TreatmentItemListScroll;

public class ProjectManager {
	

	public Providers CheckProviderActivationKey(String deviceId , String phone , int inputCode) throws Exception {
		Providers t_items = new Providers();
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.CheckProviderActivationKey(connection, deviceId , phone , inputCode);
		
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}	
	
	public List<SavedTemplate> getSavedTemplate(int providerDetail) throws Exception {
		List<SavedTemplate> t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getSavedTemplate(connection, providerDetail);
		
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}	
	
	public List<TreatmentItemListScroll> getTreatmentItemListLoad(int p_activetreatmentid) throws Exception {
		List<TreatmentItemListScroll> t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getTreatmentItemListLoad(connection,p_activetreatmentid);
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}	

	public List<TreatmentItemListScroll> getTreatmentItemListScroll(int TreatmentItemListID, 
			String T_UP_DOWN, int range) throws Exception {
		List<TreatmentItemListScroll> t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getTreatmentItemListScroll(connection,TreatmentItemListID,T_UP_DOWN, range);
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}		
	
public boolean UpdateSavedTreatment(List<TreatmentItem> t_items, int p_savedtreatmentid) throws Exception
{		
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.UpdateSavedTreatment(connection, t_items, p_savedtreatmentid);
		} 
		
		catch (Exception e) {
			throw e;		
		}
			return flag;
}	
	public boolean UpdateActiveSubTreatment(List<TreatmentItem> t_items, int p_subtreatmentid) throws Exception
{		
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.UpdateActiveSubTreatment(connection, t_items,p_subtreatmentid);
		} 
		
		catch (Exception e) {
			throw e;		
		}
			return flag;
}	
	
	public int CheckNameSavedTreatment(int ProviderID, 
			String NameTreatment, List<TreatmentItem> t_items) throws Exception
	{
		int flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.CheckNameSavedTreatment(connection, ProviderID, NameTreatment, t_items);
		}
		catch (Exception e) {
			throw e;		
		}
		
		return flag;
	}	
	
	public boolean InsertSavedTreatment(int ProviderID, 
			String NameTreatment, List<TreatmentItem> t_items) throws Exception
	{
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.InsertSavedTreatment(connection, ProviderID, NameTreatment, t_items);
		}
		catch (Exception e) {
			throw e;		
		}
		
		return flag;
	}
	
	public SubTreatment InsertActiveSubTreatment(int activeTreatmentID , 
			int ProviderID, int PatientID, String NameTreatment, String SubNameTreatment, 
			List<TreatmentItem> t_items) throws Exception
{		
		int flag=0;
		SubTreatment ret_sub_t=new SubTreatment();
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			ret_sub_t=project.InsertActiveSubTreatment(connection, activeTreatmentID, ProviderID, PatientID, NameTreatment, SubNameTreatment, t_items);
		} 
		
		catch (Exception e) {
			throw e;		
		}
			return ret_sub_t;
}	

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
	
	public Patients getPatientData(int patientId) throws Exception {
		Patients t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getPatientsData(connection,patientId);
		
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}
	
	
	public TreatmentItemList updatetreatmenitemlist(TreatmentItemList t_items, int TreatmentItemListId) throws Exception
	{		
		TreatmentItemList flag;
			try{
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				flag=project.updatetreatmenitemlist(connection, t_items, TreatmentItemListId);
			} 
			
			catch (Exception e) {
				throw e;		
			}
				return flag;
	}
	
	
	
	public List<ProviderProvider> getprovidersdatabyprovider(int ProviderProviderId) throws Exception {
		List<ProviderProvider> t_items = null;
		try {
			    Database database= new Database();
			    Connection connection = database.Get_Connection();
				Project project= new Project();
				t_items=project.getprovidersdatabyprovider(connection, ProviderProviderId);
		
		} catch (Exception e) {
			throw e;
		}
		return t_items;
	}
	
	

	
	public boolean EndTreatment(int ActiveTreatmentId) throws Exception
	{
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.EndTreatment(connection, ActiveTreatmentId);
		}
		catch (Exception e) {
			throw e;		
		}
		
		return flag;
	}
	
	public boolean deleteProviderProvider( int ProviderDetail1, int ProviderDetail2) throws Exception
	{
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.deleteProviderProvider(connection, ProviderDetail1, ProviderDetail2);
		}
		catch (Exception e) {
			throw e;		
		}
		
		return flag;
	}
	
	public boolean deleteProviderPatient( int ProviderDetail, int PatientDetail) throws Exception
	{
		boolean flag;
		try{
		    Database database= new Database();
		    Connection connection = database.Get_Connection();
			Project project= new Project();
			flag=project.deleteProviderPatient(connection, ProviderDetail, PatientDetail);
		}
		catch (Exception e) {
			throw e;		
		}
		
		return flag;
	}
	
	
}	
