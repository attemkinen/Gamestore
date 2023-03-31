package fi.backend.Gamestore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.backend.Gamestore.domain.Game;
import fi.backend.Gamestore.domain.GameRepository;

@CrossOrigin
@Controller
public class GameController {
	@Autowired 
	private GameRepository grepository;
	
	@RequestMapping (value= "/games", method = RequestMethod.GET)
		public String getGames (Model model) {
		List <Game> games =(List<Game>)grepository.findAll();
		model.addAttribute("games", games);
		return "gamelist";
		}
	
	@RequestMapping (value="/newgame", method = RequestMethod.GET)
	public String GetnewGameForm ( Model model) {model.addAttribute("game", new Game());
	return "gameform";
	}
	
	@RequestMapping (value = "/savegame", method = RequestMethod.POST)
	public String saveGame ( @ModelAttribute Game game) {
		grepository.save(game);
		return "redirect:/games";
	}
	
	@RequestMapping (value= "/deletegame/{id}", method= RequestMethod.GET)
	public String deleteGame (@PathVariable("id") Long id, Model model) {
		grepository.deleteById(id);
		return  "redirect:../games";
		
	}
		
	
}
