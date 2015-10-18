package domain.person;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

	private String firstName;
	private String lastName;
	private String userId;
	private String password;
	private byte[] salt;
	
	//Constructor for creating new users, salt is generated.
	public Person(String userId, String password, String firstName, String lastName) {
		setUserId(userId);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	//Constructor for creating existing users, salt is given.
	public Person(String userId, String password, byte[] salt, String firstName, String lastName) {
		setUserId(userId);
		setHashedPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}	
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		if (userId.isEmpty()) {
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(userId);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.userId = userId;
	}	
	
	public String getHashedPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return sha1(getHashedPassword(), getSalt()).equals(sha1(password, getSalt()));
	}

	private void setHashedPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	private void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public void setPassword(String password)  {
		SecureRandom random = new SecureRandom();
		byte seed[] = random.generateSeed(20);
		setSalt(seed);		
		setHashedPassword(sha1(password, seed));
	}
	
	private static String sha1(String string, byte[] seed) {		
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();			
			crypt.update(seed);
			crypt.update(string.getBytes("UTF-8"));		
			return new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
