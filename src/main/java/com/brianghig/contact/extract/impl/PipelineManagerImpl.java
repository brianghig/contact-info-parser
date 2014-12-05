package com.brianghig.contact.extract.impl;

import java.util.ArrayList;
import java.util.List;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.extract.ExtractorFactory;
import com.brianghig.contact.extract.PipelineManager;
import com.brianghig.contact.model.ContactInfo;

public class PipelineManagerImpl implements PipelineManager {

	private ExtractorFactory factory;
	
	public void setFactory(ExtractorFactory factory) {
		this.factory = factory;
	}
	
	public PipelineManagerImpl() {
		this.factory = new ExtractorFactory();
	}

	/**
	 * Creates a pipeline of the extractors in a particular
	 * order. New pipelines could be identified and added as
	 * other methods if better context clues could be used.
	 * @return
	 */
	protected List<Extractor> createPipeline() {
		
		// Add the extractors into an ordered list
		List<Extractor> pipeline = new ArrayList<Extractor>();
		
		/*
		 * First, Email since it follows the most particular pattern
		 */
		pipeline.add(this.factory.createEmailExtractor());
		/*
		 * Second, Phone because it's also easily identifiable
		 */
		pipeline.add(this.factory.createPhoneExtractor());
		/*
		 * Then Organization because we can use org/company suffix
		 * clues, or possibly the host URL from the email address
		 */
		pipeline.add(this.factory.createOrganizationExtractor());
		/*
		 * Finally, Name because it will be more easily extracted
		 * from a smaller data set
		 */
		pipeline.add(this.factory.createNameExtractor());
		
		return pipeline;
	}
	
	/**
	 * Process the input contact lines into a Contact Info object
	 * @param contactLines the raw input of non-classified contact information
	 * @return structured contact information extracted from the raw text input
	 */
	public ContactInfo process(List<String> contactLines) {
		
		// The object that will be modified during the pipeline
		ContactInfo contact = new ContactInfo();
		
		// Get the ordered extractors
		List<Extractor> pipeline = this.createPipeline();
		
		/*
		 * For now, we'll go sequentially, but here's where we'd
		 * handle parallelism if possible.
		 */
		for(Extractor extractor : pipeline) {
			/*
			 * Extract the input lines, then replace the variable
			 * with only what's left over
			 */
			contactLines = extractor.extract(contactLines, contact);
		}
		
		return contact;
		
	}
}
