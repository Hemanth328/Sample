package pbs.feop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DashBoardController {
	
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String getDashBoradPage(Model model) {
		
		System.out.println("Dash Board Page rendered");
		
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage() {
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiriesPage() {
		return "view-enquiries";
	}

}
