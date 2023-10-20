package com.texla.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.texla.dto.ApiResponseModel;
import com.texla.service.TexlaService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/texla/api/v1")
public class TexlaController {

	private static final String errorStatus = "error";
	private static final String successStatus = "success";

	@Autowired
	private TexlaService texlaService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadPdfAndFetchData(
			@RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile,
			@RequestParam(value = "employeeName", required = false) String employeeName,
			@RequestParam(value = "employeeId", required = false) String employeeId) {
		try {
			double totalExpense = texlaService.uploadPdfAndFetchData(pdfFile, employeeName, employeeId);
			return ResponseEntity.ok(new ApiResponseModel(Map.of("total expense", totalExpense),
					"pdf file uploaded and total expense calculated", HttpStatus.OK.value(), successStatus));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ApiResponseModel(null,
					"error while uploading pdf: " + e.getMessage(), HttpStatus.BAD_GATEWAY.value(), errorStatus));
		}
	}

}
