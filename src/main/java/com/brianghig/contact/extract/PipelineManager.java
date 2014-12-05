package com.brianghig.contact.extract;

import java.util.List;

import com.brianghig.contact.model.ContactInfo;

public interface PipelineManager {
	
	/**
	 * Process the input contact lines into a Contact Info object
	 * @param contactLines the raw input of non-classified contact information
	 * @return structured contact information extracted from the raw text input
	 */
	ContactInfo process(List<String> contactLines);
	
}
