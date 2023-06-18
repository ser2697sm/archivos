package com.subida.archivos.controllers;

import com.subida.archivos.services.IUploadFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {
    @Autowired
    IUploadFilesService uploadFilesService;

    @PostMapping("/documents")
    private ResponseEntity<String> uploadDocument( @RequestParam("file") MultipartFile file) throws Exception {
        return  new ResponseEntity<>(uploadFilesService.handleFileUpload(file),HttpStatus.OK) ;
    }
}
