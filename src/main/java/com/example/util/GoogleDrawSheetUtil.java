package com.example.util;

import java.util.Arrays;

import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleDrawSheetUtil {

	public static ValueRange DrawTop() {
		ValueRange drawTop = new ValueRange();
		drawTop.setValues(Arrays.asList(Arrays.asList("Имя", "А", "Б", "В", "Г")));

		return drawTop;
	}

	public static ValueRange AppendValues(int i) {
		ValueRange appendValues = new ValueRange();
		appendValues.setValues(Arrays.asList(Arrays.asList(" +375 ( " + i + i + " ) " + i + i + i, i, i*2, i*3, i*4)));
		return appendValues;
	}
}
