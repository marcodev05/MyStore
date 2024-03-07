package com.tsk.ecommerce.services.product.impl;

import java.util.List;

import com.tsk.ecommerce.dtos.PaginationResponse;
import com.tsk.ecommerce.dtos.requests.category.CategorySearchDto;
import com.tsk.ecommerce.entities.models.FileUploaded;
import com.tsk.ecommerce.dtos.requests.category.CategoryRequestDto;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.repositories.PictureRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.file.FileUploadService;
import com.tsk.ecommerce.services.product.CategoryService;
import com.tsk.ecommerce.services.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.repositories.CategoryRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final FileUploadService fileUploadService;
	private final PictureRepository pictureRepository;

	@Override
	public Category create(CategoryRequestDto request, BindingResult bindingResult) {
		FieldValidator.validate(bindingResult);
		Category c = new Category();
		c.setName(request.getName());
		c.setCode(request.getCode());
		c.setDescription(request.getDescription());
		if (request.getImage() != null) {
			uploadPicture(request.getImage(), c);
		}
		return categoryRepository.save(c);
	}

	@Override
	public Category update(Long id, CategoryRequestDto request, BindingResult bindingResult) {
		FieldValidator.validate(bindingResult);
		Category c = this.getCategoryById(id);
		c.setName(request.getName());
		c.setCode(request.getCode());
		c.setDescription(request.getDescription());
		if (request.getImage() != null) {
			updatePicture(request.getImage(), c);
		}
		return categoryRepository.save(c);
	}

	@Override
	public PaginationResponse<List<Category>> searchCategory(CategorySearchDto params) {
		return categoryRepository.searchCategory(params);
	}

	@Override
	public Category getCategoryById(Long id) {
		return ObjectFinder.findById(categoryRepository, "category", id);
	}

	@Override
	public void deleteCategory(Long id) {
		//@Todo check if this category don't have product online or currently in order.
		Category category = getCategoryById(id);
		categoryRepository.deleteById(id);
		fileUploadService.delete(category.getImage().getName());
	}

	private void updatePicture(MultipartFile image, Category c) {
		Picture oldPicture = c.getImage();
		uploadPicture(image, c);
		fileUploadService.delete(oldPicture.getName());
	}

	private void uploadPicture(MultipartFile file, Category c) {
			FileUploaded fileUploaded = fileUploadService.upload(file);
			Picture picture = new Picture(null, fileUploaded.getName(), fileUploaded.getLink());
			picture = pictureRepository.save(picture);
			c.setImage(picture);
	}

}
