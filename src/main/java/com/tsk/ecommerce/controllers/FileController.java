package com.tsk.ecommerce.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tsk.ecommerce.services.file.FileOperations;


@RestController
public class FileController {

	private final FileOperations fileOperations;

	public FileController(FileOperations fileOperations) {
		this.fileOperations = fileOperations;
	}


	@GetMapping("/uploads/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		try {
			Resource resource = fileOperations.getResource(fileName);
			String contentType = null;
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} catch (IOException e) {
			throw new ResourceNotFoundException("File", fileName);
		}
	}

}
