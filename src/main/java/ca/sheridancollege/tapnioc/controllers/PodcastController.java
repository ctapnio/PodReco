package ca.sheridancollege.tapnioc.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.tapnioc.beans.Podcast;
import ca.sheridancollege.tapnioc.beans.StreamingService;
import ca.sheridancollege.tapnioc.repositories.PodcastRepository;
import ca.sheridancollege.tapnioc.repositories.StreamingServiceRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PodcastController {

	private PodcastRepository podcastRepository;
	private StreamingServiceRepository streamingServiceRepository;
	
//	***CRUD METHODS

	@PostMapping("/addPodcast/{page}")
	public String addPodcast(@PathVariable ("page") Integer page, Model model, @ModelAttribute Podcast podcast, @RequestParam List<StreamingService> streamingServiceIdList) {
		podcast.setStreamingServiceList(streamingServiceIdList);
		podcastRepository.save(podcast);
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findAll(pageable);
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "redirect:/podcasts/0";
	}
	
	@GetMapping("/deletePodcast/{id}/{page}")
	public String deletePodcast(@PathVariable ("page") Integer page, Model model, @ModelAttribute Podcast podcast) {
		podcastRepository.delete(podcast);
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findAll(pageable);
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "redirect:/podcasts/0";
	}
	
	@PostMapping("/editPodcast/{id}/{page}")
	public String editAppointment(@PathVariable ("page") Integer page, Model model, @ModelAttribute Podcast podcast, @PathVariable Long id, @RequestParam List<StreamingService> streamingServiceIdList) {
		podcast.setId(id);
		podcast.setStreamingServiceList(streamingServiceIdList);
		podcastRepository.save(podcast);
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findAll(pageable);
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "redirect:/podcasts/0";
	}
	
//	***SORT METHODS
	
	@GetMapping("/findByOrderByTitleAsc/{page}")
	public String findByOrderByTitleAsc(@PathVariable ("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findByOrderByTitleAsc(pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";
	}
	
	@GetMapping("/findByOrderByTitleDesc/{page}")
	public String findByOrderByTitleDesc(@PathVariable ("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findByOrderByRatingDesc(pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";
	}
	
	@GetMapping("/findByOrderByRatingAsc/{page}")
	public String findByOrderByRatingAsc(@PathVariable ("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findByOrderByRatingAsc(pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";
	}
	
	@GetMapping("/findByOrderByRatingDesc/{page}")
	public String findByOrderByRatingDesc(@PathVariable ("page") Integer page, Model model) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findByOrderByTitleDesc(pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";
	}
	
//	***SEARCH METHODS
	
	@GetMapping("/findByDescriptionContains/{page}")
	public String findByDescriptionContains(@PathVariable ("page") Integer page, Model model, @Param("keyword") String keyword) {
		Pageable pageable = PageRequest.of(page, 1);
		Page<Podcast> podcasts = podcastRepository.findByDescriptionContains(keyword, pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		model.addAttribute("keyword", keyword);
		return "Podcast";
		
	}
	
	@GetMapping("/findByStreamingServiceListContains/{name}/{page}")
	public String findByStreamingServiceListContains(@PathVariable ("page") Integer page, Model model, @PathVariable String name) {
		Pageable pageable = PageRequest.of(page, 3);
		Page<Podcast> podcasts = podcastRepository.findByStreamingServiceListContains(streamingServiceRepository.findByNameIs(name), pageable);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("podcast", new Podcast());
		
		
		model.addAttribute("podcastList", podcasts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", podcasts.getTotalPages());
		return "Podcast";	
	}
	
	
	
}
