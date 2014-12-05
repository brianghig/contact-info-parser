package com.brianghig.contact.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.brianghig.contact.extract.BusinessCardParserFactory;
import com.brianghig.contact.extract.IBusinessCardParser;
import com.brianghig.contact.model.IContactInfo;

@Path("contact")
public class ContactInfoEndpoint {

	private BusinessCardParserFactory businessCardParserFactory = new BusinessCardParserFactory();
	private IBusinessCardParser businessCardParser;
	
	public ContactInfoEndpoint() {
		// Initialize the pipeline manager from the default pipeline factory
		this.businessCardParser = this.businessCardParserFactory.createBusinessCardParser();
	}
	
	public ContactInfoEndpoint(BusinessCardParserFactory pipelineFactory) {
		this.businessCardParserFactory = pipelineFactory;
		
		// Initialize the pipeline manager from the input pipeline factory
		this.businessCardParser = this.businessCardParserFactory.createBusinessCardParser();
	}
	
	/**
	 * Allow for overrides of the Pipeline Factory
	 * @param factory
	 */
	public void setPipelineFactory(BusinessCardParserFactory factory) {
		this.businessCardParserFactory = factory;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> info() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("success", Boolean.TRUE);
		data.put("message", "The application deployed successfully!");
		return data;
	}
	
	/**
	 * Accepts raw user input, cleans it, and sends it through
	 * the extraction pipeline. Then returns the structured Contact
	 * Information through whichever means of view the client requested
	 * (and this Jersey app supports).
	 * 
	 * @param input
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public IContactInfo parse(
			@FormParam("input") String input) {
		
		IContactInfo info = businessCardParser.getContactInfo(input);
		
		return info;
		
	}
	
}
