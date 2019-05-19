package com.seuit.spring.watchshop.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.seuit.spring.watchshop.entity.Image;
import com.seuit.spring.watchshop.entity.ProductApi;
import com.seuit.spring.watchshop.service.ProductService;

public class ProductExcelHelper {

	public List<ProductApi> saveProductsFromExcelFile(File excelFilePath) throws IOException {
		List<ProductApi> listProductApi = new ArrayList<ProductApi>();
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		Workbook workBook = getWorkbook(inputStream, excelFilePath.getPath());
		Sheet firstSheet = workBook.getSheetAt(0);
		Iterator<Row> rows = firstSheet.iterator();

		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			ProductApi productApi = new ProductApi();
			if (row.getRowNum() < 2) {
				continue;
			} else {
				while (cells.hasNext()) {
					Cell cell = cells.next();
					int columnIndex = cell.getColumnIndex();
					Image image = new Image();
					switch (columnIndex) {
					case 0:
						productApi.getProduct().setCodeName((String) getCellValue(cell));
						break;
					case 1:
						productApi.getProduct().setImage((String) getCellValue(cell));
						break;
					case 2:
						productApi.getProduct().setPrice((Double) getCellValue(cell));
						break;
					case 3:
						productApi.getProduct().setAvailable(((Double) getCellValue(cell)).intValue());
						break;
					case 4:
						productApi.getProductDetail().setSize(((Double) getCellValue(cell)).intValue());
						break;
					case 5:
						productApi.getProductDetail().setCaseMaterial((String) getCellValue(cell));
						break;
					case 6:
						productApi.getProductDetail().setChainMaterial((String) getCellValue(cell));
						break;
					case 7:
						productApi.getProductDetail().setGlassMaterial((String) getCellValue(cell));
						break;
					case 8:
						productApi.getProductDetail().setWaterResistance(((Double) getCellValue(cell)).intValue());
						break;
					case 9:
						productApi.getProductDetail().setOtherFunction((String) getCellValue(cell));
						break;
					case 10:
						productApi.getProductDetail().setInsurance(((Double) getCellValue(cell)).intValue());
						break;
					case 11:
						productApi.setIdFirm(((Double) getCellValue(cell)).intValue());
						break;
					case 12:
						productApi.setIdModel(((Double) getCellValue(cell)).intValue());
						break;
					case 13:
						productApi.setIdOrigin(((Double) getCellValue(cell)).intValue());
						break;
					case 14:
					case 15:
					case 16:
					case 17:
					case 18:
						if(!cell.getStringCellValue().equals("")) {
							productApi.getImages().add(new Image((String) getCellValue(cell)));
						}
						break;
					}
				}
				listProductApi.add(productApi);
			}
		}

		workBook.close();
		inputStream.close();

		return listProductApi;
	}

	@SuppressWarnings("deprecation")
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
}
