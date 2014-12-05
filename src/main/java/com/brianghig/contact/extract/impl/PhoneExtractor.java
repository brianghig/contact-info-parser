package com.brianghig.contact.extract.impl;

import java.util.ArrayList;
import java.util.List;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.model.ContactInfo;

public class PhoneExtractor implements Extractor {
	
	public List<String> extract(List<String> lines, ContactInfo contact) {

		List<String> remainingLines = new ArrayList<String>();
		
		/*
		 * Loop backwards through the lines so we can remove
		 * them along the way.
		 */
		lineLoop: for(int i = lines.size() - 1; i >= 0; i--) {
			String originalLine = lines.remove(i);
			
			// Remove all characters except decimals
			// \\^([0-9]+)
			String numberLine = originalLine.replaceAll("[^.0-9]", "");
			if(numberLine.length() == 10) {
				contact.setPhoneNumber(numberLine);
				// Add all of the remaining lines since we found our match
				remainingLines.addAll(lines);
				break lineLoop;
			}
			else {
				remainingLines.add(originalLine);
			}
		}
		
		return remainingLines;
		
	}

}
