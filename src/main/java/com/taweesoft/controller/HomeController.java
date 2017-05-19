package com.taweesoft.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taweesoft.dao.PatternDAOImpl;
import com.taweesoft.model.Pattern;

/**
 * Controller
 * Creating path for being requested, including handle the request from front-end
 * @author Taweerat Chaiman 5710546259
 *
 */
@Controller
public class HomeController {
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private PatternDAOImpl patternDAOImpl = (PatternDAOImpl) context.getBean("patternDAOImpl");
	
	
	/**
	 * route /
	 * Index route, serving index page.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	/**
	 * route /show
	 * Showing all of patterns from database.
	 * @param model
	 * @return
	 */
	@RequestMapping("/show")
	public String show(Model model) {
		List<Pattern> patterns = patternDAOImpl.listPatterns();
		model.addAttribute("patterns", patterns);
		return "show";
	}
	
	/**
	 * route /add
	 * Serving a page for adding new pattern.
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("patternForm", new Pattern());
		return "add";
	}
	
	/**
	 * route /save (POST)
	 * Save the pattern into database.
	 * Won't serve the static file.
	 * @param pattern as new pattern from form.
	 * @param result
	 * @param model
	 * @return next route.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("patternForm") Pattern pattern, BindingResult result, Model model) {
		System.out.println(String.format("Create Pattern: [id: %d] [name:%s] [group: %s]", pattern.getId(), pattern.getName(), pattern.getGroup()));
		patternDAOImpl.create(pattern);
		return "redirect:/show";
	}
	
	/**
	 * route /update/{id} 
	 * Serves a page for updating pattern.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		Pattern pattern = patternDAOImpl.getPattern(id);
		System.out.println(String.format("Listing Pattern: [id: %d] [name:%s] [group: %s]", pattern.getId(), pattern.getName(), pattern.getGroup()));
		model.addAttribute("patternForm", new Pattern());
		model.addAttribute("pattern", pattern);
		return "update";
	}
	
	/**
	 * route /update/{id} (POST)
	 * Updating the current pattern.
	 * @param id
	 * @param pattern
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, @ModelAttribute("patternForm") Pattern pattern, BindingResult result, Model model) {
		System.out.println(String.format("Update Pattern: [id: %d] [name:%s] [group: %s]", pattern.getId(), pattern.getName(), pattern.getGroup()));
		patternDAOImpl.update(id, pattern);
		return "redirect:/show";
	}
	
	/**
	 * route /delete/{id}
	 * Deleting pattern by id.
	 * and serve the show page.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model model) {
		Pattern pattern = patternDAOImpl.getPattern(id);
		System.out.println(String.format("Delete Pattern: [id: %d] [name:%s] [group: %s]", pattern.getId(), pattern.getName(), pattern.getGroup()));
		patternDAOImpl.delete(id);
		return "redirect:/show";
	}
}
