package com.lexach.ClothingFeedParsers;

import com.lexach.ClothingFeedParsers.parsers.wildberries.WildberriesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ClothingFeedParsersApplication {

	@Autowired
	static WildberriesParser wildberriesParser;

	public static void main(String[] args) {
				SpringApplication.run(ClothingFeedParsersApplication.class, args);


	}
}
