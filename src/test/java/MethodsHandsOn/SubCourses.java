package MethodsHandsOn;

import java.util.List;

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
