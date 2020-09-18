package com.company.dev.productService.helper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMappperGenerator {
   private ModelMapper modelMapper = new ModelMapper();
       public ModelMapper getInstance () {
           return modelMapper;
       }
}
