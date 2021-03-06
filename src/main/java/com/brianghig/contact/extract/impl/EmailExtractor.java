package com.brianghig.contact.extract.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.model.ContactInfo;

public class EmailExtractor implements Extractor {

	private Pattern pattern;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public List<String> extract(List<String> lines, ContactInfo contact) {
		
		List<String> remainingLines = new ArrayList<String>();
		
		/*
		 * Loop backwards through the lines so we can remove
		 * them along the way.
		 */
		lineLoop: for(int i = lines.size() - 1; i >= 0; i--) {
			String line = lines.remove(i);
			Matcher matcher = pattern.matcher(line);
			if( matcher.matches() ) {
				contact.setEmailAddress(line);
				// Add all of the remaining lines since we found our match
				remainingLines.addAll(lines);
				break lineLoop;
			}
			else {
				remainingLines.add(line);
			}
		}
		
		return remainingLines;
		
	}

}
