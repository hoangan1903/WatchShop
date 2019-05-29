package com.seuit.spring.watchshop.service;

import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import com.seuit.spring.watchshop.entity.DBFile;

import javassist.NotFoundException;

public interface DBFileStorageService {
	DBFile storeFile(MultipartFile file) throws FileUploadException;
	DBFile getFile(String fileId) throws NotFoundException;
}
