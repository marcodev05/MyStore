package com.tsk.ecommerce.services.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileOperations {
	
	Object store(MultipartFile file);
	Object delete(String fileName);
	Resource getResource(String fileName);
}
