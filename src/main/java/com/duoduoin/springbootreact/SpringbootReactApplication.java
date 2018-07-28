package com.duoduoin.springbootreact;

import static java.lang.System.exit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.duoduoin.springbootreact.dao.KeyValueDao;
import com.duoduoin.springbootreact.dao.KeyValueObject;

@SpringBootApplication
public class SpringbootReactApplication implements CommandLineRunner {
	
	@Autowired
	private KeyValueDao service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {


        System.out.println("Display all customers...");
        List<KeyValueObject> list = new ArrayList<>();
        
        KeyValueObject item = new KeyValueObject();
        item.setName("name 1");
        item.setValue("value 1");
        list.add(item);
        
        item = new KeyValueObject();
        item.setName("name 2");
        item.setValue("value 2");
        list.add(item);
        
        KeyValueObject[] arr = service.getKeyValueObjects(list.toArray(new KeyValueObject[2]));

        List<KeyValueObject> list2 = Arrays.asList(arr);
        
        list2.stream().forEach(x -> System.out.println(x));

        exit(0);
    }
}
