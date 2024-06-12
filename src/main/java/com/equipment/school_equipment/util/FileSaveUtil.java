package com.equipment.school_equipment.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class FileSaveUtil {
    private final static String PATH_SAVE = "/Users/leemac/IdeaProjects/img/" + "mainPage";
    private final MultipartFile multipartFile;

    public FileSaveUtil(MultipartFile multipartFile) {
        Objects.requireNonNull(multipartFile.getOriginalFilename(), "사진이 들어있지 않습니다");
        this.multipartFile = multipartFile;
    }

    public String fileSave(){
        String fileName = getFileName();
        Path path = Paths.get(PATH_SAVE, fileName);
        try {
            Files.write(path, multipartFile.getBytes());   // path 경로에 이미지 저장
        } catch (IOException ignored){};
        return fileName;
    }

    public String getFileName() {
        return UUID.randomUUID() + "." + getFileType();
    }

    private String getFileType(){
        return multipartFile.getOriginalFilename().split("\\.")[1];
    }

}
