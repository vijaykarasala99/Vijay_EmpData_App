package com.vijayit.dataloader;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.vijayit.entity.EmpEntity;
import com.vijayit.repo.EmpRepository;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private EmpRepository emprepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		emprepo.deleteAll();

		EmpEntity v1 = new EmpEntity();
		v1.setMail("viay@gmail");
		v1.setName("Vijay");
		v1.setSalary(2000.0);
		EmpEntity v2 = new EmpEntity();
		v2.setMail("kvs@gmail");
		v2.setName("Harsha");
		v2.setSalary(3000.0);
		EmpEntity v3 = new EmpEntity();
		v3.setMail("hlo@gmail");
		v3.setName("Siva");
		v3.setSalary(4000.0);

		List<EmpEntity> list = Arrays.asList(v1, v2, v3);
		emprepo.saveAll(list);
	}
}