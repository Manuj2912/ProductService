package com.company.dev.productService.service;

import com.company.dev.productService.controller.ProductDetailsController;
import com.company.dev.productService.dao.ProductDetails;
import com.company.dev.productService.dto.CommonMultipartFile;
import com.company.dev.productService.dto.ProductDetailsDto;
import com.company.dev.productService.helper.ModelMappperGenerator;
import com.company.dev.productService.repository.ProductCategoryInterface;
import com.company.dev.productService.repository.ProductDetailsInterface;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagerService {
    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsController.class);
    @Autowired
    ProductDetailsInterface productDetailsInterface;
    @Autowired
    ProductCategoryInterface productCategoryInterface;
    @Autowired
    ModelMappperGenerator modelMappperGenerator;

    public void processRequest(ProductDetailsDto productDetailsDto) throws Exception {
        ModelMapper modelMapper = modelMappperGenerator.getInstance();
        ProductDetails productDetails = modelMapper.map(productDetailsDto,ProductDetails.class);
        productDetails.setProductImages(transformImage(productDetailsDto.getProductImageArray()));
        if(productCategoryInterface.existsById(productDetailsDto.getCategoryId())){
            productDetailsInterface.save(productDetails);
        }else{
            throw new EntityNotFoundException("No Product Category found with id "+productDetailsDto.getCategoryId());
        }
    }

    private String transformImage(MultipartFile[] productImageArray) throws IOException {
        StringBuilder imageString = new StringBuilder();
        if(productImageArray != null){
            for(MultipartFile productImage :productImageArray){
                if(imageString.length() > 0 ){
                    imageString.append("|");//multiple pipe seperated images
                }
                imageString.append(org.apache.commons.io.IOUtils.toByteArray(productImage.getInputStream()));
            }
        }
        return imageString.toString();
    }

    public List<ProductDetailsDto> fetchProducts(Integer pageNo, Integer pageSize) throws IOException {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<ProductDetails> productDetailsPage = productDetailsInterface.findAll(pageable);
        List<ProductDetailsDto> productDetailsDtoList = new ArrayList<>();
        if(productDetailsPage.hasContent()){
            List<ProductDetails> productDetailsList = productDetailsPage.getContent();
            ModelMapper modelMapper = modelMappperGenerator.getInstance();
            for(ProductDetails productDetails:productDetailsList) {
                ProductDetailsDto productDetailsDto = modelMapper.map(productDetails, ProductDetailsDto.class);
                List<MultipartFile> multipartFilesList = new ArrayList<MultipartFile>();
                String fileName = "product_image";
                int fileCount = 0;
                for(String imageString : productDetails.getProductImages().split("|")){
                    fileCount++;
                    byte[] imageInByte = IOUtils.resourceToByteArray(imageString);
                    CommonMultipartFile commonMultipartFile = new CommonMultipartFile(imageInByte,fileName+"_"+fileCount+"."+"png");
                    multipartFilesList.add(commonMultipartFile);
                }
                productDetailsDto.setProductImageArray((MultipartFile[])multipartFilesList.toArray());
                productDetailsDtoList.add(productDetailsDto);
            }
        }else{
            return new ArrayList<ProductDetailsDto>();
        }
        return productDetailsDtoList;
    }
}
