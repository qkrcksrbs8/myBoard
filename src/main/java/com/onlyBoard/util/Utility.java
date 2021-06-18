package com.onlyBoard.util;

public final class Utility {
	
	/**
	 * Null to ""
	 * @param arg0
	 * @return
	 */
	public static String convertNull(String arg0){		
		String result = "";
		if (arg0 != null) {
			result = arg0;
		}
		return result;
	}
	
	public static String convertNull(String arg0, String arg1){		
		String result = arg1;
		if (arg0 != null) {
			result = arg0;
		}
		return result;
	}

	
}
