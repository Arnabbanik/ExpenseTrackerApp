package com.example.expensetracerapi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.expensetracerapi.entity.Expense;

public interface ExpenseService {

	Page<Expense> getAllExpenses(Pageable page);
	
	Expense createNewExpense(Expense expense);
	
	Expense getExpenseById(Long id);
	
	void deleteExpenseById(Long Id);
	
	Expense updateExpenseDetails(Long Id,Expense expense);
	
	List<Expense> readByCategory(String category, Pageable page);
	
	List<Expense> readByName(String keyword, Pageable page);
	
	List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
