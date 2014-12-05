package com.brianghig.contact.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;

import com.brianghig.contact.extract.PipelineFactory;
import com.brianghig.contact.extract.PipelineManager;
import com.brianghig.contact.model.ContactInfo;

@Path("contact")
public class ContactInfoEndpoint {

	private PipelineFactory pipelineFactory = new PipelineFactory();
	private PipelineManager pipelineManager;
	
	public ContactInfoEndpoint() {
		// Initialize the pipeline manager from the default pipeline factory
		this.pipelineManager = this.pipelineFactory.createPipelineManager();
	}
	
	public ContactInfoEndpoint(PipelineFactory pipelineFactory) {
		this.pipelineFactory = pipelineFactory;
		
		// Initialize the pipeline manager from the input pipeline factory
		this.pipelineManager = this.pipelineFactory.createPipelineManager();
	}
	
	/**
	 * Allow for overrides of the Pipeline Factory
	 * @param factory
	 */
	public void setPipelineFactory(PipelineFactory factory) {
		this.pipelineFactory = factory;
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
	public ContactInfo parse(
			@FormParam("input") String input) {
		
		// Pre-process and clean the input
		List<String> cleanedLines = cleanInputLines(input);
		
		ContactInfo info = pipelineManager.process(cleanedLines);
		
		return info;
		
	}

	/**
	 * Extract cleaned lines of text from the input
	 * @param input
	 * @return
	 */
	protected List<String> cleanInputLines(String input) {
		
		String[] inputLines = StringUtils.split(input, StringUtils.LF);
		List<String> cleanedLines = new ArrayList<String>();
		for(String rawLine : inputLines) {
			String cleanedLine = StringUtils.trimToNull(rawLine);
			if(cleanedLine != null) {
				cleanedLines.add(cleanedLine);
			}
		}
		return cleanedLines;
	}
	
}
