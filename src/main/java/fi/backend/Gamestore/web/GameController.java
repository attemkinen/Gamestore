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
import org.springframework.web.bind.annotation.ResponseBody;

import fi.backend.Gamestore.domain.CategoryRepository;
import fi.backend.Gamestore.domain.Game;
import fi.backend.Gamestore.domain.GameRepository;



@CrossOrigin
@Controller
public class GameController {
	@Autowired
	private GameRepository grepository;
	@Autowired
	private CategoryRepository crepository;

	@RequestMapping("/index")
	public String indexController() {
		return "index";
	}
	 // list all games REST
	@RequestMapping (value = "/gamesjson", method= RequestMethod.GET)
	public @ResponseBody List <Game> gamelistRest(){
		return (List <Game>) grepository.findAll();
	}
	
	// Find by id REST
	@RequestMapping (value ="/gamesjson/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional <Game> findGamesRest(@PathVariable("id") Long id){
		return grepository.findById(id);
	}
	// save game REST
	@RequestMapping (value = "/savegamejson", method= RequestMethod.POST)
	public @ResponseBody Game saveGameRest(@RequestBody Game game) {
		return grepository.save(game);
	}

	
	// return all listed games
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public String getGames(Model model) {
		List<Game> games = (List<Game>) grepository.findAll();
		model.addAttribute("games", games);
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
