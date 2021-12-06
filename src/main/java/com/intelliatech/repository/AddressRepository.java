package com.intelliatech.repository;

import com.intelliatech.bean.Address;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
