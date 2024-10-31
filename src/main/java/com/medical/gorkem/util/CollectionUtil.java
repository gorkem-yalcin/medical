package com.medical.gorkem.util;

import com.medical.gorkem.exception.MedicalException;

import java.util.Collection;

public class CollectionUtil {

	private CollectionUtil() throws MedicalException {
		throw new MedicalException("Utility class", "000000");
	}

	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

}
