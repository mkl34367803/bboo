package com.smart.oo.service.trigger;

import org.springframework.stereotype.Service;

@Service("OOTestTrigger")
public class TestTrigger {

	public void test() {

		System.out.println("test............" + System.currentTimeMillis());

	}

}
