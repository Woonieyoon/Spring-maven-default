package com.haeny.spring.helloworld.web.controller.home;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.haeny.spring.helloworld.core.repository.test.TestRepository;
import com.haeny.spring.helloworld.web.controller.AbstractController;

@Controller
public class HomeController extends AbstractController{

	@Autowired
	private TestRepository testMapper;
	
	@GetMapping("/home.do")
	public String home() {
		LOGGER.debug(testMapper.test().getName());
		return "home/index";
	}
	
	@PostMapping("/fileupload.do")
	public String fileupload(@RequestParam("name") String name, @RequestPart("picture") MultipartFile file) throws IllegalStateException, IOException {
		LOGGER.debug("Input Name is {}", name);
		String uploadDir = environment.getProperty("fileupload.upload.dir", String.class, "/helloworld/upload");
		File destFile = new File(uploadDir, file.getName());
		file.transferTo(destFile);
		return "home/index";
	}
	
}
