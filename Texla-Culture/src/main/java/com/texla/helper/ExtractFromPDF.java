package com.texla.helper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExtractFromPDF {

	private final Logger log = LoggerFactory.getLogger(ExtractFromPDF.class);

	public String extract(MultipartFile pdfFile) throws Exception {
		String text;
		try (final PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
			final PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(document);
		} catch (Exception ex) {
			log.error("Error parsing PDF: ", ex.getMessage());
			throw ex;
		}
		return text;
	}
}
