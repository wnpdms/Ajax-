package com.yedam.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.app.emp.service.GreetingVO;
import com.yedam.app.emp.service.MemberVO;

@SpringBootTest
class BootRestServerApplicationTests {

	//@Test
	void jacksonTest() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		GreetingVO vo = new GreetingVO("hello","철수"); // VO 객체를 만듬
		String str = om.writeValueAsString(vo); // VO 객체를 String으로 바꿔줌
		System.out.println(str);
		assertThat(str.startsWith("{"));
		// @ResponseBody : 객체(vo, amp, List) => json 형식의 String으로 바꿔줌
	}
	
	//@Test
	void mapTest() throws JsonProcessingException {
		HashMap<String,Object> map = new HashMap<>();
		map.put("firstName","길동");
		map.put("dept",10);
		map.put("hobby",new String[]{"독서","게임"}); // 배열도 가능
		ObjectMapper om = new ObjectMapper();
		String str = om.writeValueAsString(map);
		System.out.println(str);
		
		// {firstName:"길동", dept:10, hobby:["독서","게임"]}
	}
	
	//@Test
	void jacksonReadTest() throws JsonMappingException, JsonProcessingException {
		String str = "{\"msg\":\"hello\",\"name\":\"철수\"}";
		ObjectMapper om = new ObjectMapper();
		GreetingVO vo = om.readValue(str, GreetingVO.class);
		System.out.println(vo);
		assertThat(vo.getMsg().equals("hello"));
		
		// @ResponseBody json String ==> 객체( {} => vo, map )
		// 								      [] => list
	}

	@Test
	void readTest() throws JsonMappingException, JsonProcessingException {
		String str = "{\"firstName\":\"길동\",\"dept\":10,\"hobby\":[\"독서\",\"게임\"]}";
		ObjectMapper om = new ObjectMapper();
		//HashMap<String,Object> map = om.readValue(str, HashMap.class);
		//System.out.println(map);
		//System.out.println(map.get("firstName")); // 원하는 것만 읽어냄
		MemberVO vo = om.readValue(str, MemberVO.class);
		System.out.println(vo);
	}
}
