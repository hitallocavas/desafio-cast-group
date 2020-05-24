package br.ufpe.cin.hcs3.documentmanager;

import br.ufpe.cin.hcs3.documentmanager.unit_test.DocumentServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {DocumentServiceTest.class})
class DocumentManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
