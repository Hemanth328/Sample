package pbs.feop.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FEOP_USER")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String phoneNumber;
	private String userMail;
	private String userPazz;
	private String userAccStatus;
	
	@OneToMany(targetEntity = StudentsEnquiry.class, cascade = CascadeType.ALL, mappedBy = "userDetails")
	private List<StudentsEnquiry> studentsEnquiry;
}
