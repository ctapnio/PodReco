package ca.sheridancollege.tapnioc.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.tapnioc.beans.Podcast;
import ca.sheridancollege.tapnioc.beans.StreamingService;
import ca.sheridancollege.tapnioc.repositories.PodcastRepository;
import ca.sheridancollege.tapnioc.repositories.StreamingServiceRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private PodcastRepository podcastRepository;
	private StreamingServiceRepository streamingServiceRepository;
	
	@GetMapping("/podcasts/{page}")
	public String podcasts(@PathVariable ("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findAll(pageable);
	
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		model.addAttribute("streamingService", new StreamingService());
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";
	}
	@GetMapping("/streamingService")
	public String streamingService(Model model) {
	
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("streamingService", new StreamingService());
		return "StreamingService";
	}
}
