package com.bridgelabz.fundoonotes.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.fundoonotes.configuration.ApplicationproperitesReader;
import com.bridgelabz.fundoonotes.exception.FundooException;

@Component
public class S3Service {

	
	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	private ApplicationproperitesReader applicationPropertiesReader;
	
	private static final String SUFFIX = "/";
	
	
	public String fileUpload(MultipartFile file, String folderName, String id) {
		String key = id + generateKey(file);
		
		createFolder(applicationPropertiesReader.getBucketName(), folderName, s3Client);
		ObjectMetadata ob = new ObjectMetadata();
		ob.setContentDisposition(file.getName().replaceAll(" ", "_"));
		ob.setContentLength(file.getSize());
		ob.setContentType(file.getContentType());
		ob.setContentDisposition("inline");
        ob.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
		
		String fileName = folderName + SUFFIX + key;
		
		try {
			s3Client.putObject(new PutObjectRequest(applicationPropertiesReader.getBucketName(),
					fileName, file.getInputStream(), ob));
			//.withCannedAcl(CannedAccessControlList.PublicRead))
			

		} catch (SdkClientException | IOException e) {
			e.printStackTrace();
			throw new FundooException(HttpStatus.BAD_GATEWAY.value(),"Error while uploading image");
		}
		return fileName;
	}
	
	
//	public String getImgUrl(String key) throws AdminException {
//		try {
//			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
//					new GeneratePresignedUrlRequest(applicationPropertiesReader.getBucketName(),key);
//			generatePresignedUrlRequest.setMethod(HttpMethod.GET);
//			generatePresignedUrlRequest.setExpiration(DateTime.now().plusHours(1).toDate());
//			URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
//
//			return signedUrl.toString();
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new AdminException(105);
//		}
//	}
	
	private static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadata);
		client.putObject(putObjectRequest);
	}

	private String generateKey(MultipartFile file) {
		return "_" + Instant.now().getEpochSecond() + "_" + file.getOriginalFilename().replaceAll(" ", "_");
	}

}
