package com.example.expensetracerapi.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracerapi.entity.Expense;
import com.example.expensetracerapi.service.ExpenseService;

@RestController
@CrossOrigin(origins="*")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/createexpense")
	public Expense createExpense(@Valid @RequestBody Expense expense){
		return expenseService.createNewExpense(expense);
	}
	
	@GetMapping("/allexpenses")
	public List<Expense> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}
	
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}
	
	@DeleteMapping("/deleteexpense")
	public void deleteExpenseById(@RequestParam Long id) { 
		expenseService.deleteExpenseById(id);
	}
	
	@PutMapping("/updateexpense/{Id}")
	public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long Id) {
		return expenseService.updateExpenseDetails(Id, expense);
	}
	
	@GetMapping("/readbycategory/category")
	public List<Expense> readByCategory(@RequestParam String category, Pageable page){
		return expenseService.readByCategory(category, page);
	}
	
	@GetMapping("/readbyname/name")
	public List<Expense> getExpenseByName(@RequestParam String keyword, Pageable page){
		return expenseService.readByName(keyword, page);
	}
	
	@GetMapping("/readbydate/date")
	public List<Expense> getExpenseByDate(@RequestParam(required = false) Date startDate,@RequestParam(required = false) Date endDate,Pageable page){
		return expenseService.readByDate(startDate, endDate, page);
	}
}
