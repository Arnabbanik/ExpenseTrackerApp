package com.example.expensetracerapi.serviceimpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.expensetracerapi.entity.Expense;
import com.example.expensetracerapi.exception.ResourceNotFoundException;
import com.example.expensetracerapi.repository.ExpenseRepository;
import com.example.expensetracerapi.repository.UserRepository;
import com.example.expensetracerapi.service.ExpenseService;
import com.example.expensetracerapi.service.UserService;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenserepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenserepo.findByUserId(userService.getLoggedInUser().getId(), page);
	}

	@Override
	public Expense createNewExpense(Expense expense) {
		expense.setUser(userService.getLoggedInUser());
		return expenserepo.save(expense);
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenserepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
		//Optional<Expense> expense = expenserepo.findById(id);
		if(expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found by ExpenseID "+ id);
	}

	@Override
	public void deleteExpenseById(Long Id) {
//		Optional<Expense> expense = expenserepo.findById(Id);
//		
//		if(expense.isPresent()) {
//		expenserepo.deleteById(Id);
//		}
//		throw new ResourceNotFoundException("Delete expense is not exist where id is "+ Id);
		
		Expense existingExpense = getExpenseById(Id);
		
		expenserepo.delete(existingExpense);
	}
	
	@Override
	public Expense updateExpenseDetails(Long Id, Expense expense) {
		Expense existingExpense = getExpenseById(Id);
		
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		
		return expenserepo.save(existingExpense);
	}

	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		return expenserepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
	}

	@Override
	public List<Expense> readByName(String keyword, Pageable page) {
		
		return expenserepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		
		if(startDate == null) {
			startDate = new Date(0);
		}
		if(endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		return expenserepo.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page).toList();
	}
}
