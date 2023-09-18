package com.sina.store.repository;

import com.sina.store.model.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    @Query("SELECT p FROM Payment p left join p.customer c " +
            "where c.id =:customerId and p.status = 1 and (p.dateOrder between :dateBegin and :dateEnd) order by p.dateOrder")
    Page<Payment> findAllPaymentsByEmployeeAndDate(Long customerId, OffsetDateTime dateBegin, OffsetDateTime dateEnd, Pageable pageable);

}