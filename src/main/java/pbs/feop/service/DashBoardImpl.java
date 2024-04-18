package pbs.feop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pbs.feop.entity.StudentsEnquiry;
import pbs.feop.entity.UserDetails;
import pbs.feop.repo.UserDetailsRepo;
import pbs.feop.request.DashBoardResponse;

@Service
@AllArgsConstructor
public class DashBoardImpl implements IDashBoard {
	
	private UserDetailsRepo userDetailsRepo;

	@Override
	public DashBoardResponse fetchDashBoardResponse(Integer usedId) {
		
		Optional<UserDetails> usersDetails =  userDetailsRepo.findById(usedId);
		
		DashBoardResponse dashboard = new DashBoardResponse();
		
		if(usersDetails.isPresent()) {
			
			UserDetails usDetails = usersDetails.get();
			
			List<StudentsEnquiry> stdEnquiry = usDetails.getStudentsEnquiry();
			
			Integer totalCnt = stdEnquiry.size();
			
			Integer enrolledCnt = stdEnquiry.stream().filter(s -> s.getStdJngStatus()
					.equalsIgnoreCase("ENROLLED")).collect(Collectors.toList()).size();
			
			Integer lostCnt = stdEnquiry.stream().filter(s -> s.getStdJngStatus()
					.equalsIgnoreCase("LOST")).collect(Collectors.toList()).size();
			
			dashboard.setEnrolled(enrolledCnt);
			dashboard.setLost(lostCnt);
			dashboard.setTotalcount(totalCnt);
			
		}
		
		
		
		return dashboard;
	}
}
