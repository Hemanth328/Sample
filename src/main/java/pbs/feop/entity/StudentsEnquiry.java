package pbs.feop.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FEOP_STUDENTS_ENQY")
public class StudentsEnquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stdId;
	private String stdName;
	private Integer StdPhnNum;
	private String stdClssMode;
	private String stdCourse;
	private String stdJngStatus;
	private LocalDate enqyCreatedDate;
	private LocalDate enqyUpdatedDate;
	
	@ManyToOne(targetEntity = UserDetails.class, cascade = CascadeType.ALL)
	private UserDetails userDetails;
	
}
