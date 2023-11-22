package com.example.expensetracerapi.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expensetracerapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	//select * from tbl_expense where category = ?
	//Page<Expense> findByCategory(String category, Pageable page);
	
	//SELECT * FROM tbl_expense WHERE name LIKE '%keyword%'
	//Page<Expense> findByNameContaining(String keyword, Pageable page);
	
	//SELECT * FROM tbl_expense WHERE data BETWEEN 'startDate' AND 'endDate';
	//Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
	
	//SELECT * FROM tbl_expenses WHERE user_id =?;
	Page<Expense> findByUserId(Long userId, Pageable page);
	
	//SELECT * FROM tbl_expenses WHERE user_id=? AND id=?;
    Optional<Expense> findByUserIdAndId(Long userId,Long Id);
    
    // SELECT * FROM tbl_expenses WHERE user_id=? AND category=?
    Page<Expense> findByUserIdAndCategory(Long userId,String category,Pageable page);
    
    // SELECT * FROM tbl_expenses WHERE user_id=? AND name LIKE '%keyword%'
    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);
    
    //SELECT * FROM tbl_expenses WHERE user_id=? AND date BETWEEN 'startDate' AND 'endDate'
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

}
