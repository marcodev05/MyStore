package com.tsk.ecommerce.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;


import com.tsk.ecommerce.services.product.CrudProductService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.services.file.FileStorageService;
import com.tsk.ecommerce.services.picture.PictureService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class FileController {

	private final CrudProductService crudProductService;
	private final PictureService pictureService;
	private final FileStorageService fileStorageService;

	public FileController(CrudProductService crudProductService,
						  PictureService pictureService,
						  FileStorageService fileStorageService) {
		this.crudProductService = crudProductService;
		this.pictureService = pictureService;
		this.fileStorageService = fileStorageService;
	}


	@Operation(summary = "upload a new picture for product")
	@PostMapping("/api/v1/uploadFile/{id}")
	public Product uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id ) {
		Product product = crudProductService.getProductById(id);
		Picture picture = new Picture();
		picture.setProduct(product);
		
		String fileName = fileStorageService.storeFile(file);	
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
															.path("/downloadFile/")
															.path(fileName)
															.toUriString();
		picture.setLink(fileDownloadUri);
		pictureService.addPicture(picture);
		return crudProductService.getProductById(id);
	}

	@Operation(summary = "upload multiple pictures ")
	@PostMapping("/api/v1/uploadMultipleFiles/{id}")
	public Product uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable Long id ){
		Arrays.asList(files)
				.stream()
				.map(file -> this.uploadFile(file, id))
				.collect(Collectors.toList());
		return crudProductService.getProductById(id);
	}


	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		//determiner le type de fichier
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Impossible de determiner le type de fichier");
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
	}

}
