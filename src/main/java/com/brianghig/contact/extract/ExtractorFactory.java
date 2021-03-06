package com.brianghig.contact.extract;

import com.brianghig.contact.extract.impl.EmailExtractor;
import com.brianghig.contact.extract.impl.FaxExtractor;
import com.brianghig.contact.extract.impl.LeftoverExtractor;
import com.brianghig.contact.extract.impl.NameExtractor;
import com.brianghig.contact.extract.impl.OrganizationExtractor;
import com.brianghig.contact.extract.impl.PhoneExtractor;

public class ExtractorFactory {

	public Extractor createEmailExtractor() {
		return new EmailExtractor();
	}
	
	public Extractor createNameExtractor() {
		return new NameExtractor();
	}
	
	public Extractor createOrganizationExtractor() {
		return new OrganizationExtractor();
	}
	
	public Extractor createPhoneExtractor() {
		return new PhoneExtractor();
	}
	
	public Extractor createFaxExtractor() {
		return new FaxExtractor();
	}
	
	public Extractor createLeftoverExtractor() {
		return new LeftoverExtractor();
	}
	
}
