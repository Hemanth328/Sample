package pbs.feop.service;

import java.util.List;

import pbs.feop.entity.StudentsEnquiry;
import pbs.feop.entity.UserDetails;
import pbs.feop.request.SearchRequest;

public interface IViewEnqueries {

	public List<StudentsEnquiry> viewAllEnquiries(SearchRequest search, UserDetails userId);
}
