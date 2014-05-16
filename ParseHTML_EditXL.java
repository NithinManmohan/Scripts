package com.report;

/*
 * 
 * Author: Nithin Manmohan
 * Objective: Parses PMD Html report and fills in the excel sheet
 * Sample Input: C:\work\29thApril.xlsx C:\work\pmd_report.html 3 5 6 7 C:\work\29thApril_pmd.xlsx
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JExcel {

	public static int ParseHTML_P1(File input) throws IOException {
		Document doc;
		doc = Jsoup.parse(input, "UTF-8", "");
		Element link = doc.select("div.p1").first();
		return Integer.parseInt(link.text());
	}

	public static int ParseHTML_P2(File input) throws IOException {
		Document doc;
		doc = Jsoup.parse(input, "UTF-8", "");
		Element link = doc.select("div.p2").first();
		return Integer.parseInt(link.text());
	}

	public static int ParseHTML_P3(File input) throws IOException {
		Document doc;
		doc = Jsoup.parse(input, "UTF-8", "");
		Element link = doc.select("div.p3").first();
		return Integer.parseInt(link.text());
	}

	public static void main(String[] args) throws Exception {

		// Input Excel, Input HTML, Row, Column, Column, Column, Output Excel

		// Read input excel
		FileInputStream input_xcel = new FileInputStream(args[0]);
		XSSFWorkbook wb = new XSSFWorkbook(input_xcel);
		wb.setForceFormulaRecalculation(true);
		Sheet sheet = wb.getSheetAt(0);

		// Read input html
		File input_html = new File(args[1]);

		// get P1 count
		Row row = sheet.getRow(Integer.parseInt(args[2]));
		Cell cell = row.getCell(Integer.parseInt(args[3]));
		cell.setCellValue(ParseHTML_P1(input_html));

		// get P2 count
		Cell cell1 = row.getCell(Integer.parseInt(args[4]));
		cell1.setCellValue(ParseHTML_P2(input_html));

		// get P3 count
		Cell cell2 = row.getCell(Integer.parseInt(args[5]));
		cell2.setCellValue(ParseHTML_P3(input_html));

		// Modify the value in the cell
		FileOutputStream fileOut = new FileOutputStream(args[6]);
		wb.write(fileOut);
		fileOut.close();
		System.out.println("Generated");
	}

}
