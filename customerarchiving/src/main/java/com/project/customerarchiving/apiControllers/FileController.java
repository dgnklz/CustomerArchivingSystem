package com.project.customerarchiving.apiControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.customerarchiving.business.abstracts.FileService;
import com.project.customerarchiving.entities.concretes.File;

@RestController
@RequestMapping("/files")
public class FileController {
	
	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	//Bring all created files
	@GetMapping
	public List<File> getAllFiles(@RequestParam Optional<Long> customerId){
		return fileService.getAllFiles(customerId);
	}
		
	//Create new file
	@PostMapping
	public File saveOneFile(@RequestBody File newFile) {
		return fileService.saveOneFile(newFile);
	}
	
	//Update file
	@PutMapping("/{fileId}")
	public File updateOneFile(@PathVariable Long fileId, @RequestBody File newFile) {
		return fileService.updateOneFile(fileId, newFile);
	}
	
	//Delete file
	@DeleteMapping("/{fileId}")
	public void deleteOneFile(@PathVariable Long fileId) {
		fileService.deleteById(fileId);
	}
	
}
