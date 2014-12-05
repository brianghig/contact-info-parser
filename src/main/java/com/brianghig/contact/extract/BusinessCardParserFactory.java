package com.brianghig.contact.extract;

import com.brianghig.contact.extract.impl.BusinessCardParserImpl;

public class BusinessCardParserFactory {

	public IBusinessCardParser createBusinessCardParser() {
		return new BusinessCardParserImpl();
	}
	
}
