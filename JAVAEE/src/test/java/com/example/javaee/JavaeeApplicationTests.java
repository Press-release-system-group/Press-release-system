package com.example.javaee;

import com.example.javaee.dao.SelectDao;
import com.example.javaee.vo.SimpleNews;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JavaeeApplicationTests {

	@Resource
	SelectDao selectDao;
	@Test
	void contextLoads() {
		int i=0;
		int j=1;
		List<Integer> list=new ArrayList<>();
		list.add(i);
		list.add(j);
	List<SimpleNews>	hh= selectDao.newsSelectByStatus(list);
		System.out.println(hh.get(0).getTitle());

	}

}
