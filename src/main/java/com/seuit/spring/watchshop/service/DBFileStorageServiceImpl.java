package com.seuit.spring.watchshop.service;


import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.seuit.spring.watchshop.entity.DBFile;
import com.seuit.spring.watchshop.repository.DBFileRepository;

import javassist.NotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



@Service
public class DBFileStorageServiceImpl implements DBFileStorageService{

    @Autowired
    private DBFileRepository dbFileRepository;
    
    @Override
    public DBFile storeFile(MultipartFile file) throws FileUploadException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileUploadException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileUploadException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    @Override
    public DBFile getFile(String fileId) throws NotFoundException {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("File not found with id " + fileId));
    }
    
}