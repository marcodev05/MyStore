package com.tsk.ecommerce.service.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exception.ResourceNotFoundException;
import com.tsk.ecommerce.repository.PictureRepository;
import com.tsk.ecommerce.service.exception.FormatDataInvalidException;
import com.tsk.ecommerce.service.product.ProductService;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

	@Autowired
	PictureRepository pictureRepository;
	
	@Autowired
	ProductService productService;
	
	
	
	@Override
	public Picture addPicture(Picture picture) {
		Picture pic = new Picture();

		if (picture.getProduct() != null) {
			Product p = productService.getProductById(picture.getProduct().getIdProduct());
			pic.setProduct(p);
		} else
			throw new FormatDataInvalidException("Le produit ne peut pas être null");
		
		pic.setLink(picture.getLink());
		return pictureRepository.save(pic);
	}

	
	
	@Override
	public void deletePicture(Long idPicture) {
		Picture pic = this.getPictureById(idPicture);
		pictureRepository.delete(pic);
	}



	@Override
	public Picture getPictureById(Long id) {
		return pictureRepository.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("Photo introuvable"));
	}

}
