package in.anusha.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.anusha.request.CitizenApp;
import in.anusha.service.AppRegService;

@RestController
public class AppRestController {

	
	@Autowired
	private AppRegService service;
	
	@PostMapping("/app")
	public ResponseEntity<String> createApp(@RequestBody CitizenApp app) {
		
		String response = service.CreateCitizenApp(app);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/applications/{userId}/{userType}")
	public ResponseEntity<List<CitizenApp>> getApplications(@PathVariable("userId") Integer userId, @PathVariable("userType") String userType) {
	
		List<CitizenApp> apps =service.getApplications(userId, userType);
		
		return new ResponseEntity<>(apps, HttpStatus.OK);
	
	
}
}