package com.refactoring.fcm.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Data
public class S3ServiceImpl implements S3Service{

    private AmazonS3 amazonS3Client;

    @Value("${amazonProperties.bucketName}")
    private String bucket;

    Logger logger=LoggerFactory.getLogger(S3ServiceImpl.class);


    @Autowired
    public void setAmazonS3Client(AmazonS3 amazonS3){
    	this.amazonS3Client=amazonS3;
    }

    /*
     *  파일생성 규칙 -> trainer/trainerid 에 따라 해당 경로를 통해 이미지 불러옴
     */
	public String getFileURL(String bucketName, String fileName) {
			return "https://s3-ap-southeast-1.amazonaws.com/fmbucketest/trainer/"+fileName;
	}


    public String upload(MultipartFile multipartFile, String dirName, String tr_id) throws IOException {
        File uploadFile = convert(multipartFile, tr_id)
                .orElseThrow(() -> new IllegalArgumentException("Convert Fail!!"));

        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            logger.info("파일이 삭제되었습니다.");
        } else {
            logger.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file, String tr_id) throws IOException {
        File convertFile = new File(tr_id);
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    public void deleteFile(String filename){
	   try{
		   amazonS3Client.deleteObject(bucket+"/trainer",filename );
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   logger.info("정상적으로 삭제되었습니다");
   }
}
