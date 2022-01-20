package com.codegym.demospringboot.validate;

import com.codegym.demospringboot.model.AppUser;
import com.codegym.demospringboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckDuplicateData implements Validator {
    @Autowired
    IUserService iUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
            AppUser user= (AppUser) target;
        for (AppUser u:iUserService.findAll()) {
            if(user.getUser_name().equals(u.getUser_name()) ) {
                errors.rejectValue("userName","","User Name already exist");
                return;
            }
            if(user.getEmail().equals(u.getEmail())){
                errors.rejectValue("email","","Email already exist");
                return;
            }
        }
    }


}
