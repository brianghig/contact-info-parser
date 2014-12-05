package com.brianghig.contact.extract.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.extract.impl.phone.PhoneFormatter;
import com.brianghig.contact.model.ContactInfo;

public class FaxExtractor implements Extractor {

	public List<String> extract(List<String> lines, ContactInfo contact) {

		List<String> remainingLines = new ArrayList<String>();
		
		/*
		 * Loop backwards through the lines so we can remove
		 * them along the way.
		 */
		lineLoop: for(int i = lines.size() - 1; i >= 0; i--) {
			String originalLine = lines.remove(i);
			
			if( StringUtils.startsWithAny(originalLine.toLowerCase(), "f") ) {
				// Remove all characters except decimals
				// \\^([0-9]+)
				String numberLine = originalLine.replaceAll("[^.0-9]", "");
				if(numberLine.length() >= 10) {
					numberLine = PhoneFormatter.formatPhoneNumber(numberLine);
					contact.setFaxNumber(numberLine);
					// Add all of the remaining lines since we found our match
					remainingLines.addAll(lines);
					break lineLoop;
				}
				else {
					remainingLines.add(originalLine);
				}
			}
			else {
				remainingLines.add(originalLine);
			}
			
		}
		
		return remainingLines;
		
	}

}