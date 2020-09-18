package com.company.dev.productService.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CommonMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String fileName;

    public CommonMultipartFile(byte[] imgContent,String fileName) {
        this.imgContent = imgContent;
        this.fileName = fileName;
    }
    @Override
    public String getName() {
        return null;
    }
    @Override
    public String getOriginalFilename() {
        return null;
    }
    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}