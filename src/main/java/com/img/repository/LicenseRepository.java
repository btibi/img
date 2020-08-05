package com.img.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.img.domain.License;

public interface LicenseRepository extends CrudRepository<License, Long> {

    @Query("select distinct l from License l " + "left join fetch l.customer "
            + "left join fetch l.tournament t left join fetch t.matches "
            + "left join fetch l.match m left join fetch m.tournament "
            + "where l.customer.customerId in (:customerIds)")
    List<License> findByCustomerIds(long... customerIds);
}
