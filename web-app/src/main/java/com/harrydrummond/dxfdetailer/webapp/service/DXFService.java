package com.harrydrummond.dxfdetailer.webapp.service;

import com.harrydrummond.dxfdetailer.readerapi.Geometry;
import com.harrydrummond.dxfdetailer.webapp.model.DXFFile;
import com.harrydrummond.dxfdetailer.webapp.repository.DXFDbRepository;
import org.kabeja.dxf.DXFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class DXFService {

    private final DXFDbRepository dxfRepository;

    @Autowired
    public DXFService(DXFDbRepository dxfRepository) {
        this.dxfRepository = dxfRepository;
    }

    /**
     * Uploads file to sql server database
     * @param multipartFile File to save to database
     * @return ID of file in database
     * @throws Exception if anything goes wrong ooOoOoo
     */
    public Long uploadFile(final MultipartFile multipartFile) throws Exception {
        DXFFile dxfFile = new DXFFile();
        dxfFile.setName(multipartFile.getOriginalFilename());
        dxfFile.setContent(multipartFile.getBytes());

        return dxfRepository.save(dxfFile)
                .getId();
    }

    /**
     * Parses selected file and parses it. Will return a DXFResponse Entity if
     * success.
     * @param fileId FileID from database
     * @return ResponseEntity<DXFResponse> if success.
     * HttpStatus - SERVICE UNAVAILABLE if temp file could not be created on server or missing.
     * HttpStatus - UNPROCESSABLE ENTITY if file cannot be parsed.
     */
    public DXFResponse getFileVariables(final Long fileId) {

        DXFFile dxfFile = dxfRepository
                .findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        File tempFile = null;

        try {
            tempFile = File.createTempFile("dxfbyte", null, null);

            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(dxfFile.getContent());

            tempFile.deleteOnExit();
            fos.close();
        } catch (IOException ioException) {
            System.err.println("Unable to write temp file with file id: [" + fileId + "].");
            ioException.printStackTrace();
        }

        // If file could not be created or was deleted before request completion.
        if (tempFile == null || !tempFile.exists()) {
           // return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            return null;
        }

        DXFDocument document = Geometry.parseFile(tempFile);
        // if file could not be parsed
        if (document == null) {
            //return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            return null;
        }

        return
                new DXFResponse(
                        dxfFile.getName(),
                        Geometry.getPathLength(document),
                        Geometry.getDocumentMaxWidth(document),
                        Geometry.getDocumentMaxHeight(document)
        );
    }


}