package ca.sheridancollege.tapnioc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.tapnioc.beans.Podcast;
import ca.sheridancollege.tapnioc.beans.StreamingService;

public interface PodcastRepository extends JpaRepository<Podcast, Long>{

	Page<Podcast> findByOrderByTitleAsc(Pageable page);
	Page<Podcast> findByOrderByTitleDesc(Pageable page);
	Page<Podcast> findByOrderByRatingAsc(Pageable page);
	Page<Podcast> findByOrderByRatingDesc(Pageable page);
	Page<Podcast> findByDescriptionContains(String keyword, Pageable page);
	Page<Podcast> findByStreamingServiceListContains(StreamingService streamingServiceList, Pageable page);
	List<Podcast> findByStreamingServiceListContains(StreamingService streamingServiceList);
}
