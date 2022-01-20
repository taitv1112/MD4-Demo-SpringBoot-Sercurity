package com.codegym.demospringboot.repository;

import com.codegym.demospringboot.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IAppUserRepo extends PagingAndSortingRepository<AppUser,Long> {
    @Query(value = "select u from AppUser  u where u.user_name=:user_name")
    public AppUser findByUser_name(String user_name);
}
