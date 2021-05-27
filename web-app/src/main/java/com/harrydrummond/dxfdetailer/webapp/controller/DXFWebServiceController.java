package com.harrydrummond.dxfdetailer.webapp.controller;

import com.harrydrummond.dxfdetailer.webapp.service.DXFResponse;
import com.harrydrummond.dxfdetailer.webapp.service.DXFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/dxf")
public class DXFWebServiceController {

    @Autowired
    private DXFService dxfService;

    @PostMapping("/upload")
    private Long uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return dxfService.uploadFile(multipartFile);
    }

    @GetMapping(value = "/files/{fileId}")
    private ResponseEntity<DXFResponse> getFileVariables(@PathVariable Long fileId) {
        return new ResponseEntity<>(dxfService.getFileVariables(fileId), HttpStatus.OK);
    }
}