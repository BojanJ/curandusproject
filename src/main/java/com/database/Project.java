package com.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import com.google.gson.Gson;
import com.model.PatientsCascade;
import com.model.Providers;
import com.model.SavedTemplate;
import com.model.TreatmentItem;
import com.model.TreatmentItemListScroll;
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
	public List<SavedTemplate> getSavedTemplate(Connection connection, int ProviderDetail) throws Exception
	{
		List<SavedTemplate> t_items = new ArrayList<SavedTemplate>();
		PreparedStatement ps=null;
			try
			{
				ps = connection.prepareStatement("CALL GetSavedTreatmentTemplateByProviderId(?)");
				ps.setInt(3,ProviderDetail);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					SavedTemplate p_eden = new SavedTemplate(
										rs.getInt(1),   
										rs.getString(2), 
										rs.getInt(3),   
										rs.getDate(4),  
										rs.getInt(5),   
										rs.getDate(6),  
										rs.getInt(7)    
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
			finally {
				ps.close();
				connection.close();
				}
	}	
	
	public List<TreatmentItemListScroll> getTreatmentItemListLoad(Connection connection, int p_activetreatmentid) throws Exception
	{
		List<TreatmentItemListScroll> t_items = new ArrayList<TreatmentItemListScroll>();
		PreparedStatement ps=null;
			try
			{

			    ps = connection.prepareStatement("CALL TreatmentItemListLoad(?)");
				ps.setInt(1, p_activetreatmentid);
				ResultSet rs = ps.executeQuery();
				
				System.out.println(rs);
				while(rs.next())
				{	
					TreatmentItemListScroll p_eden = new TreatmentItemListScroll(
										rs.getInt(1),
										rs.getInt(2),
										rs.getString(3),
										rs.getTimestamp(4),
										rs.getTimestamp(5),
										rs.getTimestamp(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9),
										rs.getDate(10),
										rs.getInt(11),
										rs.getDate(12),
										rs.getInt(13),
										rs.getInt(14),
										rs.getInt(15),
										rs.getInt(16),
										rs.getString(17)
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
			finally {
				ps.close();
				connection.close();
				}
	}	
	
	public List<TreatmentItemListScroll> getTreatmentItemListScroll(Connection connection, int TreatmentItemListID, String T_UP_DOWN, int range) throws Exception
	{
		List<TreatmentItemListScroll> t_items = new ArrayList<TreatmentItemListScroll>();
		PreparedStatement ps=null;
			try
			{
			    ps = connection.prepareStatement("CALL ScrollTreatmentItemList_1(?, ?, ?)");
				ps.setInt(1, TreatmentItemListID);
				ps.setString(2,T_UP_DOWN);
				ps.setInt(3, range);
				ResultSet rs = ps.executeQuery();
				
				System.out.println(rs);
				while(rs.next())
				{	
					TreatmentItemListScroll p_eden = new TreatmentItemListScroll(
										rs.getInt(1),
										rs.getInt(2),
										rs.getString(3),
										rs.getTimestamp(4),
										rs.getTimestamp(5),
										rs.getTimestamp(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9),
										rs.getDate(10),
										rs.getInt(11),
										rs.getDate(12),
										rs.getInt(13),
										rs.getInt(14),
										rs.getInt(15),
										rs.getInt(16),
										rs.getString(17)										
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
			finally {
				ps.close();
				connection.close();
				}
	}
	
	public boolean UpdateSavedTreatment(Connection connection, List<TreatmentItem> t_items, int p_savedtreatmentid) throws Exception
	{	
		PreparedStatement pss=null;
		PreparedStatement ps=null;
		System.out.println("start");
		List<String> listaDelete=new ArrayList<String>();
		String listadeleteobicna="0";
				Gson gson = new Gson();
			try{
				connection.setAutoCommit(false);
				for(TreatmentItem tt:t_items)
				{
					listadeleteobicna=listadeleteobicna+","+NVL(tt.getTreatmentItemId()).toString();
					//listaDelete.add(tt.getTreatmentItemId().toString());
				}
				
				ps =connection.prepareStatement("call DeleteSavedTreatmentItems(?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, listadeleteobicna);
				ps.setInt(2, NVL(p_savedtreatmentid));
				System.out.println(listadeleteobicna);
				ps.executeQuery();
				
				pss =connection.prepareStatement("call InsertUpdateSavedTreatmentItem(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				for(TreatmentItem tt:t_items)
				{
					//lista.add(tt.getTreatmentItemId());
					pss.setInt(1,NVL(tt.getTreatmentItemId()));
					pss.setInt(2, NVL(p_savedtreatmentid));
					pss.setString(3, tt.getName());
					pss.setString(4, tt.getRepeatT());
					pss.setString(5, tt.getTypeT());
					pss.setString(6, tt.getDuration());
					pss.setString(7, gson.toJson(tt.getRenderingInfo()));
					
					pss.addBatch();
				}
				pss.executeBatch();
				//pss.executeQuery();
			
				connection.commit(); 
				return true;
		}
		catch(Exception e)
		{
			connection.rollback();
			e.printStackTrace();
			throw e;
		}

		finally {
				pss.close();
				connection.close();
		}
	}	
	
	public boolean UpdateActiveSubTreatment(Connection connection, List<TreatmentItem> t_items) throws Exception
	{	
		PreparedStatement pss=null;
		System.out.println("start");
				Gson gson = new Gson();
			try{
				connection.setAutoCommit(false);
				pss =connection.prepareStatement("call InsertUpdateTreatmentItem(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				for(TreatmentItem tt:t_items)
				{
					System.out.println("ID "+NVL(tt.getTreatmentItemId()));
					pss.setInt(1,NVL(tt.getTreatmentItemId()));
					pss.setInt(2, NVL(tt.getSubtreatmentid()));
					pss.setString(3, tt.getName());
					pss.setString(4, tt.getRepeatT());
					pss.setString(5, tt.getTypeT());
					pss.setString(6, tt.getDuration());
					pss.setString(7, gson.toJson(tt.getRenderingInfo()));
					
					pss.addBatch();
				}
				pss.executeBatch();
				//pss.executeQuery();
			
				connection.commit(); 
				return true;
		}
		catch(Exception e)
		{
			connection.rollback();
			e.printStackTrace();
			throw e;
		}

		finally {
				pss.close();
				connection.close();
		}
	}	
	
	public int CheckNameSavedTreatment(Connection connection, int ProviderID, String NameTreatment, List<TreatmentItem> t_items) throws Exception
	{	
		PreparedStatement ps=null;
		PreparedStatement pss=null;
		int flag=0;
		int p_savedTreatmentID;	
		try
		{
		    ps =connection.prepareStatement("call CHECK_NAME_SAVE_TREATMENT(?,?)", Statement.RETURN_GENERATED_KEYS);
		    ps.setInt(1,ProviderID);
		    ps.setString(2,NameTreatment);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				if (rs.getInt(1)==0){
					flag=0;
					connection.setAutoCommit(false);
				    ps =connection.prepareStatement("call InsertSavedTreatment(?,?)", Statement.RETURN_GENERATED_KEYS);
				    ps.setInt(1,ProviderID);
				    ps.setString(2,NameTreatment);
				    
				    Gson gson = new Gson();
			
					ResultSet rss = ps.executeQuery();
					
					if (rss.next()){
						p_savedTreatmentID=rss.getInt(1);

						pss =connection.prepareStatement("call InsertUpdateSavedTreatmentItem(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
						for(TreatmentItem tt:t_items)
						{
							pss.setInt(1, 0);
							pss.setInt(2, p_savedTreatmentID);
							pss.setString(3, tt.getName());
							pss.setString(4, tt.getRepeatT());
							pss.setString(5, tt.getTypeT());
							pss.setString(6, tt.getDuration());
							pss.setString(7, gson.toJson(tt.getRenderingInfo()));
							
							pss.addBatch();
						}
						pss.executeBatch();
						//pss.executeQuery();
						connection.commit(); 
					}
				}
				else
				{
					flag=rs.getInt(1);
				}
			}
			return flag;
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
	
	public boolean InsertSavedTreatment(Connection connection, int ProviderID, 
			String NameTreatment, List<TreatmentItem> t_items) throws Exception
	{	
		PreparedStatement ps=null;
		PreparedStatement pss=null;
		int p_savedTreatmentID;	
		try
		{
		    ps =connection.prepareStatement("call InsertSavedTreatment(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		    ps.setInt(1,ProviderID);
		    ps.setString(2,NameTreatment);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				p_savedTreatmentID=rs.getInt(1);
				Gson gson = new Gson();
				connection.setAutoCommit(false);
				pss =connection.prepareStatement("call InsertSavedTreatmentItem(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				for(TreatmentItem tt:t_items)
				{
					pss.setInt(1, p_savedTreatmentID);
					pss.setString(2, tt.getName());
					pss.setString(3, tt.getRepeatT());
					pss.setString(4, tt.getTypeT());
					pss.setString(5, tt.getDuration());
					pss.setString(6, gson.toJson(tt.getRenderingInfo()));
					
					pss.addBatch();
				}
				pss.executeBatch();
				//pss.executeQuery();
				connection.commit(); 
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
				pss.close();
				connection.close();
		}
	}	
	
	public int InsertActiveSubTreatment(Connection connection, int activeTreatmentID , 
			int ProviderID, int PatientID, String NameTreatment, String SubNameTreatment, 
			List<TreatmentItem> t_items) throws Exception
	{	
		PreparedStatement ps=null;
		PreparedStatement pss=null;
		int p_subTreatmentID;
		int ret_active_treatment=0;
	
		try
		{
			connection.setAutoCommit(false);
		    ps =connection.prepareStatement("call InsertActiveSubTreatment(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		    ps.setInt(1,activeTreatmentID);
		    ps.setInt(2,ProviderID);
		    ps.setInt(3,PatientID);
		    ps.setString(4,NameTreatment);
		    ps.setString(5,SubNameTreatment);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				p_subTreatmentID=rs.getInt(1);
				
				ret_active_treatment=rs.getInt(2);
				
				Gson gson = new Gson();

				connection.setAutoCommit(false);
				pss =connection.prepareStatement("call InsertUpdateTreatmentItem(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				for(TreatmentItem tt:t_items)
				{
					pss.setInt(1,0);
					pss.setInt(2, p_subTreatmentID);
					pss.setString(3, tt.getName());
					pss.setString(4, tt.getRepeatT());
					pss.setString(5, tt.getTypeT());
					pss.setString(6, tt.getDuration());
					pss.setString(7, gson.toJson(tt.getRenderingInfo()));
					
					pss.addBatch();
				}
				pss.executeBatch();
				//pss.executeQuery();
			}
			connection.commit(); 
			return ret_active_treatment;
		}
		catch(Exception e)
		{
			connection.rollback();
			e.printStackTrace();
			throw e;
		}

		finally {
				ps.close();
				pss.close();
				connection.close();
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
				System.out.println("Try");
				//String uname = request.getParameter("uname");
				//PreparedStatement ps = connection.prepareStatement("SELECT SavedTreatmentItemId, SavedTreatmentDetail, Name, TypeT, RepeatT, Duration, RenderingInfo, Created, CreatedBy, Modified, ModifiedBy FROM savedtreatmentitem WHERE savedtreatmentdetail=?");
			    ps = connection.prepareStatement("CALL getTreatmentItemsByTreatment(?, ?)");
				ps.setString(1, TT);
				ps.setInt(2,TreatmentID);
				ResultSet rs = ps.executeQuery();
				
				System.out.println(rs);
				//System.out.println(rs.getInt(1));
				
				System.out.println("Finish");
				while(rs.next())
				{
					System.out.println("rs.next");
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
