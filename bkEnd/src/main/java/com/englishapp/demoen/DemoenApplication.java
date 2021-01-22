package com.englishapp.demoen;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.englishapp.demoen.validator.ThreadInstan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.englishapp.demoen.InfoProvider.*;

@SpringBootApplication
public class DemoenApplication {
//
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@Bean
	public AmazonS3 amazonS3ClientProvider(){
		BasicAWSCredentials basicAWSCredentials =new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY );

		AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(BUCKET_REGION).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
		amazonS3 = new AmazonS3Client(credentials);
		return amazonS3;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoenApplication.class, args);
	}


}
