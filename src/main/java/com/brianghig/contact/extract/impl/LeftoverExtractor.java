package com.brianghig.contact.extract.impl;

import java.util.List;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.model.ContactInfo;

/**
 * In order to not lose any data,
 * put any remaining lines in the
 * 'other' field of the Contact
 * @author brian
 *
 */
public class LeftoverExtractor implements Extractor {

	public List<String> extract(List<String> lines, ContactInfo contact) {
		for(String line : lines) {
			contact.addOther(line);
		}
		return lines;
	}

}
