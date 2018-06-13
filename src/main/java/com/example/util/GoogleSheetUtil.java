package com.example.util;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.DeleteSheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateSpreadsheetPropertiesRequest;

public class GoogleSheetUtil {
	
	private static String TODAY = "СЕГОДНЯ";
	private static String YESTERDAY = "ВЧЕРА";
	private static String MONTH_COMPARE = "СРАВНЕНИЕ МЕСЯЦЕВ";
	private static String MONTH = "МЕСЯЦ";
	
	private static int TODAY_ID = 0;
	private static int YESTERDAY_ID = 1;
	private static int MONTH_COMPARE_ID = 2;
	private static int MONTH_ID = 3;
	
	
	/*
	 * Создание списка запросов на пересоздание всех листов.
	 * Поочередное удаление и создание листов, так как Google не позволяет удалить все видимые листы сразу.
	 */
	public static List<Request> CompleteRequestsList() {
		List<Request> requests = new ArrayList<>();
		
		requests.add(SetSpreadsheetTitleRequest());
		
		requests.add(DeleteTodaySheet());
		requests.add(CreateTodaySheet());
		
		requests.add(DeleteYesterdaySheet());
		requests.add(CreateYesterdaySheet());
		
		requests.add(DeleteMonthSheet());
		requests.add(CreateMonthSheet());
		
		requests.add(DeleteMonthCompareSheet());
		requests.add(CreateMonthCompareSheet());

		System.out.println("-- Создан список запросов для пересоздания листов --");

		return requests;
	}
	
	/*
	 * Название всей таблицы.
	 */
	
	public static Request SetSpreadsheetTitleRequest() {
		Request setTitleRequest = new Request();
		
		SpreadsheetProperties spreadsheetProp = new SpreadsheetProperties();
		spreadsheetProp.setTitle("Лекарства");
		
		UpdateSpreadsheetPropertiesRequest updateSpreadSheetRequest = new UpdateSpreadsheetPropertiesRequest();
		updateSpreadSheetRequest.setProperties(spreadsheetProp).setFields("*");

		setTitleRequest.setUpdateSpreadsheetProperties(updateSpreadSheetRequest);
		
		return setTitleRequest;
	}
	
	/*
	 * Запросы создания листов.
	 * Начало региона.
	 */
	
	public static Request CreateTodaySheet() {
		Request сreateTodaySheet = new Request();
		SheetProperties todaySheetProperties = new SheetProperties();
		todaySheetProperties.setTitle(TODAY);
		todaySheetProperties.setSheetId(TODAY_ID);

		сreateTodaySheet.setAddSheet(new AddSheetRequest().setProperties(todaySheetProperties));

		return сreateTodaySheet;
	}

	public static Request CreateYesterdaySheet() {
		Request сreateYesterdaySheet = new Request();
		SheetProperties yesterdaySheetProperties = new SheetProperties();
		yesterdaySheetProperties.setTitle(YESTERDAY);
		yesterdaySheetProperties.setSheetId(YESTERDAY_ID);

		сreateYesterdaySheet.setAddSheet(new AddSheetRequest().setProperties(yesterdaySheetProperties));

		return сreateYesterdaySheet;
	}

	public static Request CreateMonthCompareSheet() {
		Request сreateMonthCompareSheet = new Request();
		SheetProperties monthCompareSheetProperties = new SheetProperties();
		monthCompareSheetProperties.setTitle(MONTH_COMPARE);
		monthCompareSheetProperties.setSheetId(MONTH_COMPARE_ID);

		сreateMonthCompareSheet.setAddSheet(new AddSheetRequest().setProperties(monthCompareSheetProperties));

		return сreateMonthCompareSheet;
	}

	public static Request CreateMonthSheet() {
		Request сreateMonthSheet = new Request();
		SheetProperties monthSheetProperties = new SheetProperties();
		monthSheetProperties.setTitle(MONTH);
		monthSheetProperties.setSheetId(MONTH_ID);

		сreateMonthSheet.setAddSheet(new AddSheetRequest().setProperties(monthSheetProperties));

		return сreateMonthSheet;
	}
	
	/*
	 * Запросы создания листов.
	 * Конец региона.
	 */

	/*
	 * Запросы удаления листов.
	 * Начало региона.
	 */
	
	public static Request DeleteTodaySheet() {
		Request deleteTodaySheet = new Request();
		DeleteSheetRequest todayRequest = new DeleteSheetRequest();
		todayRequest.setSheetId(TODAY_ID);
		deleteTodaySheet.setDeleteSheet(todayRequest);
		
		return deleteTodaySheet;
	}

	public static Request DeleteYesterdaySheet() {
		Request deleteYesterdaySheet = new Request();
		DeleteSheetRequest yesterdayRequest = new DeleteSheetRequest();
		yesterdayRequest.setSheetId(YESTERDAY_ID);
		deleteYesterdaySheet.setDeleteSheet(yesterdayRequest);
		
		return deleteYesterdaySheet;
	}

	public static Request DeleteMonthCompareSheet() {
		Request deleteMonthCompareSheet = new Request();
		DeleteSheetRequest monthCompareRequest = new DeleteSheetRequest();
		monthCompareRequest.setSheetId(MONTH_COMPARE_ID);
		deleteMonthCompareSheet.setDeleteSheet(monthCompareRequest);
		
		return deleteMonthCompareSheet;
	}

	public static Request DeleteMonthSheet() {
		Request deleteMonthSheet = new Request();
		DeleteSheetRequest monthRequest = new DeleteSheetRequest();
		monthRequest.setSheetId(MONTH_ID);
		deleteMonthSheet.setDeleteSheet(monthRequest);
		
		return deleteMonthSheet;
	}
	
	/*
	 * Запросы удаления листов.
	 * Конец региона.
	 */
}
