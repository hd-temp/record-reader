package com.ai.reader.common;

/**
 * Records information about users
 */
public class UserRecord {

	// We don't construct UserRecords ourselves but if needed we can use static builder pattern

	private String guid;
	private boolean isActive;
	private String balance;
	private int age;
	private String eyeColor;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String registered;
	private Friend[] friends;
	private String greeting;
	private String favoriteFruit;

	// Getters

	public String getGuid() {
		return guid;
	}
	public boolean isActive() {
		return isActive;
	}
	public String getBalance() {
		return balance;
	}
	public int getAge() {
		return age;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getRegistered() {
		return registered;
	}
	public Friend[] getFriends() {
		return friends;
	}
	public String getGreeting() {
		return greeting;
	}
	public String getFavoriteFruit() {
		return favoriteFruit;
	}

	// Setters

	public void setGuid(String guid) {
		this.guid = guid;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setRegistered(String registered) {
		this.registered = registered;
	}
	public void setFriends(Friend[] friends) {
		this.friends = friends;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public void setFavoriteFruit(String favoriteFruit) {
		this.favoriteFruit = favoriteFruit;
	}

	@Override
	public String toString() {
		// Very basic auto-generated toString for pretty-printing
		return "UserRecord [" + guid + "]";
	}

	/**
	 * A friend record defined only by a name
	 */
	public static class Friend {
		private String name;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
