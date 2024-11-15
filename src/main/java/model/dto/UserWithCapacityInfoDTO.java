package model.dto;

public class UserWithCapacityInfoDTO {
	private String username;
	private String fullName;
	private String status;
	private String role;
	private double totalCapacityUsed;
	private double totalCapacity;
	private double percentCapacityUsed;
	
	public UserWithCapacityInfoDTO(String username, String fullName, String status, String role,
			double totalCapacityUsed, double totalCapacity, double percentCapacityUsed) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.status = status;
		this.role = role;
		this.totalCapacityUsed = totalCapacityUsed;
		this.totalCapacity = totalCapacity;
		this.percentCapacityUsed = percentCapacityUsed;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getTotalCapacityUsed() {
		return totalCapacityUsed;
	}

	public void setTotalCapacityUsed(double totalCapacityUsed) {
		this.totalCapacityUsed = totalCapacityUsed;
	}

	public double getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(double totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public double getPercentCapacityUsed() {
		return percentCapacityUsed;
	}

	public void setPercentCapacityUsed(double percentCapacityUsed) {
		this.percentCapacityUsed = percentCapacityUsed;
	}
	
	
	
}
