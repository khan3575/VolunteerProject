package com.sakib.bubt.volunternetwork2.repository;

import com.sakib.bubt.volunternetwork2.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
