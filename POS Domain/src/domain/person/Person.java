package domain.person;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private byte[] salt;

	public Person() {
		salt = new SecureRandom().generateSeed(20);
	}

	public Person(byte[] salt) {
		this.salt = salt;
	}

	// new person without salt
	public Person(String userId, String password, String firstName,
			String lastName) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		this();
		setUserId(userId);
		setPassword(password); // this is going to be hashed
		setFirstName(firstName);
		setLastName(lastName);
	}

	// re-create existing person with salt
	public Person(String userId, String password, String firstName,
			String lastName, byte[] salt, boolean isHashed)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this(salt);
		setUserId(userId);
		if (isHashed)
			setHashedPassword(password);
		else
			setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
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

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(password);
	}

	public void setPassword(String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	private String hashPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//System.out.println("hashing password: " + password);
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		//System.out.println("created digest instance? " + crypt != null);
		crypt.reset();
		//System.out.println("reset crypt");
		//System.out.println("updating crypt with salt: " + this.salt);
		crypt.update(this.salt);
		//System.out.println("updating crypt with password");
		crypt.update(password.getBytes("UTF-8"));
		//System.out.println("generating digest...");
		String digestedPassword = new BigInteger(1, crypt.digest()).toString();
		//System.out.println("generated digest: ");
		return digestedPassword;
	}

	public boolean isPasswordCorrect(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password));
	}

	public void setHashedPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public byte[] getSalt() {
		return this.salt;
	}
}
