package ua.goit.finall.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static ua.goit.finall.utils.Utils.getLoggedinUserName;

@Controller
public class WelcomeController {

	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String showWelcomePage(Map<String, Object> model) {
		model.put("name", getLoggedinUserName());
		return "index";
	}

	@RequestMapping(value = {"/error403"}, method = RequestMethod.GET)
	public String showError403Page() {
		return "error403";
	}
}
