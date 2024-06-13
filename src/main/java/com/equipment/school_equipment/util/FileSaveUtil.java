package com.equipment.school_equipment.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class FileSaveUtil {
    public final static String PATH_MAINPAGE = "/Users/leemac/IdeaProjects/img/mainPage/";
    public final static String PATH_EQUIPMENT = "/Users/leemac/IdeaProjects/img/equipment/";

    public static String fileSave(MultipartFile multipartFile, String folderName){
        Objects.requireNonNull(multipartFile.getOriginalFilename(), "이미지 파일이 없습니다");

        String fileName = getFileName(multipartFile.getOriginalFilename());
        Path path = Paths.get(folderName, fileName);
        try {
            Files.write(path, multipartFile.getBytes());   // path 경로에 이미지 저장
        } catch (IOException ignored){};
        return fileName;
    }

    private static String getFileName(String originalFilename) {
        String fileType = getFileType(originalFilename);
        return UUID.randomUUID() + "." + fileType;
    }

    private static String getFileType(String originalFilename){
        return originalFilename.split("\\.")[1];
    }

}
