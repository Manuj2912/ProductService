package com.company.dev.productService.controller;

import com.company.dev.productService.dto.ProductDetailsDto;
import com.company.dev.productService.response.ServiceResponse;
import com.company.dev.productService.service.ProductManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductDetailsController extends BaseController {
    @Autowired
    ProductManagerService productManagerService;
    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsController.class);
    @PostMapping(value = "/productSave",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ServiceResponse> productSave(
            @ModelAttribute ProductDetailsDto productDetailsDto){
        printApiRequest(productDetailsDto,"productSave");
        ServiceResponse serviceResponse = null;
        try{
            productManagerService.processRequest(productDetailsDto);
            serviceResponse = new ServiceResponse("00","Product Created","true");
        }catch (Exception e){
            logger.error(" Error at ProductDetailsController::productSave",e);
            serviceResponse = new ServiceResponse("99","Error In Product Creation ","false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
        }
        ResponseEntity<ServiceResponse> response = ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
        printApiResponse(response,"productSave");
        return response;
    }

    @GetMapping(value="/fetchProducts",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductDetailsDto>> fetchProducts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                 @RequestParam(defaultValue = "10") Integer pageSize){
        printApiRequest("","fetchProducts");
        List<ProductDetailsDto> productDetailsDtoList = null;
        try{
            productDetailsDtoList = productManagerService.fetchProducts(pageNo,pageSize);
        }catch (Exception ex){
            logger.error(" Error at ProductDetailsController::productSave",ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        ResponseEntity<List<ProductDetailsDto>> serviceResponse = ResponseEntity.status(HttpStatus.OK).body(productDetailsDtoList);
        return serviceResponse;
    }
}
