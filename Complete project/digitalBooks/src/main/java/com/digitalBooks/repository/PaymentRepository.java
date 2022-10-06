package com.digitalBooks.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitalBooks.entity.Payment;
import com.digitalBooks.entity.User;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Boolean existsByReaderId(Long readerId);
	List<Payment> findAllByreaderId(Long readerId);
	Payment  findByPaymentId(Integer paymentId);
	

}
