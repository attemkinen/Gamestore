package fi.backend.Gamestore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.backend.Gamestore.domain.CategoryRepository;
import fi.backend.Gamestore.domain.Game;
import fi.backend.Gamestore.domain.GameRepository;
import fi.backend.Gamestore.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	private GameRepository grepository;

	@Autowired
	CategoryRepository crepository;

	@Test
	// testing findByName() metodin toimivuutta
	public void FindGameByName() {
		List<Game> games = grepository.findByName("Hogwarts Legacy");
		assertThat(games).hasSize(1);
		assertThat(games.get(0).getConsole()).isEqualTo("PS5");
	}

	@Test
	// testing findByName() metodin toimivuutta
	public void findCategoryByName() {
		List<Category> categories = crepository.findByName("Football");
		assertThat(categories).hasSize(1);

	}

	@Test
	// testing create new category
	public void createNewCategory() {
		Category category = new Category("Gaming");
		crepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test
	// testing create new game
	public void createNewGame() {
		Category c8 = new Category("testaus");
		Game game = new Game("testi", "testaus", "testaillaan", 10, 11, 15.5, c8);
		grepository.save(game);
		assertThat(game.getId()).isNotNull();
	}
	
	
	
}
