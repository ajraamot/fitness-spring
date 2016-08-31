package com.chyld.repositories;

import com.chyld.entities.Profile;
import com.chyld.entities.Role;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IProfileRepository extends PagingAndSortingRepository<Role, Integer> {
    public Profile findById(int id);

}
