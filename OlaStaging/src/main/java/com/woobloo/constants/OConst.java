package com.woobloo.constants;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

public class OConst {
	// public static final List<String> Actions = new ArrayList<String>();
	public static final HashMap<String, String> responseHeaders = new HashMap<String, String>();

	static {
		/*
		 * Actions.add("showcabs"); Actions.add("book"); Actions.add("track");
		 * Actions.add("cancelride"); Actions.add("cacellationreasons");
		 * Actions.add("cancelreasons");
		 */
		responseHeaders.put(OlaConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		responseHeaders.put(OlaConstants.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
	}

}
