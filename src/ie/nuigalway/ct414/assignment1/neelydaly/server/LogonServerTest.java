package ie.nuigalway.ct414.assignment1.neelydaly.server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogonServerTest {

	private LogonServer ls;
	private LocalDate laterDate, earlierDate;
	private LocalTime laterTime, earlierTime;
	private static final String testDB = "students_test.txt";
	private static final String testStudent1 = "123;cats;bct;ct414,ct420";
	private static Path file;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		file = FileSystems.getDefault().getPath(Paths.get(".").toAbsolutePath().normalize().toString(), testDB);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
		    writer.write(testStudent1, 0, testStudent1.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		System.out.println(LocalDateTime.of(LocalDate.of(2018, 3, 4), LocalTime.of(23, 59)));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
		    Files.delete(file);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", file);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", file);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		ls = new LogonServer(testDB);
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
