package com.uit.chophen.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FCMInitializer {

	@Value("${app.firebase-config}")
	private String firebaseConfigPath;
	
	@Value("${app.storagebucket}")
	private String storageBucket;
	Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

	@PostConstruct
	public void initialize() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());

		FirebaseOptions options = FirebaseOptions.builder().setCredentials(googleCredentials).setStorageBucket(storageBucket).build();

		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
			logger.info("firebase app initialized");

		}
	}

}
