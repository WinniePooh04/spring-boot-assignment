package com.spring.exercise.spring.boot.assignment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.exercise.spring.boot.assignment2.model.Employee;
import com.spring.exercise.spring.boot.assignment2.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		List<Employee> employeeList = employeeService.getAllEmplooyee();
		model.addAttribute("employeeList", employeeList);
		return "index";
	}

	@RequestMapping(value = { "/searchProcess" }, method = RequestMethod.POST)
	public String searchEmployee(@RequestParam("search") String search, Model model) {

		List<Employee> employeeList = employeeService.searchEmployee(search);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("search", search);
		return "index";
	}

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
	public String addEmployee(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee";
	}

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.POST)
	public String saveEmployee(Model model, @ModelAttribute("employee") Employee employee,
			RedirectAttributes redirAttrs) {

		employeeService.insertEmployee(employee);
		redirAttrs.addFlashAttribute("successMsg", "New employee successfully created!");
		return "redirect:/index";
	}

	@RequestMapping(value = { "/editEmployee/id/{id}" }, method = RequestMethod.GET)
	public String editEmployee(Model model, @PathVariable("id") Integer id) {

		Employee employee = employeeService.getEmployeeDetail(id);
		model.addAttribute("employee", employee);
		return "employeeEdit";
	}

	@RequestMapping(value = { "/editEmployee" }, method = RequestMethod.POST)
	public String editEmployeeProcess(Model model, @ModelAttribute("employee") Employee employee,
			RedirectAttributes redirAttrs) {

		employeeService.updateEmployee(employee);
		redirAttrs.addFlashAttribute("successMsg", "Employee info is successfully updated!");
		return "redirect:/index";
	}

	@RequestMapping(value = { "/deleteEmployee/id/{id}" }, method = RequestMethod.GET)
	public String deleteEmployee(Model model, @PathVariable("id") Integer id, RedirectAttributes redirAttrs) {

		employeeService.deleteEmployee(id);
		redirAttrs.addFlashAttribute("successMsg", "Employee is successfully deleted!");
		return "redirect:/index";
	}
}
