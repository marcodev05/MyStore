package com.tsk.ecommerce.services.product;

import com.tsk.ecommerce.services.product.CrudProductService;
import com.tsk.ecommerce.services.product.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.Product;
import com.tsk.ecommerce.exceptions.ResourceNotFoundException;
import com.tsk.ecommerce.repositories.PictureRepository;
import com.tsk.ecommerce.exceptions.BadRequestException;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

	private final PictureRepository pictureRepository;
	private final CrudProductService crudProductService;

	public PictureServiceImpl(PictureRepository pictureRepository, CrudProductService crudProductService) {
		this.pictureRepository = pictureRepository;
		this.crudProductService = crudProductService;
	}

	@Override
	public Picture addPicture(Picture picture) {
		Picture pic = new Picture();
		if (picture.getProduct() != null) {
			Product p = crudProductService.getProductById(picture.getProduct().getIdProduct());
			pic.setProduct(p);
		} else
			throw new BadRequestException("Le produit ne peut pas être null");
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
