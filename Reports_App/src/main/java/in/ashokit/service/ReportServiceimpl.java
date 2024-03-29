package in.ashokit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;
@Service
public class ReportServiceimpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;
	@Override
	public List<String> getPlanNames() {
	return planRepo.getPlanNames();
	
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity=new CitizenPlan();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())){
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())){
			entity.setGender(request.getGender());;
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())){
			entity.setPlanStatus(request.getPlanStatus());;
		}
		if (null !=request.getStartDate()) {
			String startDate=request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			LocalDate localDate=LocalDate.parse(startDate,formatter);
			
			
			entity.setPlanStartDate(localDate);
			
		}
		
		
		//request is the source object and entity is the target object.
		Example<CitizenPlan> filterData = Example.of(entity);
		
		return planRepo.findAll(filterData);
		
		
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
