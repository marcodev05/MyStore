package com.tsk.ecommerce.dtos.requests.products;

import com.tsk.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UpdateProductRequest {

	@NotBlank(message = "Product name is required")
	private String nameProduct;
	
	@NotBlank(message = "Description is required")
	private String description;

	private Collection<Long> pictureToDeletes;

	private List<MultipartFile> files;

	@NotNull(message = "Category is required")
	private Long categoryId;

	public void commitTo(Product product){
		product.setName(nameProduct);
		product.setDescription(description);
	}

}
