package pbs.feop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FEOP_ENQY_STATUS")
public class EnquiryStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqyStatusId;
	private String enqyStatuses;
}
