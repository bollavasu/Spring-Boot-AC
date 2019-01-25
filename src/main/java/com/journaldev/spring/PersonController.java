package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import java.util.Arrays;

@RestController
public class PersonController {

	@Autowired
	private Person person;
	
	@Autowired
    private ApplicationContext context;
	
	@RequestMapping("/")
	public String healthCheck() {
		display();
		return "Welcome to Spring Boot REST...";
	}
	
	public void display(){
		String[] beans = context.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }
	}
	
	@RequestMapping("/person/get")
	public Person getPerson(@RequestParam(name="name", required=false, defaultValue="Unknown") String name) {
		person.setName(name);
		return person;
	}
	
	@RequestMapping(value="/person/update", method=RequestMethod.POST)
	public Person updatePerson(@RequestParam(name="name", required=true) String name) {
		person.setName(name);
		return person;
	}
	
	@RequestMapping(value="/person/update2", method=RequestMethod.POST, consumes = "application/json")
	public Person updatePerson(@RequestBody Person p) {
		person.setName(p.getName());
		return person;
	}
}
