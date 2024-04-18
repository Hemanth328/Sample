package pbs.feop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pbs.feop.entity.StudentsEnquiry;

public interface StudentEnquiryRepo extends JpaRepository<StudentsEnquiry, Integer> {

}
