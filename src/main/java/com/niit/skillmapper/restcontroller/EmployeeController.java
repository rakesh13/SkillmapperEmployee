package com.niit.skillmapper.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService  empService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp)
	{
		empService.saveEmployee(emp);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp)
	{
		empService.updateEmployee(emp);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delete/{empId}",method=RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("empId") int employeeId)
	{
		if(empService.deleteEmployee(employeeId))
		{
			return new ResponseEntity("Employee with "+employeeId+" deleted Successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Error While Deleting",HttpStatus.OK);
		}
		
	}
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		List<Employee> allEmployees=empService.getAllEmployees();
		if(allEmployees!=null)
		{
			return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
}
