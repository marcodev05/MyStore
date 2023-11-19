package com.tsk.ecommerce.services.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tsk.ecommerce.dtos.forms.FileUploaded;
import com.tsk.ecommerce.exceptions.BadRequestException;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tsk.ecommerce.configs.FileStorageProperties;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileUploadService implements FileOperations {

	public static final String FILE_DIRECTORY = "/ecommerce/photos";
	private final Path fileStorageLocation;

	public FileUploadService(FileStorageProperties fileStorageProperties) {
		String directory = com.tsk.ecommerce.common.StringUtils.isBlank(fileStorageProperties.getUploadDirectory()) ? FILE_DIRECTORY : fileStorageProperties.getUploadDirectory();
		this.fileStorageLocation = Paths.get(System.getProperty("user.home") + directory)
				.toAbsolutePath()
				.normalize();
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (IOException e) {
			throw new BadRequestException("Error to store file");
		}
	}


	@Override
	public Resource getResource(String fileName) {
		Resource resource;
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			resource = new UrlResource(filePath.toUri());
			if (!resource.exists()) {
				throw new ResourceNotFoundException("File", fileName);
			}
		} catch (MalformedURLException e) {
			throw new BadRequestException("Error to get file resource");
		}
		return resource;
	}


	@Override
	public Object store(MultipartFile file) {
		try {
			String fileName = StringUtils.cleanPath(System.currentTimeMillis() + file.getOriginalFilename()).trim();
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.write(targetLocation, file.getBytes());
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<FileUploaded> upload(MultipartFile[] files) {
		return Arrays.stream(files)
				.map(this::upload)
				.collect(Collectors.toList());
	}


	public FileUploaded upload(MultipartFile file) {
		if (file != null) {
			String fileName = (String) store(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/uploads/")
					.path(fileName)
					.toUriString();
			return new FileUploaded(fileName, fileDownloadUri, FileUploaded.getType(file.getContentType()));
		}
		return null;
	}


	@Override
	public String delete(String fileName) {
		try {
			Path filePath = Paths.get(fileStorageLocation.toUri()).resolve(fileName);
			Files.delete(filePath);
			return fileName;
		} catch (IOException e) {
			return null;
		}
	}

}
