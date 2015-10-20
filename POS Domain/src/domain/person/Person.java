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
	private Role role;

	//Constructor for creating new users, salt is generated.
	public Person(String userId, String password, String firstName, String lastName, Role role) {
		setUserId(userId);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}

	//Constructor for creating existing users, salt is given.
	public Person(String userId, String password, byte[] salt, String firstName, String lastName, Role role) {
		setUserId(userId);
		setHashedPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}

	public String getFirstName() {
		return firstName;
	}

	public static boolean isValidFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No first name given");
		}
		return true;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public static boolean isValidLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		return true;
	}

	public void setLastName(String lastName) {
			this.lastName = lastName;
	}	

	public String getUserId() {
		return userId;
	}
	
	public Role getRole() {
		return role;
	}
	
	private void setRole(Role role) {
		this.role = role;
	}

	public static boolean isValidUserId(String userId) {
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
		return true;
	}

	public void setUserId(String userId) {
			this.userId = userId;
	}	

	public String getHashedPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		return getHashedPassword().equals(sha1(password, getSalt()));
	}

	public static boolean isValidPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return true;
	}

	private void setHashedPassword(String password) {
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
