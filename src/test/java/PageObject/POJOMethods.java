package PageObject;

import java.util.List;

import MethodsHandsOn.SubCourses;
import MethodsHandsOn.miniSubCourses;
import MethodsHandsOn.miniSubCoursesMobile;
import MethodsHandsOn.miniSubCoursesapi;
import MethodsHandsOn.sublocation;

public class POJOMethods {
	
	public class getCource {
		private String url;
		private String services;
		private String expertise;
		private SubCourses courses;
		private String instructor;
		private String linkedIn;
		
		
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getServices() {
			return services;
		}
		public void setServices(String services) {
			this.services = services;
		}
		public String getExpertise() {
			return expertise;
		}
		public void setExpertise(String expertise) {
			this.expertise = expertise;
		}
		public SubCourses getCourses() {
			return courses;
		}
		public void setCourses(SubCourses courses) {
			this.courses = courses;
		}
		public String getInstructor() {
			return instructor;
		}
		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}
		public String getLinkedIn() {
			return linkedIn;
		}
		public void setLinkedIn(String linkedIn) {
			this.linkedIn = linkedIn;
		}
		
	}
	
	public class LoginCLassPOJO {
		private String userEmail;
		private String userPassword;
		
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
	}
	
	public class LoginResponsePayload {
		private String token;
		private String userId;
		private String message;
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}
	
	public class miniSubCourses {
		private String courseTitle;
		private int price;
		
		public String getCourseTitle() {
			return courseTitle;
		}
		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
	}
	
	public class miniSubCoursesapi {
		private String courseTitle;
		private int price;
		
		public String getCourseTitle() {
			return courseTitle;
		}
		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
	}

	public class miniSubCoursesMobile {
		private String courseTitle;
		private int price;
		
		public String getCourseTitle() {
			return courseTitle;
		}
		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
	}

	public class ordersEcom {
		String country;
		String productOrderedId;
		
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getProductOrderedId() {
			return productOrderedId;
		}
		public void setProductOrderedId(String productOrderedId) {
			this.productOrderedId = productOrderedId;
		}
		
	}

	public class SerialisePOJO {
		private sublocation location;
		private int accuracy;
		private String name;
		private String phone_number;
		private String address;
		private List<String> types;
		private String website;
		private String language;
		
		
		public sublocation getLocation() {
			return location;
		}
		public void setLocation(sublocation location) {
			this.location = location;
		}
		public int getAccuracy() {
			return accuracy;
		}
		public void setAccuracy(int accuracy) {
			this.accuracy = accuracy;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public List<String> getTypes() {
			return types;
		}
		public void setTypes(List<String> types) {
			this.types = types;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
	}
	
	public class SubCourses {
		private List<miniSubCourses> webAutomation;
		private List<miniSubCoursesapi> api;
		private List<miniSubCoursesMobile> mobile;

		
		public List<miniSubCourses> getWebAutomation() {
			return webAutomation;
		}
		public void setWebAutomation(List<miniSubCourses> webAutomation) {
			this.webAutomation = webAutomation;
		}
		public List<miniSubCoursesapi> getApi() {
			return api;
		}
		public void setApi(List<miniSubCoursesapi> api) {
			this.api = api;
		}
		public List<miniSubCoursesMobile> getMobile() {
			return mobile;
		}
		public void setMobile(List<miniSubCoursesMobile> mobile) {
			this.mobile = mobile;
		}

	}
	
	public class sublocation {
		private double lat;
		private double lng;

		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
	}


}
