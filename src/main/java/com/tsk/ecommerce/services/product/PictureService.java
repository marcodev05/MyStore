package com.tsk.ecommerce.services.picture;

import com.tsk.ecommerce.entities.Picture;

public interface PictureService {
	
	public Picture addPicture(Picture picture);
	
	public void deletePicture(Long idPicture);
	
	public Picture getPictureById(Long id);

}
