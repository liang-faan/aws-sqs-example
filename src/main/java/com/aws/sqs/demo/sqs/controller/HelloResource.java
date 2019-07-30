/**
 * 
 */
package com.aws.sqs.demo.sqs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liang Faan <liang.faan@gmail.com>
 *
 */
@RestController
@RequestMapping("/rest/hello")
public class HelloResource {

	@GetMapping
	public String hello() {
		return "Hello Token";
	}
	
}
