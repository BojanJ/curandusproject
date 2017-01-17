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
import com.model.PatientsCascade;
import com.model.Providers;
import com.model.TreatmentItem;


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

		} catch (Exception e)
		{
			System.out.println("error");
		}
		return t_tems_str;
	}	

	@GET
	@Path("/gettreatmentitemssbytreatment/treatmentId={treatmentID}&typetreatment={tt}")
	@Produces("application/json")
   public String getTreatmentItemsByTreatment(@PathParam("treatmentID")int treatmentID, @PathParam("tt")String tt)
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

		} catch (Exception e)
		{
			System.out.println("error");
		}
		return t_tems_str;
	}		
}