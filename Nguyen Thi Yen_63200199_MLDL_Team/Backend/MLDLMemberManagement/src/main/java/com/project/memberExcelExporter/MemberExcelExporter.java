package com.project.memberExcelExporter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.dto.MemberDto;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class MemberExcelExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<MemberDto> memberDtoList;

	public MemberExcelExporter(List<MemberDto> memberDtoList) {
		this.memberDtoList = memberDtoList;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Member List");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "No.", style);
		createCell(row, 1, "Member No.", style);
		createCell(row, 2, "ID", style);
		createCell(row, 3, "Name", style);
		createCell(row, 4, "Phone", style);
		createCell(row, 5, "Email", style);
		createCell(row, 6, "Sign Up Date", style);


	}

	private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof String) {
			cell.setCellValue((String) valueOfCell);
		} else {
			cell.setCellValue((LocalDateTime) valueOfCell);
		}
		cell.setCellStyle(style);
	}
	
	private void write() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		int count = 1;
		for (MemberDto record : memberDtoList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, count, style);
			createCell(row, columnCount++, record.getMemberNo(), style);
			createCell(row, columnCount++, record.getMemberId(), style);
			createCell(row, columnCount++, record.getMemberName(), style);
			createCell(row, columnCount++, record.getMemberPhone(), style);
			createCell(row, columnCount++, record.getMemberEmail(), style);
			createCell(row, columnCount++, record.getSignUpDate(), style);
			count++;
		}
	}

	public void generateExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
