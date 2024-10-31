package in.anusha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import in.anusha.entity.CitizenAppEntity;
import in.anusha.repo.CitizenAppRepo;
import in.anusha.request.CitizenApp;

public class AppRegServiceImpl implements AppRegService {

	private String SSA_API_URL="http://192.168.3.1:8080/ssn/{ssn}";
	
	@Autowired
	private CitizenAppRepo appRepo;
	
	
	@Override
	public String CreateCitizenApp(CitizenApp app) {

		Long citizenSsn = app.getCitizenSsn();
		
		CitizenAppEntity appEntity = appRepo.findByCitizenSsn(citizenSsn);
		
		if(appEntity !=null) {
			return "Duplicate Application";
		}
		
		RestTemplate rt  = new RestTemplate();
		ResponseEntity<String> forEntity = rt.getForEntity(SSA_API_URL, String.class, citizenSsn);
		
		String body  = forEntity.getBody();
		
		if(body.equals("Rhode Island")) {
		
			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			CitizenAppEntity save = appRepo.save(entity);
			return "Application created with case Number-" + save.getCaseNum();
		}
		return "Invalid SSN";
	}

	@Override
	public List<CitizenApp> getApplications(Integer userId, String userType) {

		List<CitizenAppEntity> entities = null;
		List<CitizenApp> apps = new ArrayList<>();
		
		if("admin".equals(userType)) {
			entities= appRepo.findAll();			
		}else {
			appRepo.findBycreatedBy(userId);
		}
		for(CitizenAppEntity entity : entities) {
			
			CitizenApp app = new CitizenApp();
			
			BeanUtils.copyProperties(entity, app);
		
			apps.add(app);
		}
		return apps;
	}

}
