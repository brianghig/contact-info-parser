package com.brianghig.contact.extract.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brianghig.contact.extract.Extractor;
import com.brianghig.contact.model.ContactInfo;

public class PhoneExtractor implements Extractor {

	/**
	 * @see http://stackoverflow.com/a/10912910 for original expression:
	 * ^(?=.{7,32}$)(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*((\\s?x\\s?|ext\\s?|extension\\s?)\\d{1,5}){0,1}$
	 * 
	 * Modified to allow for prefixes such as "P:" and "Ph: "
	 */
	private static final String PHONE_PATTERN = ".*(?=.{7,32}$)(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*((\\s?x\\s?|ext\\s?|extension\\s?)\\d{1,5}){0,1}";
	private final Pattern pattern;
	
	{
		this.pattern = Pattern.compile(PHONE_PATTERN);
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
				contact.setPhone(line);
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
