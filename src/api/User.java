package api;


public class User {
	private String firstName;
	private String lastName;
	private String city;
	private String phone;
	private String ageGroup;
	
	private String id;
	private Status status;

	public User(String id, String firstName, String lastName, String city, String phone, String ageGroup, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.phone = phone;
		this.ageGroup = ageGroup;
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
