package com.tsk.ecommerce.services.product;

import java.util.ArrayList;
import java.util.List;

import com.tsk.ecommerce.entities.models.FileUploaded;
import com.tsk.ecommerce.dtos.requests.CategoryRequest;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.repositories.PictureRepository;
import com.tsk.ecommerce.services.ObjectFinder;
import com.tsk.ecommerce.services.file.FileUploadService;
import com.tsk.ecommerce.services.validators.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.repositories.CategoryRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements ICrudCategoryService {

	private final CategoryRepository categoryRepository;
	private final FileUploadService fileUploadService;
	private final PictureRepository pictureRepository;

	@Override
	public Category create(CategoryRequest request, BindingResult bindingResult) {
		FieldValidator.validate(bindingResult);
		Category c = new Category();
		c.setName(request.getName());
		c.setDescription(request.getDescription());
		if (request.getImage() != null) {
			uploadPicture(request.getImage(), c);
		}
		return categoryRepository.save(c);
	}

	@Override
	public Category update(Long id, CategoryRequest request, BindingResult bindingResult) {
		FieldValidator.validate(bindingResult);
		Category c = this.getCategoryById(id);
		c.setName(request.getName());
		c.setDescription(request.getDescription());
		if (request.getImage() != null) {
			updatePicture(request.getImage(), c);
		}
		return categoryRepository.save(c);
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return ObjectFinder.findById(categoryRepository, "category", id);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = getCategoryById(id);
		categoryRepository.deleteById(id);
		fileUploadService.delete(category.getImage().getName());
	}

	public List<Product> getAllProductsByCategory(Integer idCategory) {
		//TODO find products by category
		return new ArrayList<>();
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
