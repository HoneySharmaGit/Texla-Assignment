package com.texla.service;

import org.springframework.web.multipart.MultipartFile;

public interface TexlaService {

	double uploadPdfAndFetchData(MultipartFile pdfFile, String employeeName, String employeeId) throws Exception;

}
