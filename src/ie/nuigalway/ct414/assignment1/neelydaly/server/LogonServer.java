package ie.nuigalway.ct414.assignment1.neelydaly.server;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class LogonServer {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	protected String generateToken(String id, LocalDateTime expiryDate) {
		return this.encode("" + id + "AAA" + expiryDate.format(formatter) + "");
	}
	
	protected boolean isTokenValid(String id, String token) {
		String t = this.decode(token);
		String[] data = t.split("AAA");
		LocalDateTime expiryDate = LocalDateTime.parse(data[1], formatter);
		return (LocalDateTime.now().isBefore(expiryDate) && id.equals(data[0]));
	}

	private String encode(String token) {
		return Base64.getEncoder().encodeToString(token.getBytes());
	}
	
	private String decode(String encrypted_token) {
		return new String(Base64.getDecoder().decode(encrypted_token.getBytes()));
	}
}
