package ca.sheridancollege.tapnioc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.tapnioc.beans.StreamingService;

public interface StreamingServiceRepository extends JpaRepository<StreamingService, Long>{

	StreamingService findByNameIs(String name);
	List<StreamingService>findByOrderByNameAsc ();
	List<StreamingService>findByOrderByNameDesc ();
}
