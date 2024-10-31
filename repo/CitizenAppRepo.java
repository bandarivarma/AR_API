package in.anusha.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.anusha.entity.CitizenAppEntity;

public interface CitizenAppRepo  extends JpaRepository<CitizenAppEntity, Integer>{

	
	public CitizenAppEntity findByCitizenSsn(Long Ssn);
	
	public List<CitizenAppEntity> findBycreatedBy(Integer userId);
}
