package com.tsk.ecommerce.shared;

import com.tsk.ecommerce.entities.*;
import com.tsk.ecommerce.entities.enumerations.ERole;

import java.util.ArrayList;

public class MockResource {

    public static UserEntity createMockUserToto() {
        return new UserEntity(1L,
                "toto",
                "password1234",
                "toto@toto.com",
                "toto",
                "test",
                new ArrayList<>());
    }

    public static RoleEntity createMockRole(ERole role){
        return new RoleEntity(1L, role);
    }

    public static Category createMockCategoryOne(){
        return new Category(
                1L,
                "ONE",
                "Category One",
                "Category one description",
                null);
    }

    public static Product createMockProductOne(){
        Product product = new Product();
        product.setId(1L);
        product.setCode("PROD1");
        product.setName("One");
        product.setDescription("One description");
        product.setFeatures(new ArrayList<>());
        product.setPictures(new ArrayList<>());
        return product;
    }

    public static Picture createMockPictureOne(){
        return new Picture(1L, "picture one.jpeg", "https://domain.com/picture_file_id.jpeg");
    }

}
