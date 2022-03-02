package ca.sheridancollege.tapnioc.bootstrap;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.tapnioc.beans.Podcast;
import ca.sheridancollege.tapnioc.beans.StreamingService;
import ca.sheridancollege.tapnioc.repositories.PodcastRepository;
import ca.sheridancollege.tapnioc.repositories.StreamingServiceRepository;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner{
	
	private PodcastRepository podcastRepository;
	private StreamingServiceRepository streamingServiceRepository;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		StreamingService streamingService1 = StreamingService.builder()
				.name("Spotify")
				.link("https://www.spotify.com/us/")
				.imageLink("https://www.freepnglogos.com/uploads/spotify-logo-png/file-spotify-logo-png-4.png")
				.build();
		
		StreamingService streamingService2 = StreamingService.builder()
				.name("Apple Music")
				.link("https://music.apple.com/us/browse")
				.imageLink("https://upload.wikimedia.org/wikipedia/commons/9/9e/Itunes-music-app-icon.png")
				.build();
		StreamingService streamingService3 = StreamingService.builder()
				.name("Amazon Music")
				.link("https://music.amazon.ca/")
				.imageLink("https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Amazon_Music_logo.svg/2560px-Amazon_Music_logo.svg.png")
				.build();
		
		ArrayList<StreamingService> streamingServiceSet1= new ArrayList<StreamingService>();
		ArrayList<StreamingService> streamingServiceSet2= new ArrayList<StreamingService>();
		
		streamingServiceSet1.add(streamingService1);
		streamingServiceSet1.add(streamingService2);
		
		streamingServiceSet2.add(streamingService2);
		streamingServiceSet2.add(streamingService3);
		
		
		streamingServiceRepository.save(streamingService1);
		streamingServiceRepository.save(streamingService2);
		streamingServiceRepository.save(streamingService3);
		
		Podcast podcast1 = Podcast.builder()
				.title("Darknet Diaries")
				.description("This is a podcast about hackers, breaches, shadow government activity, hacktivism, cybercrime, and all the things that dwell on the hidden parts of the network. This is Darknet Diaries.")
				.link("https://open.spotify.com/show/4XPl3uEEL9hvqMkoZrzbx5")
				.rating(4.5)
				.streamingServiceList(streamingServiceSet1)
				.build();
		Podcast podcast2 = Podcast.builder()
				.title("Sweet Bobby")
				.description("From the makers of Finding Q, this is Sweet Bobby. A live investigation, in six parts, in search of one of the world's most sophisticated catfishers.")
				.link("https://podcasts.apple.com/us/podcast/sweet-bobby/id1590561275")
				.rating(4.0)
				.streamingServiceList(streamingServiceSet1)
				.build();
		Podcast podcast3 = Podcast.builder()
				.title("Science Vs")
				.description("Science Vs takes on fads, trends, and the opinionated mob to find out what’s fact, what’s not, and what’s somewhere in between.")
				.link("https://open.spotify.com/show/5lY4b5PGOvMuOYOjOVEcb9")
				.rating(4.0)
				.streamingServiceList(streamingServiceSet2)
				.build();
		
		Podcast podcast4 = Podcast.builder()
				.title("American Radical")
				.description("MSNBC digs into what brought one woman to the riot in the first place. Host and family friend Ayman Mohyeldin talks through how the late Rosanne Boyland got sucked into conspiracy theories that brought her to the riot that day.")
				.link("https://podcasts.apple.com/us/podcast/american-radical/id1596796171")
				.rating(4.5)
				.streamingServiceList(streamingServiceSet1)
				.build();
		Podcast podcast5 = Podcast.builder()
				.title("Dark House")
				.description("At the intersection of interior design, true crime and the paranormal lives this delicious new podcast hosted by House Beautiful editors Hadley Mendelsohn and Alyssa Fiorentino.")
				.link("https://podcasts.apple.com/us/podcast/dark-house/id1585723806")
				.rating(4.0)
				.streamingServiceList(streamingServiceSet1)
				.build();
		Podcast podcast6 = Podcast.builder()
				.title("70 Over 70")
				.description("With a wink at the eponymous \"30 Under 30\" and similar lists, this podcast interviews 70 fascinating people over the age of 70 about their lives.")
				.link("http://pineapple.fm/70-over-70")
				.rating(4.0)
				.streamingServiceList(streamingServiceSet2)
				.build();
		
	
		
		podcastRepository.save(podcast1);
		podcastRepository.save(podcast2);
		podcastRepository.save(podcast3);
		podcastRepository.save(podcast4);
		podcastRepository.save(podcast5);
		podcastRepository.save(podcast6);
		
		
		
		
		
		
	}


}
