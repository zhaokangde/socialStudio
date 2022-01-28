package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() {
     System.out.println(dataSource.getClass());
	}

}
