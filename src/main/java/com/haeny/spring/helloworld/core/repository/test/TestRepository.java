package com.haeny.spring.helloworld.core.repository.test;

import org.springframework.stereotype.Repository;

import com.haeny.spring.helloworld.core.dto.TestDTO;

@Repository
public interface TestRepository {
	TestDTO test();
}
