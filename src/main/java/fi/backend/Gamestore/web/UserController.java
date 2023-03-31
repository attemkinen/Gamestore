package fi.backend.Gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.backend.Gamestore.domain.SignupForm;
import fi.backend.Gamestore.domain.User;
import fi.backend.Gamestore.domain.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserRepository urepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping (value = "/signup")
	public String addUser (Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
		}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupform, BindingResult bindingresult) {
		if (!bindingresult.hasErrors()) { // validation errors
			if (signupform.getPassword().equals(signupform.getPasswordCheck())) { // check password match
				String pwd = signupform.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupform.getUsername());
				newUser.setRole("USER");
				if (urepository.findByUsername(signupform.getUsername()) == null) { // Check if user exists
					urepository.save(newUser);
				} else {
					bindingresult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingresult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";

	}

}
