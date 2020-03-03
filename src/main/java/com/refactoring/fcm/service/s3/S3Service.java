package com.refactoring.fcm.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
	public String getFileURL(String bucketName, String fileName);

	public String upload(MultipartFile multipartFile, String dirName, String tr_id) throws IOException;

	public void deleteFile(String filename);

	public String getBucket();
}
