package com.dmitry.NewsClient.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.UUID;

import com.dmitry.NewsClient.service.fileInterface.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;





@Service
@RequiredArgsConstructor
public class FileServiceImp implements FileService {
    @Value("${storage.dir}")
    private String uploadDir;
    private final Path root = Paths.get("src/main/resources/avatar");
    public static String avatar;
    @Override
    public String fileUpt(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Path copyLocation = Paths.get(uploadDir + File.separator + fileName);
        avatar = "http://localhost:8080/file/" + fileName;
        Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        return avatar;
    }

    @Override
    public UrlResource getFile(final String fileName) throws MalformedURLException {
        Path file = root.resolve(fileName);
        return new UrlResource(file.toUri());
    }




}
