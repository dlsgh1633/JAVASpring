package com.example.demo.controller;



import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Utils.FileUtis;
import com.example.demo.model.FileDto;
import com.example.demo.service.FileService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;
    private final FileUtis fileUtis;
    
    @Autowired
    public FileApiController(FileService fileService, FileUtis fileUtis) {
    	this.fileUtis =fileUtis;
    	this.fileService = fileService;
    }


    // 첨부파일 다운로드
    @GetMapping("/board/{boardId}/files/{UUID}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final int boardId, @PathVariable final String UUID) {
        FileDto file = fileService.findByUuid(UUID);
        Resource resource = fileUtis.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getORG_FILE_NAME(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFILE_SIZE() + "")
                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getORG_FILE_NAME());
        }
    }

}