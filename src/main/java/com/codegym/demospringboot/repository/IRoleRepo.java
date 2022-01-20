package com.codegym.demospringboot.repository;

import com.codegym.demospringboot.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepo extends CrudRepository<Role,Long> {
}
