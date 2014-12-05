package com.brianghig.contact.extract;

import com.brianghig.contact.extract.impl.PipelineManagerImpl;

public class PipelineFactory {

	public PipelineManager createPipelineManager() {
		return new PipelineManagerImpl();
	}
	
}
