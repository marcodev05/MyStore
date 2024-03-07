package com.tsk.ecommerce.dtos.requests.products;

import com.tsk.ecommerce.dtos.OrderBy;
import com.tsk.ecommerce.dtos.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto {

	private String keyword;
	private String code;
	private Long categoryId;

	@Valid
	private Pagination pagination = new Pagination();

	@Valid
	private OrderBy orderBy = new OrderBy();
}
