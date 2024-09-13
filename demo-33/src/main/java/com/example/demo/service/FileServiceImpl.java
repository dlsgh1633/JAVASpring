package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.FileMapper;
import com.example.demo.model.FileDto;

@Service
public class FileServiceImpl implements FileService {

	private final FileMapper fileMapper;

	@Autowired
	public FileServiceImpl(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	@Override
	public void saveFiles(int boardId, List<FileDto> files) {
		for (FileDto file : files) {

			file.setBOARDID(boardId);

		}
		fileMapper.insertFile(files);
	}

	@Override
	public List<FileDto> fileList(int BoardId) {

		return fileMapper.fileList(BoardId);
	}

	@Override
	public FileDto findByUuid(String UUID) {

		return fileMapper.findByUUID(UUID);
	}

	@Override
	public int DeleteFlagByUUIDs(List<String> UUIDs) {

		return fileMapper.updateDeleteFlagByUUIDs(UUIDs);
	}

	@Override
	public int boardDeleteFiles(int boardId) {

		return fileMapper.boardDeleteFile(boardId);
	}

}
