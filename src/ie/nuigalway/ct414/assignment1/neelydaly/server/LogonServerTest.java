package ie.nuigalway.ct414.assignment1.neelydaly.server;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogonServerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		LogonServer ls = new LogonServer();
		LocalDate laterDate = LocalDate.now().plusDays(1);
		LocalTime laterTime = LocalTime.now().plusHours(1);
		String token = ls.generateToken("13100590", LocalDateTime.of(laterDate, laterTime));
		assertEquals(ls.isTokenValid("13100590", token), true);
		
	}

}
