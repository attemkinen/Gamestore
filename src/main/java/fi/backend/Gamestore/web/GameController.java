package fi.backend.Gamestore.web;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fi.backend.Gamestore.domain.CategoryRepository;
import fi.backend.Gamestore.domain.Game;
import fi.backend.Gamestore.domain.GameRepository;
import fi.backend.Gamestore.domain.ShoppingCart;

@CrossOrigin
@Controller
@SessionAttributes("cart")

public class GameController {
	@Autowired
	private GameRepository grepository;
	@Autowired
	private CategoryRepository crepository;

	@RequestMapping("/index")
	public String indexController() {
		return "index";
	}

	@ModelAttribute("cart")
	public ShoppingCart getCart() {
		return new ShoppingCart();

	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String showCart() {
		return "cart";
	}

	// Poista yksi peli ostoskorista
	@RequestMapping(value = "/removefromcart/{id}", method = RequestMethod.GET)
	public String removeFromCart(@PathVariable("id") Long id, @ModelAttribute("cart") ShoppingCart cart) {
		cart.removeGame(id);
		return "redirect:/cart";
	}

	// Päivitä pelin määrä ostoskorissa
	@RequestMapping(value = "/updatequantity", method = RequestMethod.POST)
	public String updateQuantity(@RequestParam Long gameId, @RequestParam int quantity,
			@ModelAttribute("cart") ShoppingCart cart) {
		cart.updateQuantity(gameId, quantity);
		return "redirect:/cart";
	}

	@RequestMapping(value = "/addtocart/{id}", method = RequestMethod.GET)
	public String addToCart(@PathVariable("id") Long id, @ModelAttribute("cart") ShoppingCart cart) {
		Optional<Game> game = grepository.findById(id);
		if (game.isPresent()) {
			cart.addGame(game.get());
		}
		return "redirect:/games";
	}

	// list all games REST
	@RequestMapping(value = "/gamesjson", method = RequestMethod.GET)
	public @ResponseBody List<Game> gamelistRest() {
		return (List<Game>) grepository.findAll();
	}

	// Find by id REST
	@RequestMapping(value = "/gamesjson/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Game> findGamesRest(@PathVariable("id") Long id) {
		return grepository.findById(id);
	}

	// save game REST
	@RequestMapping(value = "/savegamejson", method = RequestMethod.POST)
	public @ResponseBody Game saveGameRest(@RequestBody Game game) {
		return grepository.save(game);
	}

	// return all listed games
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public String getGames(Model model, @ModelAttribute("cart") ShoppingCart cart) {
		List<Game> games = (List<Game>) grepository.findAll();
		model.addAttribute("games", games);
		model.addAttribute("cartSize", cart.getTotalQuantity());
		return "gamelist";
	}

	// add new game
	@RequestMapping(value = "/newgame", method = RequestMethod.GET)
	public String GetnewGameForm(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("categories", crepository.findAll());
		return "gameform";
	}

	// save game
	@RequestMapping(value = "/savegame", method = RequestMethod.POST)
	public String saveGame(@ModelAttribute Game game) {
		grepository.save(game);
		return "redirect:/games";
	}

	// delete game by id
	@RequestMapping(value = "/deletegame/{id}", method = RequestMethod.GET)
	public String deleteGame(@PathVariable("id") Long id, Model model) {
		grepository.deleteById(id);
		return "redirect:../games";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editSpot(@PathVariable("id") Long id, Model model, Game game) {
		model.addAttribute("game", grepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		model.addAttribute("gameId", id);
		return "editgame";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateGame(@PathVariable("id") Long id, Model model, Game game) {
		grepository.save(game);
		return "redirect:../games";
	}

}
