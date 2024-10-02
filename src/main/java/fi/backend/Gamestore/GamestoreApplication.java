package fi.backend.Gamestore;

import org.slf4j.Logger;



import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.backend.Gamestore.domain.Category;
import fi.backend.Gamestore.domain.CategoryRepository;
import fi.backend.Gamestore.domain.Game;
import fi.backend.Gamestore.domain.GameRepository;
import fi.backend.Gamestore.domain.User;
import fi.backend.Gamestore.domain.UserRepository;



@SpringBootApplication
public class GamestoreApplication {
	private static final Logger log = LoggerFactory.getLogger(GamestoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GamestoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo (GameRepository grepository, UserRepository urepository, CategoryRepository crepository) {
		return (args) -> {
			
		
			
			// adding new categories
			Category c1 = new Category ("Fantasy");
			Category c2 = new Category ("Football");
			Category c3 = new Category ("Ice Hockey");
			Category c4 = new Category ("Dystopian");
			Category c5 = new Category ("Scifi");
			Category c6 = new Category ("Simulation");
			Category c7 = new Category ("Kids");
			
			//saving added categories
			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);
			crepository.save(c4);
			crepository.save(c5);
			crepository.save(c6);
			crepository.save(c7);
			
			
			
			
			log.info("saving games to database");
			grepository.save(new Game(
					"Hogwarts Legacy",
					"PS5",
					"Discover wizarding  world in whole new way. This open mapped game gives you opportunity to make your own name in Hogwards",
					16,
					2022,
					59.9,
					c1));
			grepository.save(new Game(
					"Star Wars Jedi Survivor",
					"Xbox1","The story of Cal Kestis continues in Star Wars Jedi: Survivor, a third person galaxy-spanning action-adventure",
					18,
					2023,
					80.0,
					c5));
			
			grepository.save(new Game (
					"Resident Evil 4",
					"PC",
					"Resident Evil™ 4 joins Leon S. Kennedy six years after his hellish experiences in the biological disaster of Raccoon City. His unmatched resolve caused him to be recruited as an agent reporting directly to the president of the United States." ,
					18,
					2023,
					59.9,
					c4));
			grepository.save(new Game(
					"Fifa22",
					"PS4",
					" Elevate your game to a new level, score points, attack plays, and break down the defense in this highly-anticipated sports video game.",
					3,
					2021,
					28.0,
					c2));
			
			log.info("fetching games");
			for (Game game: grepository.findAll()) {
				log.info(game.toString());
			}
			// creating new users
			User user1 = new User ("admin", "$2a$10$3j9DzR9l8qDIeccPMJEHwu9Un1bIcnh6zQa0BwGBimxEfVIhYTlnK", "ADMIN");
			User user2 = new User ("user", "$2a$10$nGe2DACbeS99PzZzjnWUy.hxj79JUlz98C3YvzdfvlO/eSGiSwJJC", "USER");
			User user3 = new User("atte", "$2y$10$4dbRy6JeGpWGLBiuCLIDGejOb5KuTwyaP6SIvuUrfBplJzcYXRo5C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			
		};
		
			
		
	

}
	}
