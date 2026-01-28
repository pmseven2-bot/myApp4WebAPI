package api;

public enum Status {
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	PENDING("Pending"),
	VISITOR("Visitor"),
	MEMBER("Member"),
	OTHER("Other");
	
	private final String label;
	
	private Status (String label) {
		this.label = label;
	}
	

}
