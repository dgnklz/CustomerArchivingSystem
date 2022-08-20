package com.project.customerarchiving.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.project.customerarchiving.entities.concretes.File;


public interface FileService {

	List<File> getAllFiles(Optional<Long> customerId);
	
	File saveOneFile(File newFile);

	File updateOneFile(Long fileId, File newFile);

	void deleteById(Long fileId);
	
}
