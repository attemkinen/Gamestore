package fi.backend.Gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.backend.Gamestore.domain.Category;
import fi.backend.Gamestore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository crepository;
	
	//List all existing categories
	@RequestMapping( value ="/categories", method = RequestMethod.GET)
		public String listCategories (Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
		}
		// add new categories
	@RequestMapping (value = "/addcategory", method = RequestMethod.GET)
	public String addCategory (Model model) {
		model.addAttribute("categories", new Category());
		return "categoryform";
	}
	
	@RequestMapping (value ="/savecategory", method = RequestMethod.POST)
	public String saveCategory (@ModelAttribute Category category) {
		crepository.save(category);
		return "redirect:/categorylist";
	}
	
}
