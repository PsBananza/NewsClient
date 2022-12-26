package com.dmitry.NewsClient.service.fileInterface;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String fileUpt(MultipartFile multipartFile) throws IOException;
    UrlResource getFile(String fileName) throws MalformedURLException;
}
