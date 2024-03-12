package com.tsk.ecommerce.services.product.impl;

import com.tsk.ecommerce.dtos.requests.category.CategoryRequestDto;
import com.tsk.ecommerce.entities.Category;
import com.tsk.ecommerce.entities.Picture;
import com.tsk.ecommerce.entities.models.FileUploaded;
import com.tsk.ecommerce.repositories.CategoryRepository;
import com.tsk.ecommerce.repositories.PictureRepository;
import com.tsk.ecommerce.services.file.FileUploadService;
import com.tsk.ecommerce.services.mappers.CategoryMapper;
import com.tsk.ecommerce.services.tools.IObjectFinder;
import com.tsk.ecommerce.shared.MockResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private FileUploadService fileUploadService;
    @Mock
    private PictureRepository pictureRepository;
    @Mock
    private IObjectFinder objectFinder;
    @Mock
    private CategoryMapper categoryMapper;

    private final Category categoryOne = MockResource.createMockCategoryOne();
    private final Picture pictureOne = MockResource.createMockPictureOne();
    @Test
    @DisplayName("Should create a new category")
    void create() {
        //given
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setCode("ONE");
        requestDto.setName("Category one");
        requestDto.setDescription("Category one description");

        String fileContent = "some file content";
        MockMultipartFile categoryImage = new MockMultipartFile("image", "cat1_image.jpeg", "image/jpeg", fileContent.getBytes());
        requestDto.setImage(categoryImage);

        //mock config
        FileUploaded mockFileUploaded = mock(FileUploaded.class);
        when(categoryRepository.findByCode(any())).thenReturn(Optional.empty());
        when(categoryMapper.fromCategoryDto(any(Category.class), any(CategoryRequestDto.class))).thenAnswer(var-> var.getArgument(0));
        when(fileUploadService.upload(any(MultipartFile.class))).thenReturn(mockFileUploaded);
        when(categoryRepository.save(any(Category.class))).thenReturn(categoryOne);

        //when
        Category responseCategory = categoryService.create(requestDto);

        //then
        verify(pictureRepository, times(1)).save(any(Picture.class));
        verify(categoryRepository).save(any());
        assertThat(responseCategory).isNotNull();
        assertThat(responseCategory.getCode()).isEqualTo(requestDto.getCode());
    }

    @Test
    @DisplayName("Should update category")
    void update() {
        //given
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setCode("ONE");
        requestDto.setName("Category TWO");
        requestDto.setDescription("Category one description");

        //mock config
        BindingResult mockBindingResult = mock(BindingResult.class);
        Category mockUpdatedCategory = new Category();
        BeanUtils.copyProperties(categoryOne, mockUpdatedCategory);
        mockUpdatedCategory.setName(requestDto.getName());

        when(objectFinder.findById(categoryRepository, "category", categoryOne.getId())).thenReturn(categoryOne);
        when(categoryMapper.fromCategoryDto(categoryOne, requestDto)).thenReturn(mockUpdatedCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(mockUpdatedCategory);

        Category returnerCategory = categoryService.update(categoryOne.getId(), requestDto);

        verify(categoryRepository).save(any(Category.class));
        verify(categoryMapper, times(1)).fromCategoryDto(categoryOne, requestDto);
        assertThat(returnerCategory).isNotNull();
    }

    @Test
    void deleteCategory() {
        categoryOne.setImage(pictureOne);
        when(objectFinder.findById(categoryRepository, "category", categoryOne.getId())).thenReturn(categoryOne);

        categoryService.deleteCategory(categoryOne.getId());

        verify(categoryRepository, times(1)).deleteById(categoryOne.getId());
    }
}