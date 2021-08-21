package com.tsk.ecommerce.service.picture;

import com.tsk.ecommerce.entities.Picture;

public interface PictureService {
	
	public Picture addPicture(Picture picture);
	
	public void deletePicture(Long idPicture);
	
	public Picture getPictureById(Long id);

}
