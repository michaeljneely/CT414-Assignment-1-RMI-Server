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

	LogonServer ls;
	LocalDate laterDate, earlierDate;
	LocalTime laterTime, earlierTime;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		ls = new LogonServer();
		laterDate = LocalDate.now().plusDays(1);
		laterTime = LocalTime.now().plusHours(1);
		earlierDate = LocalDate.now().minusDays(1);
		earlierTime = LocalTime.now().minusHours(1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testValidToken() {
		String token = ls.generateToken("123", LocalDateTime.of(laterDate, laterTime));
		assertEquals(ls.isTokenValid("123", token), true);	
	}
	
	@Test
	void testExpiredToken() {
		String token = ls.generateToken("123", LocalDateTime.of(earlierDate, earlierTime));
		assertEquals(ls.isTokenValid("123", token), false);	
	}
	
	@Test
	void testWrongStudentIDToken() {
		String token = ls.generateToken("124", LocalDateTime.of(laterDate, laterTime));
		assertEquals(ls.isTokenValid("123", token), false);
	}

}
