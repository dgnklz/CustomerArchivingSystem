package com.project.customerarchiving.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.customerarchiving.business.abstracts.FileService;
import com.project.customerarchiving.dataAccess.abstracts.FileDao;
import com.project.customerarchiving.entities.concretes.File;

@Service
public class FileManager implements FileService{
	
	FileDao fileDao;
	
	public FileManager(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public List<File> getAllFiles(Optional<Long> customerId) {
		if(customerId.isPresent())
			return fileDao.findByCustomerId(customerId.get());
		return fileDao.findAll();
	}

	@Override
	public File saveOneFile(File newFile) {
		return fileDao.save(newFile);
	}

	@Override
	public File updateOneFile(Long fileId, File newFile) {
		Optional<File> file = fileDao.findById(fileId);
		if(file.isPresent()) {
			File foundFile = file.get();
			foundFile.setTitle(newFile.getTitle());
			fileDao.save(foundFile);
			return foundFile;
		}
		else
			return null;
	}

	@Override
	public void deleteById(Long fileId) {
		fileDao.deleteById(fileId);
	}
	
}
