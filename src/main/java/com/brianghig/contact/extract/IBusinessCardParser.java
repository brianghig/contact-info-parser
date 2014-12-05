package com.brianghig.contact.extract;

import com.brianghig.contact.model.IContactInfo;

public interface IBusinessCardParser {
	
	/**
	 * Process the input contact lines into a IContactInfo object
	 * @param contactLines the raw input of non-classified contact information
	 * @return structured contact information extracted from the raw text input
	 */
	IContactInfo getContactInfo(String document);
	
}
