package com.codegym.demospringboot.controller;

import com.codegym.demospringboot.model.Role;
import com.codegym.demospringboot.model.AppUser;
import com.codegym.demospringboot.service.IRoleService;
import com.codegym.demospringboot.service.IUserService;
import com.codegym.demospringboot.validate.CheckDuplicateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    CheckDuplicateData checkDuplicateData;

    @Value("${uploadPart}")
    private String uploadPart;

    @ModelAttribute("roles")
    public List<Role> listRole(){
        return iRoleService.findAll();
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("user")
    public AppUser user(){
        return new AppUser();
    }

    @GetMapping("/home")
    public ModelAndView showAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="email") String option ){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("users",iUserService.findAll(PageRequest.of(page, 3, Sort.by(option))));
        modelAndView.addObject("option", option);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;

    }
    @PostMapping("/create")
    public String create(@ModelAttribute("user") AppUser user, BindingResult bindingResult, @RequestParam MultipartFile upImg){
//        checkDuplicateData.validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "create";
        }
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(uploadPart+"/img/" + nameFile));
            user.setAvatar("/img/" + nameFile);
            iUserService.save(user);
        } catch (IOException e) {
            user.setAvatar("https://i.pinimg.com/originals/15/37/a7/1537a76c07952bbe69da01b8086a5f00.png");
            iUserService.save(user);
            e.printStackTrace();
        }
        return "redirect:/home";
    }
    @GetMapping("/edit")
    public ModelAndView editForm(@RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user",iUserService.findById(id));
        return modelAndView ;
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") AppUser user, BindingResult bindingResult, @RequestParam MultipartFile upImg){
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File(uploadPart+"/img/" + nameFile));
            user.setAvatar("/img/" + nameFile);
            iUserService.save(user);
        } catch (IOException e) {
            user.setAvatar("https://i.pinimg.com/originals/15/37/a7/1537a76c07952bbe69da01b8086a5f00.png");
            iUserService.save(user);
            e.printStackTrace();
        }
        return "redirect:/home";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id){
       iUserService.delete(id);
        return "redirect:/home";
    }
}
