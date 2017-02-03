package com.webService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.client.ProjectManager;
import com.google.gson.Gson;
import com.model.Patients;
import com.model.PatientsCascade;
import com.model.ProviderProvider;
import com.model.Providers;
import com.model.SavedTemplate;
import com.model.TreatmentItem;
import com.model.TreatmentItemList;
import com.model.TreatmentItemListScroll;


@Path("/api")
public class JSONService {

//	@GET
//	@Path("/get")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Track getTrackInJSON() {
//
//		Track track = new Track();
//		track.setTitle("Enter Sandman");
//		track.setSinger("Metallica");
//
//		return track;
//	}
//
//	@POST
//	@Path("/post")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createTrackInJSON(Track track) {
//
//		String result = "Track saved : " + track;
//		return Response.status(201).entity(result).build();
//	}
	
	@GET
	@Path("/getSavedTreatmentTemplateByProvider/ProviderDetail={ProviderDetail}")
	@Produces("application/json")
   public String getSavedTemplate(@PathParam("ProviderDetail")int ProviderDetail)
	{
		List<SavedTemplate> t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getSavedTemplate(ProviderDetail);
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);

		} catch (Exception e)
		{
			System.out.println("error");
		}
		return t_tems_str;
	}	
	
	@GET
	@Path("/treatmentitemlis/activetreatmentid={activetreatmentid}")
	@Produces("application/json")
   public List<TreatmentItemListScroll> getTreatmentItemListLoad(@PathParam("activetreatmentid")int activetreatmentid) throws Exception 
	{
		//System.out.println("1s");
		List<TreatmentItemListScroll> t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getTreatmentItemListLoad(activetreatmentid);
			//StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);
			
			return t_items;

		} catch (Exception e)
		{
			System.out.println("error");
			e.printStackTrace();
			throw e;
		}
		
	}	
	
	@GET
	@Path("/treatmentitemlistscroll/treatmentitemlistid={treatmentitemlistid}&updown={updown}&range={range}")
	@Produces("application/json")
   public List<TreatmentItemListScroll> getTreatmentItemListScroll(@PathParam("treatmentitemlistid")int treatmentitemlistid, 
		   @PathParam("updown")String updown, @PathParam("range")int range) throws Exception
	{
		//System.out.println("1s");
		List<TreatmentItemListScroll> t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getTreatmentItemListScroll(treatmentitemlistid, updown, range);
			//StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);
			
			return t_items;

		} catch (Exception e)
		{
			System.out.println("error");
			e.printStackTrace();
			throw e;
		}
		
	}	
	
	@POST
	@Path("/updatesavedtreatment/savedtreatmentid={savedtreatmentid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String UpdateSavedTreatment(@PathParam("savedtreatmentid")int savedtreatmentid,List<TreatmentItem> t_items) {
		
		ProjectManager projectManager= new ProjectManager();
		boolean flag=false;
		String t_tems_str = null;
		try {
			flag = projectManager.UpdateSavedTreatment(t_items, savedtreatmentid);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}	
	
	@POST
	@Path("/UpdateActiveSubTreatment")
	@Consumes(MediaType.APPLICATION_JSON)
	public String InsertActiveSubTreatment(List<TreatmentItem> t_items) {
		
		ProjectManager projectManager= new ProjectManager();
		boolean flag=false;
		String t_tems_str = null;
		try {
			flag = projectManager.UpdateActiveSubTreatment(t_items);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}	
	
	@POST
	@Path("/insertsavedtreatment/providerid={providerid}&"
			+ "nametreatment={nametreatment}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String CheckNameSavedTreatment(@PathParam("providerid") Integer providerid, @PathParam("nametreatment") String nametreatment,
			List<TreatmentItem> t_items) {	
		ProjectManager projectManager= new ProjectManager();
		int flag;
		String t_tems_str = null;
		try {
			flag = projectManager.CheckNameSavedTreatment( providerid, nametreatment, t_items);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}		
	
	@POST
	@Path("/InsertSavedTreatment/&providerid={providerid}&"
			+ "nametreatment={nametreatment}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String InsertSavedTreatment(@PathParam("providerid") Integer providerid, @PathParam("nametreatment") String nametreatment,
			List<TreatmentItem> t_items) {	
		ProjectManager projectManager= new ProjectManager();
		boolean flag=false;
		String t_tems_str = null;
		try {
			flag = projectManager.InsertSavedTreatment( providerid, nametreatment, t_items);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}		
	
	@POST
	@Path("/InsertActiveSubTreatment/activetreatmentid={activetreatmentid}&providerid={providerid}&patientid={patientid}&"
			+ "nametreatment={nametreatment}&namesubtreatment={namesubtreatment}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String InsertActiveSubTreatment(@PathParam("activetreatmentid") Integer activetreatmentid,
			@PathParam("providerid") Integer providerid, @PathParam("patientid") Integer patientid, 
			@PathParam("nametreatment") String nametreatment, @PathParam("namesubtreatment") String namesubtreatment,
			List<TreatmentItem> t_items) {
		
		ProjectManager projectManager= new ProjectManager();
		int flag=0;
		String t_tems_str = null;
		try {
			flag = projectManager.InsertActiveSubTreatment(activetreatmentid, providerid, patientid, 
					nametreatment, namesubtreatment,t_items);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}	
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Providers createPro(Providers track) {

		Providers p_prov=null;
		ProjectManager projectManager= new ProjectManager();
		try {
			p_prov = projectManager.InsertProvider(track);
			return p_prov;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p_prov;
	}
	
	 @POST
	 @Path("/insertprovider")
	 @Consumes({MediaType.APPLICATION_JSON })
	 @Produces("application/json")
	 public String insertProvider(Providers p_provider) {
		 System.out.println("init");
		 Providers p_prov=new Providers();
		String t_tems_str = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			System.out.println("init");
			p_prov = projectManager.InsertProvider(p_provider);
			System.out.println("post init");
			//System.out.println("flag: "+flag);
			Gson gson = new Gson();
			System.out.println(gson.toJson(p_prov));
			t_tems_str = gson.toJson(p_prov);

		} catch (Exception e)
		{
			System.out.println("error");
			e.printStackTrace();
		}
		return t_tems_str;
	}	
	
	
   @GET
   @Path("/patients/providerId={providerId}")
   @Produces("application/json")
   public String getPatientsAllCascade(@PathParam("providerId") Integer providerId) throws Exception 
	{
		 System.out.println("init");
		List<PatientsCascade> t_items = null;
		String t_tems_str  = null;
		
		try{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getPatientsByProvider(providerId);
			//StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);

//			if (t_items.isEmpty()){
//				 throw new WebApplicationException(404);
//			}
//			else
//			{
				return t_tems_str;
			//}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}		
	}
	
	@POST
	@Path("/addcontactpatient/providerId={providerID}&phone={phone}&firstName={firstName}&lastName={lastName}")	
	@Produces("application/json")
   public String AddContactPatient(@PathParam("providerID")int providerID, @PathParam("phone")String phone,
			@PathParam("firstName")String firstName, @PathParam("lastName")String lastName)
	{
		boolean flag=false;
		String t_tems_str = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			flag = projectManager.AddContactPatient(providerID, phone, firstName, lastName);
			//System.out.println("flag: "+flag);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);

		} catch (Exception e)
		{
			System.out.println("error");
			e.printStackTrace();
		}
		return t_tems_str;
	}		
	
	@POST
	@Path("/addcontactdoctor/providerId={providerID}&phone={phone}&firstName={firstName}&lastName={lastName}")	
	@Produces("application/json")
   public String AddContactDoctor(@PathParam("providerID")int providerID, @PathParam("phone")String phone,
			@PathParam("firstName")String firstName, @PathParam("lastName")String lastName)
	{
		boolean flag=false;
		List<Providers> t_items = null;
		String t_tems_str = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			flag = projectManager.AddContactDoctor(providerID, phone, firstName, lastName);
			//t_items = projectManager.AddContactDoctor(providerID, phone, firstName, lastName);
			//StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);

		} 
		
		catch (Exception e)
		{
			System.out.println("error");
		}
		return t_tems_str;
	}	

	@GET
	@Path("/gettreatmentitemssbytreatment/treatmentId={treatmentID}&typetreatment={tt}")
	@Produces("application/json")
   public List<TreatmentItem> getTreatmentItemsByTreatment(@PathParam("treatmentID")int treatmentID, @PathParam("tt")String tt)
	{
		//System.out.println("1s");
		List<TreatmentItem> t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getTreatmentItemsByTreatment(treatmentID, tt);
			//StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);
			return t_items;
		} 
		
		catch (Exception e)
		{
			System.out.println("error");
			return null;
		}
		
	}		
	
	
	@GET
	@Path("/getPatientsData/patientId={patientId}")
	@Produces("application/json")
   public Patients getPatientData(@PathParam("patientId")int patientId) throws Exception 
	{
		//System.out.println("1s");
		Patients t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getPatientData(patientId);
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);
			return t_items;
		} 
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}		
		
	}
	
	
	@POST
	@Path("/updatetreatmenitemlist/TreatmentItemListId={TreatmentItemListId}")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatetreatmenitemlist(@PathParam("TreatmentItemListId")int TreatmentItemListId,TreatmentItemList t_items) {
		
		ProjectManager projectManager= new ProjectManager();
		TreatmentItemList flag;
		String t_tems_str = null;
		try {
			flag = projectManager.updatetreatmenitemlist(t_items, TreatmentItemListId);
			Gson gson = new Gson();
			System.out.println(gson.toJson(flag));
			t_tems_str = gson.toJson(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t_tems_str;
	}
	

	@GET
	@Path("/getprovidersdatabyprovider/ProviderProviderId={ProviderProviderId}")
	@Produces("application/json")
   public String getprovidersdatabyprovider(@PathParam("ProviderProviderId")int ProviderProviderId)
	{
		List<ProviderProvider> t_items = null;
		String t_tems_str  = null;
		try 
		{
			ProjectManager projectManager= new ProjectManager();
			t_items = projectManager.getprovidersdatabyprovider(ProviderProviderId);
			Gson gson = new Gson();
			System.out.println(gson.toJson(t_items));
			t_tems_str = gson.toJson(t_items);

		} catch (Exception e)
		{
			System.out.println("error");
		}
		return t_tems_str;
	}	
	
	
}