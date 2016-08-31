package com.chyld.services;

import com.chyld.entities.Profile;
import com.chyld.entities.User;
import com.chyld.repositories.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {
    private IProfileRepository repository;

    @Autowired
    public void setRepository(IProfileRepository repository) { this.repository = repository; }

    public Profile findById(int id) { return this.repository.findById(id); }

    // getProfile

}
