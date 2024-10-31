package in.anusha.service;

import java.util.List;

import in.anusha.request.CitizenApp;

public interface AppRegService {

	
	public String CreateCitizenApp(CitizenApp app);
	
	public List<CitizenApp> getApplications(Integer userId, String userType);
	
	
}
