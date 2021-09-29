package com.health.specialization;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.health.entity.Specialization;
import com.health.repository.SpecializationRepository;

//Activate data JPA in unit test.
@DataJpaTest(showSql =true)

//Use our own properties/yaml file as input to create datasource
@AutoConfigureTestDatabase(replace = Replace.NONE)

//After Unit Testing data will be deleted (Rollback=true) to keep same data in Database after unit Testing even.
@Rollback(false)

//Execute test cases in order on top of Unit test method
@TestMethodOrder(OrderAnnotation.class)
public class SpecializationrepositoryTest {

	@Autowired
	private SpecializationRepository repository;
	/**
	 * Test Save Operation
	 */
	@Disabled
	//@Disabled
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec=new Specialization(null,"Cardiologist","CRSLST","They are exports on the Heart and Blood vessels ");
		spec=repository.save(spec);
		assertNotNull(spec.getId(),"spec is not Created! ");
	}

	/**
	 * Test Display All Operation
	 */
	 @Disabled
	 //@Disabled
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List<Specialization> list=	repository.findAll();
		assertNotNull(list);
		if(list.isEmpty()){
			fail("No data exist in Database");
		}
	}
}
