package pbs.feop.service;

import pbs.feop.entity.StudentsEnquiry;

public interface IStudentEnquiry {

	public boolean registerStudent(StudentsEnquiry enquiry);
	
	public boolean updateStudent(StudentsEnquiry enquiry);
}
