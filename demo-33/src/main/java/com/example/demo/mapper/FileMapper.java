package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.FileDto;

@Mapper
public interface FileMapper {
	public void insertFile(List<FileDto> files);
	public List<FileDto> fileList(int BoardId);
	public FileDto findByUUID(String fileName);
	public int updateDeleteFlagByUUIDs(List<String> UUIDS);
	public int boardDeleteFile(int boardId);
}
