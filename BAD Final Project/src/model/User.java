package model;

public class User extends Model {
	private String id, username, email, password, gender, country, phoneNumber, role;
	private Integer age;

	public User() {
	}

	public User(String username, String email, String password, String gender, String country, String phoneNumber,
			String role, Integer age) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.age = age;
	}

	@Override
	public String getIdPrefix() {
		return "US";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserID() {
		return this.id;
	}

	public void setUserID(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
