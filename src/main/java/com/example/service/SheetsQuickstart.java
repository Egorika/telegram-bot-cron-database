package com.example.service;

import com.example.util.GoogleAuthorizeUtil;
import com.example.util.GoogleDrawSheetUtil;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.example.util.GoogleSheetUtil;

public class SheetsQuickstart {
	private static Sheets sheetsService;
	private static String SPREADSHEET_ID = "1YcRXOPAEk1ZeUogUkSdO607qllhmyMjDXe8iwNr1agM";

	public static void main(String... args) throws IOException, GeneralSecurityException {

		sheetsService = GoogleAuthorizeUtil.authorize();

		BatchUpdateSpreadsheetRequest batchUpdateReq = new BatchUpdateSpreadsheetRequest();
		BatchUpdateSpreadsheetResponse batchUpdateResponse;
		
		batchUpdateReq.setRequests(GoogleSheetUtil.CompleteRequestsList());

		batchUpdateResponse = sheetsService.spreadsheets().batchUpdate(SPREADSHEET_ID, batchUpdateReq).execute();

		System.out.println("-- Созданы листы --");
		
		UpdateValuesResponse result = sheetsService.spreadsheets().values()
				.update(SPREADSHEET_ID, "ВЧЕРА!A1", GoogleDrawSheetUtil.DrawTop()).setValueInputOption("RAW").execute();
		for (int i = 0; i < 10; i++) {
			
			AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
					.append(SPREADSHEET_ID, "ВЧЕРА!A1", GoogleDrawSheetUtil.AppendValues(i))
					.setValueInputOption("USER_ENTERED").setInsertDataOption("INSERT_ROWS")
					.setIncludeValuesInResponse(true).execute();
		}

		System.out.println("-- Записаны значения в листы --");
	}
}