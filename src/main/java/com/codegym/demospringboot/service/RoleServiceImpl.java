package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.Role;
import com.codegym.demospringboot.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    IRoleRepo iRoleRepo;
    @Override
    public List<Role> findAll() {
        return (List<Role>) iRoleRepo.findAll();
    }
}
