package com.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import com.model.PatientsCascade;
import com.model.Providers;
import com.model.TreatmentItem;
import com.mysql.jdbc.Statement;

public class Project {
	
	public Integer NVL(Integer a){
		if (a==null)
		{
			return 0;
		}
		else
		{
			return a;
		}
	}
	
	public Providers InsertProvider(Connection connection, Providers p_provider) throws Exception
	{
		PreparedStatement ps=null;
		Providers result=new Providers();
		try
		{
		    ps =connection.prepareStatement("call InsertProvider(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		    ps.setString(1,p_provider.getFirstName());
		    ps.setString(2,p_provider.getTypeProvider());
		    ps.setString(3,p_provider.getMiddleInitial());
		    ps.setString(4,p_provider.getLastName());
		    ps.setString(5,p_provider.getStreetAdress());
		    ps.setString(6,p_provider.getCity());
		    ps.setString(7,p_provider.getState());
		    ps.setString(8,p_provider.getZip());
		    ps.setString(9,p_provider.getPhone());
		    ps.setString(10,p_provider.getAlternatePhone());
		    ps.setInt(11,NVL(p_provider.getStatus()));
		    ps.setDate(12,(Date) p_provider.getCreated());
		    ps.setInt(13,NVL(p_provider.getCreatedBy()));
		    ps.setDate(14,(Date)p_provider.getModified());
		    ps.setInt(15,NVL(p_provider.getModifiedBy()));		    
		    ps.setString(16,p_provider.getDeviceId());
		    ps.setInt(17,NVL(p_provider.getActivationCode()));
		    
			//ps.executeUpdate();
			
            //prest.executeUpdate(query, PreparedStatement.RETURN_GENERATED_KEYS); Throws an error
            //prest.executeQuery(); Throws an error			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				p_provider.setProviderId(rs.getInt(1));
			}
			return p_provider;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		finally {
				ps.close();
		      connection.close();
		}
	}
	
	public List<PatientsCascade> getPatientsByProvider(Connection connection, int PatientID) throws Exception
	{
		List<PatientsCascade> t_items = new ArrayList<PatientsCascade>();
		PreparedStatement ps=null;
			try
			{
				//String uname = request.getParameter("uname");
				//PreparedStatement ps = connection.prepareStatement("SELECT SavedTreatmentItemId, SavedTreatmentDetail, Name, TypeT, RepeatT, Duration, RenderingInfo, Created, CreatedBy, Modified, ModifiedBy FROM savedtreatmentitem WHERE savedtreatmentdetail=?");
			    ps = connection.prepareStatement("CALL GetPatientsByProvider(?)");
				ps.setInt(1,PatientID);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					//TreatmentItem p_eden = new TreatmentItem();
					
					PatientsCascade p_eden = new PatientsCascade(
										rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6)
										);
					t_items.add(p_eden);
				}
				
				if (t_items.isEmpty()){
					 throw new WebApplicationException(404);
				}
				else
				{
					return t_items;
				}
			}  
				catch(Exception e)
				{
					e.printStackTrace();
					throw e;
				}
			finally{
					ps.close();
			        connection.close();
			}
	}	
	
	public boolean AddContactPatient(Connection connection, Integer providerid, String telephoneNumber, String firstName, String lastName) throws Exception
	{
		PreparedStatement ps=null;
		try
		{
		    ps =connection.prepareStatement("call AddPatientContact(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		    ps.setInt(1,providerid);
		    ps.setString(2,telephoneNumber);
			ps.setString(3,firstName);
			ps.setString(4,lastName);	
			//ps.setString(4,"0");
			ps.executeUpdate();
			
            //prest.executeUpdate(query, PreparedStatement.RETURN_GENERATED_KEYS); Throws an error
            //prest.executeQuery(); Throws an error			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				System.out.println("Result");
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		finally {
				ps.close();
		      connection.close();
		}
	}	
	
	
	public boolean AddContactDoctor(Connection connection, Integer providerid, String telephoneNumber, String firstName, String lastName) throws Exception
	{
		PreparedStatement ps=null;
		try
		{
			System.out.println("Prvo");
			//String uname = request.getParameter("uname");
		    ps =connection.prepareStatement("call AddProviderContact(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		    //ps =connection.prepareStatement("INSERT INTO PROVIDERS(FirstName,LastName,Phone,Status) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			//connection.prepareStatement("SELECT name,countrycode,district FROM city where countrycode=?");
			
		    ps.setInt(1,providerid);
		    ps.setString(2,telephoneNumber);
			ps.setString(3,firstName);
			ps.setString(4,lastName);	
			//ps.setString(4,"0");
			ps.executeUpdate();
			
            //prest.executeUpdate(query, PreparedStatement.RETURN_GENERATED_KEYS); Throws an error
            //prest.executeQuery(); Throws an error			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				System.out.println("Result");
			}
			return true;
		}
		catch(Exception e)
		{
			throw e;
		}

		finally {
				ps.close();
		      connection.close();
		}
	}	
	

	
	public List<TreatmentItem> getTreatmentItemsByTreatment(Connection connection, int TreatmentID, String TT) throws Exception
	{
		List<TreatmentItem> t_items = new ArrayList<TreatmentItem>();
		PreparedStatement ps=null;
			try
			{
				//String uname = request.getParameter("uname");
				//PreparedStatement ps = connection.prepareStatement("SELECT SavedTreatmentItemId, SavedTreatmentDetail, Name, TypeT, RepeatT, Duration, RenderingInfo, Created, CreatedBy, Modified, ModifiedBy FROM savedtreatmentitem WHERE savedtreatmentdetail=?");
			    ps = connection.prepareStatement("CALL getTreatmentItemsByTreatment(?, ?)");
				ps.setString(1, TT);
				ps.setInt(2,TreatmentID);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					//TreatmentItem p_eden = new TreatmentItem();
					
					TreatmentItem p_eden = new TreatmentItem(
										rs.getInt(1),
										rs.getInt(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getString(7),
										rs.getDate(8),
										rs.getInt(9),
										rs.getDate(10),
										rs.getInt(11)
										);
//					System.out.println("Modified "+p_eden.getModified());
//					p_eden.setTreatmentItemId(rs.getInt(1));
//					p_eden.setTreatmentID(rs.getInt(2));
//					p_eden.setName(rs.getString(3));
//					p_eden.setTypeT(rs.getString(4));
//					p_eden.setRepeatT(rs.getString(5));
//					p_eden.setDuration(rs.getString(6));
//					p_eden.setRenderingInfo(rs.getString(7));
//					p_eden.setCreated(rs.getDate(8));
//					p_eden.setCreatedBy(rs.getInt(9));
//					System.out.println("Modified "+rs.getDate(10));
//					p_eden.setModified(rs.getDate(10));
//					p_eden.setModifiedBy(rs.getInt(11));

					t_items.add(p_eden);
				}
				
				if (t_items.isEmpty()){
					 throw new WebApplicationException(404);
				}
				else
				{
					return t_items;
				}
			}
				catch(Exception e)
				{
					e.printStackTrace();
					throw e;
				}
			finally {
				ps.close();
				connection.close();
				}
	}
}
