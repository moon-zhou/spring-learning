package org.moonzhou.fiaa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class MyController {

	/**
	 * http://localhost:8080/name
	 * @return
	 */
	@GetMapping("/name")
	public String getName() {
		log.info("getName");
		return "moon zhou";
	}

	/**
	 * http://localhost:8080/age
	 * @return
	 */
	@GetMapping("/age")
	public Integer getAge() {
		log.info("getAge");
		return 18;
	}

	/**
	 * http://localhost:8080/exception
	 * @return
	 */
	@GetMapping("/exception")
	public String getException() {
		log.info("getException");
		throw new RuntimeException("报错啦");
	}

	/**
	 * http://localhost:8080/aop
	 * @return
	 */
	@GetMapping("/aop")
	public String getAop() {
		log.info("getAop");
		return "aop";
	}

	/**
	 * http://localhost:8080/all
	 * @return
	 */
	@GetMapping("all")
	public String getAll() {
		log.info("getAll");
		// 这里得抛个异常
		throw new RuntimeException("all exception");
	}

}