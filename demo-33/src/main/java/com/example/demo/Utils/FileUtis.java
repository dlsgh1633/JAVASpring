package com.example.demo.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileDto;

@Component
public class FileUtis {

	private String uploadPath = Paths.get("C:\\board_file", "upload").toString();
//	private String uploadPath = Paths.get("src/main/resources/static/board_file","upload").toString();

	public List<FileDto> uploadFiles(final List<MultipartFile> multipartFiles) {
		List<FileDto> files = new ArrayList<>();
		System.out.println("다중 파일 업로드 uploadFiles 진입");
		for (MultipartFile multipartFile : multipartFiles) {
			if (multipartFile.isEmpty()) {
				continue;
			}
			System.out.println("다중파일 정보 : " + multipartFile);
			files.add(uploadFile(multipartFile));
		}
		return files;
	}

	public FileDto uploadFile(final MultipartFile multipartFile) {

		if (multipartFile.isEmpty()) {
			return null;
		}

		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String uploadPath = getUploadPath() + File.separator + saveName;
		
		System.out.println("uploadPath는 ? :" + uploadPath);
		File uploadFile = new File(uploadPath);
		Path path = Paths.get(uploadPath);

		try {
			// File newFile = new File(uploadPath);
			multipartFile.transferTo(path.toFile());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileDto fileDto = new FileDto();
		fileDto.setORG_FILE_NAME(multipartFile.getOriginalFilename());
		fileDto.setFILE_NAME(saveName);
		fileDto.setFILE_SIZE(multipartFile.getSize());

		return fileDto;

	}

	// 파일 다운로드
	public Resource readFileAsResource(final FileDto file) {

		String filename = file.getFILE_NAME();
		Path filePath = Paths.get(uploadPath, filename);
		try {
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists() == false || resource.isFile() == false) {
				throw new RuntimeException("fiLle not found : " + filePath.toString());
			}
			return resource;
		} catch (MalformedURLException e) {
			throw new RuntimeException("file not found : " + filePath.toString());
		}
	}

	private String generateSaveFilename(final String filename) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String extension = StringUtils.getFilenameExtension(filename);
		return uuid + "." + extension;
	}

	private String getUploadPath() {
		return makeDirectories(uploadPath);
	}

	private String getUploadPath(final String addPath) {
		return makeDirectories(uploadPath + File.separator + addPath);
	}

	private String makeDirectories(final String path) {
		File dir = new File(path);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		return dir.getPath();
	}

	/**
	 * 파일 삭제 (from Disk)
	 * 
	 * @param files - 삭제할 파일 정보 List
	 */
	public void deleteFiles(final List<FileDto> files) {
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		for (FileDto file : files) {
			
			deleteFile(file.getFILE_NAME());
		}
	}

	// 추가경로 삭제
	private void deleteFile(final String addPath, final String filename) {
		String filePath = Paths.get(uploadPath, addPath, filename).toString();
		deleteFile(filePath);
	}

	
	private void deleteFile(final String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

}
