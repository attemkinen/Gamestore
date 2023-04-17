package fi.backend.Gamestore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.backend.Gamestore.domain.Category;
import fi.backend.Gamestore.domain.CategoryRepository;


@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository crepository;

	// list all categories REST
	@RequestMapping(value = "/categoryjson", method = RequestMethod.GET)
	public @ResponseBody List<Category> gamelistRest() {
		return (List<Category>) crepository.findAll();
	}

	// Find by id REST
	@RequestMapping(value = "/categoryjson/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> findCategoriesRest(@PathVariable("id") Long categoryId) {
		return crepository.findById(categoryId);
	}

	// List all existing categories
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String listCategories(Model model) {
		model.addAttribute("categories", crepository.findAll());
		return "categorylist";
	}
	// add new categories
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String addCategory(Model model) {
		model.addAttribute("categories", new Category());
		return "categoryform";
	}

	// save new categories
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {
		crepository.save(category);
		return "redirect:/categories";
	}

	// delete category using id
	@RequestMapping(value = "/deletecategory/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
		crepository.deleteById(categoryId);
		return "redirect:../categories";
	}

}
