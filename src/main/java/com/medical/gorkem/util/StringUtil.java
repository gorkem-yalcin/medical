package com.medical.gorkem.util;

import com.medical.gorkem.exception.MedicalException;

public class StringUtil {

	private StringUtil() throws MedicalException {
		throw new MedicalException("Utility class", "000000");
	}

	public static boolean isNotEmpty(String string) {
		return string != null && !string.trim().isEmpty();
	}

	public static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}


}
