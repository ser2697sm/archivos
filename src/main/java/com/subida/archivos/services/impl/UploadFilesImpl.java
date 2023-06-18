package com.subida.archivos.services.impl;

import com.subida.archivos.services.IUploadFilesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class UploadFilesImpl implements IUploadFilesService {

    @Override
    public String handleFileUpload(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();

            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if(fileSize > maxFileSize) {
                return "File size must be less then or equal 5 MB";
            }

            if(!fileOriginalName.endsWith(".pdf")) {
                return "Only pdf files are allowed!";
            }

            //para pillar la extension del archivo
            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));

            //para tener el archivo junto
            String newFileName = fileName + fileExtension;

            File folder = new File("src/main/resources/pdfs/");

            //para crear ña carpeta en caso de que no exista
            if(!folder.exists()) {
                folder.mkdirs();
            }

            Path path= Paths.get("src/main/resources/pdfs/" + newFileName);

            Files.write(path,bytes);

            return "File updload sucesfull";

        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
