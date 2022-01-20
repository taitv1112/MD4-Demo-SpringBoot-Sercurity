package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
}
