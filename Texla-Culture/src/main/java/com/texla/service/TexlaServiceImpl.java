package com.texla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.texla.entity.EmployeeExpenses;
import com.texla.exception.ImproperRequestException;
import com.texla.exception.PdfFileNotFoundException;
import com.texla.helper.CommonUtils;
import com.texla.helper.ExtractFromPDF;
import com.texla.repository.TexlaRepository;

@Service
public class TexlaServiceImpl implements TexlaService {

	@Autowired
	private TexlaRepository texlaRepo;

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	private ExtractFromPDF extractFromPdf;

	@Override
	public double uploadPdfAndFetchData(MultipartFile pdfFile, String employeeName, String employeeId)
			throws Exception {
		if (pdfFile == null || employeeId == null || employeeName == null) {
			throw new ImproperRequestException("required fields is not present");
		}
		try {
			if (commonUtils.checkPdfFormat(pdfFile)) {
				String text = extractFromPdf.extract(pdfFile);
				text = text.replace("ALL EXPENSES OF EMPLOYEE", "");
				String[] textArray = text.split("\r\n");

				String travelExpenseString = textArray[2].replace("Travel Expense:", "").replace(" ", "");
				String foodExpenseString = textArray[3].replace("Food Expense:  ", "").replace(" ", "");
				String accommodationExpenseString = textArray[4].replace("Accommodation Expense:  ", "");
				String otherExpenseString = textArray[5].replace("Other Expense:  ", "").replace(" ", "");

				double[] allExpenses = fetchAllExpenses(travelExpenseString, foodExpenseString,
						accommodationExpenseString, otherExpenseString);

				// save in DB
				saveExpensesInDB(allExpenses, employeeName, employeeId);

				return allExpenses[4];
			} else {
				throw new PdfFileNotFoundException("given file is not PDF");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private double[] fetchAllExpenses(String travelExpenseString, String foodExpenseString,
			String accommodationExpenseString, String otherExpenseString) {
		double totalExpense, travelExpense, foodExpense, accommodationExpense, otherExpense;
		if (travelExpenseString == null || travelExpenseString.equals("")) {
			travelExpense = 0.0;
		} else {
			travelExpense = Double.parseDouble(travelExpenseString);
		}
		if (foodExpenseString == null || foodExpenseString.equals("")) {
			foodExpense = 0.0;
		} else {
			foodExpense = Double.parseDouble(foodExpenseString);
		}
		if (accommodationExpenseString == null || accommodationExpenseString.equals("")) {
			accommodationExpense = 0.0;
		} else {
			accommodationExpense = Double.parseDouble(accommodationExpenseString);
		}
		if (otherExpenseString == null || otherExpenseString.equals("")) {
			otherExpense = 0.0;
		} else {
			otherExpense = Double.parseDouble(otherExpenseString);
		}

		totalExpense = travelExpense + foodExpense + accommodationExpense + otherExpense;
		double[] array = new double[5];
		array[0] = travelExpense;
		array[1] = foodExpense;
		array[2] = accommodationExpense;
		array[3] = otherExpense;
		array[4] = totalExpense;
		return array;
	}

	private void saveExpensesInDB(double[] allExpenses, String employeeName, String employeeId) {
		EmployeeExpenses employeeExpenses = new EmployeeExpenses();
		employeeExpenses.setCreatedAt(commonUtils.generateDateAndTime());
		employeeExpenses.setUniqueId(commonUtils.generateUniqueId());
		employeeExpenses.setTravelExpense(allExpenses[0]);
		employeeExpenses.setFoodExpense(allExpenses[1]);
		employeeExpenses.setAccommodationExpense(allExpenses[2]);
		employeeExpenses.setOtherExpense(allExpenses[3]);
		employeeExpenses.setTotalExpense(allExpenses[4]);
		employeeExpenses.setEmployeeName(employeeName);
		employeeExpenses.setEmployeeId(employeeId);

		texlaRepo.save(employeeExpenses);
	}

}
