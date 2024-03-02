package org.formation.repositories;

import java.util.Optional;

import org.formation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByLogin(String login);
	
	//Member findMemberByLogin(String login);

}
