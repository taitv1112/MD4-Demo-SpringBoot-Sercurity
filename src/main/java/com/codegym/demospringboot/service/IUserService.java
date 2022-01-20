package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<AppUser> findAll(Pageable pageable);
    public List<AppUser> findAll();
    void save(AppUser user);
    void delete(long id);
    AppUser findById(long id);

}
