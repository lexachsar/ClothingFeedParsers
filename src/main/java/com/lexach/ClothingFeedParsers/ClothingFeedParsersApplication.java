package com.lexach.ClothingFeedParsers;

import com.lexach.ClothingFeedParsers.parsers.wildberries.WildberriesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class ClothingFeedParsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClothingFeedParsersApplication.class, args);
	}

}