package com.biblioteca.springboot.backend.models.dao;

import com.biblioteca.springboot.backend.models.entity.DBFiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDBFilesDao extends CrudRepository<DBFiles, String>{

}
