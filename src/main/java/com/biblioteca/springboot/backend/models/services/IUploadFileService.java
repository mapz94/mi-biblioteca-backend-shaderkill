package com.biblioteca.springboot.backend.models.services;

import java.io.FileNotFoundException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import com.biblioteca.springboot.backend.models.entity.DBFiles;

public interface IUploadFileService {
	
	public DBFiles cargar(MultipartFile archivo) throws FileUploadException;
	public DBFiles getPath(String archivoId) throws FileNotFoundException;
}
