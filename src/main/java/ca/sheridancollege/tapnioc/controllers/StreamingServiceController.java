package ca.sheridancollege.tapnioc.controllers;


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
public class StreamingServiceController {

	private StreamingServiceRepository streamingServiceRepository;
	private PodcastRepository podcastRepository;
	
	@PostMapping("addStreamingService")
	public String addStreamingService(Model model, @ModelAttribute StreamingService streamingService) {
		streamingServiceRepository.save(streamingService);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("streamingService", new StreamingService());
		return "redirect:/streamingService";
	}

	@GetMapping("/deleteStreamingService/{id}")
	public String deleteStreamingService(Model model, @ModelAttribute StreamingService streamingService) {
		for (Podcast podcast : podcastRepository.findByStreamingServiceListContains(streamingService)) {
			podcast.getStreamingServiceList().remove(streamingServiceRepository.getById(streamingService.getId()));
			podcastRepository.save(podcast);
			
		}
		try {
			streamingServiceRepository.delete(streamingService);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("podcastList", podcastRepository.findAll());
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("streamingService", new StreamingService());
		return "redirect:/streamingService";
	}
	
	@PostMapping("/editStreamingService/{id}")
	public String editStreamingService(Model model, @ModelAttribute StreamingService streamingService, @PathVariable Long id, @RequestParam String name, @RequestParam String link, @RequestParam String imageLink) {
		streamingService.setId(id);
		streamingServiceRepository.save(streamingService);
		model.addAttribute("streamingServiceList", streamingServiceRepository.findAll());
		model.addAttribute("streamingService", new StreamingService());
		return "redirect:/streamingService";
	}
	
	@GetMapping("/findByOrderByNameAsc")
	public String findByOrderByNameAsc(Model model) {
		model.addAttribute("streamingServiceList", streamingServiceRepository.findByOrderByNameAsc());
		model.addAttribute("streamingService", new StreamingService());
		return "StreamingService";
	}
	@GetMapping("/findByOrderByNameDesc")
	public String findByOrderByNameDesc(Model model) {
		model.addAttribute("streamingServiceList", streamingServiceRepository.findByOrderByNameDesc());
		model.addAttribute("streamingService", new StreamingService());
		return "StreamingService";
	}
	
}
