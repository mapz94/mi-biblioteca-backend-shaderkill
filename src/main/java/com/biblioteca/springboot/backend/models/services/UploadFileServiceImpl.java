package com.biblioteca.springboot.backend.models.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.biblioteca.springboot.backend.models.dao.IDBFilesDao;
import com.biblioteca.springboot.backend.models.entity.DBFiles;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	@Autowired
	private IDBFilesDao dbFilesDao;
	

	@Override
	@Transactional
	public DBFiles cargar(MultipartFile archivo) throws FileUploadException {
		String fileName = StringUtils.cleanPath(archivo.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileUploadException("No se puede almacenar el archivo " + fileName + ". Debido a que incluye un nombre invalido.");
			}
			
			DBFiles dbFile = new DBFiles(fileName, archivo.getContentType(), archivo.getBytes());
			
			return dbFilesDao.save(dbFile);
			
		} catch (IOException ex) {
			throw new FileUploadException("No se puede almacenar el archivo " + fileName + ". Vuelva a intentar de nuevo", ex);
		}
	}

	@Override
	public DBFiles getPath(String archivoId) throws FileNotFoundException {
		return dbFilesDao.findById(archivoId).orElseThrow(() -> new FileNotFoundException("El archivo solicitado no se encuentra con la id" + archivoId));
	}

}
