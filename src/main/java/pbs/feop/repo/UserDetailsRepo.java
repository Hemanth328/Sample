package pbs.feop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pbs.feop.entity.UserDetails;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

	public UserDetails findByUserMail(String mail);
	
	public UserDetails findByUserMailAndUserPazz(String email, String pizzward);
}
