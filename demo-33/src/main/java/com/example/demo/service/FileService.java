package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FileDto;

public interface FileService {

	public void saveFiles(int boardId, List<FileDto> files);
	public List<FileDto> fileList(int BoardId);
	public FileDto findByUuid(String UUID);
	public int DeleteFlagByUUIDs (List<String> UUIDs);
	public int boardDeleteFiles (int boardId);
}
