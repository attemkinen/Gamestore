package fi.backend.Gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.backend.Gamestore.domain.ReviewRepository;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewRepository rrepository;
	
	@RequestMapping(value= "/review", method=RequestMethod.GET)
	public String listReviews ( Model model ) {
		model.addAttribute("review", rrepository.findAll());
		return "reviewlist";
		
	}

}
