package com.medical.gorkem.util;

import com.medical.gorkem.exception.MedicalException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateUtil {

	private static final String DATE_FORMAT_WITH_TIME = "HH:mm:ss dd/MM/yyyy";
	public static final String DATE_FORMAT = "dd-MM-yyyy";

	private DateUtil() throws MedicalException {
		throw new MedicalException("Utility class", "000000");
	}

	public static LocalDate getLocalDateFromString(String dateFormat, String dateString) {
		LocalDate resultDate = null;

		if (dateFormat == null) {
			dateFormat = DATE_FORMAT;
		}

		if (StringUtil.isNotEmpty(dateString)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			formatter = formatter.withLocale(Locale.of("NL"));
			try {
				resultDate = LocalDate.parse(dateString, formatter);
			} catch (DateTimeParseException e) {
				resultDate = null;
			}
		}

		return resultDate;
	}

	public static String getNowDateWithTimeString() {
		return getNow().format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME));
	}

	public static LocalDateTime getNow() {
		return LocalDateTime.now();
	}
}
