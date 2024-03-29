package com.example.backend;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	void contextLoads() {
		String username = stringEncryptor.encrypt("");
		System.out.println(username);
	}

}
