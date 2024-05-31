package com.Ds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Ds.Entity.Employee;
import com.Ds.Service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	@GetMapping("/")
	public String home(Model m)
	{
		
		/*
		 * List<Employee> emp=service.getAllEmp(); m.addAttribute("emp",emp); return
		 * "index";
		 */
		
		return paginated(0, m);
	}
	@GetMapping("/addemp")
	public String addEmp()
	{
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute  Employee e, HttpSession session)
	{
		System.out.println(e);
		/* session.setAttribute("msg","Employee Data added Successfully...."); */
		
		service.addEmp(e);
		 session.setAttribute("msg","Employee Data added Successfully...."); 
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		
		Employee e=service.getEmpById(id);
		
		m.addAttribute("emp", e);
		
		return"edit";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e)
	{
		service.addEmp(e);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id)
	{
		service.deleteById(id);
		
		return"redirect:/";
	}
	@GetMapping("/page/{pageno}")
	public String paginated(@PathVariable int pageno,Model m)
	{
		Page <Employee> empList= service.getEmpByPaginated(pageno, 3);
		m.addAttribute("emp",empList);
		m.addAttribute("currentPages",pageno);
		m.addAttribute("totalPages",empList.getTotalPages());
		m.addAttribute("totalItems", empList.getTotalElements());
		
		return"index";
	}
	
	
}
