package com.texla.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.texla.repository.TexlaRepository;

@Component
public class CommonUtils {

	@Autowired
	private TexlaRepository texlaRepo;

	public String generateDateAndTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		String date = dateTimeFormatter.format(now);
		return date;
	}

	public String generateUniqueId() {
		String lastUniqueId = texlaRepo.getLastGeneratedUniqueId();
		if (lastUniqueId == null || lastUniqueId.equals("")) {
			return "UEEID1";
		} else {
			int number = Integer.parseInt((String) lastUniqueId.replace("UEEID", ""));
			return "UEEID" + (number + 1);
		}
	}

	public boolean checkPdfFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/pdf")) {
			return true;
		}
		return false;
	}

}
