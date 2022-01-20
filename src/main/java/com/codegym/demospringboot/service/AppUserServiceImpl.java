package com.codegym.demospringboot.service;

import com.codegym.demospringboot.model.AppUser;
import com.codegym.demospringboot.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AppUserServiceImpl implements IUserService, UserDetailsService {
    @Autowired
    IAppUserRepo iUserRepo;
    @Override
    public Page<AppUser> findAll(Pageable pageable) {
        return (Page<AppUser>) iUserRepo.findAll(pageable);
    }

    @Override
    public List<AppUser> findAll() {
        return (List<AppUser>) iUserRepo.findAll();
    }

    @Override
    public void save(AppUser user) {
        iUserRepo.save(user);
    }

    @Override
    public void delete(long id) {
        iUserRepo.deleteById(id);
    }

    @Override
    public AppUser findById(long id) {
        return iUserRepo.findById(id).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = iUserRepo.findByUser_name(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        UserDetails userDetails = new User(user.getUser_name(),user.getPassword(),authorities);
        return userDetails;
    }
}
