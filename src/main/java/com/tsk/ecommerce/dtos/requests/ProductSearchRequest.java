package com.tsk.ecommerce.dtos.requests;

import com.tsk.ecommerce.common.Pagination;
import com.tsk.ecommerce.entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequest {

	private String name;

	@Valid
	private Pagination pagination;
}
