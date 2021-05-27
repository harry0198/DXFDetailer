package com.harrydrummond.dxfdetailer.webapp.controller;

import com.harrydrummond.dxfdetailer.webapp.service.DXFResponse;
import com.harrydrummond.dxfdetailer.webapp.service.DXFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dxf")
public class DXFWebController {

    @Autowired
    private DXFService dxfService;

    @PostMapping("/upload")
    private String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model) throws Exception {
        return getFileVariables(dxfService.uploadFile(multipartFile), model);
    }

    @GetMapping
    private String getFileVariables(Model model) {
        return "dxf";
    }

    @GetMapping("/files/{fileNum}")
    private String getFileVariables(@PathVariable Long fileNum, Model model) {
        DXFResponse dxfResponse = dxfService.getFileVariables(fileNum);
        if (dxfResponse == null || fileNum == null) {
            // cry yourself a river
            dxfResponse = new DXFResponse("NO RECORD", 0L, 0L, 0L);
        }
        model.addAttribute("fileResult", dxfResponse);
        return "dxf2";
    }
}