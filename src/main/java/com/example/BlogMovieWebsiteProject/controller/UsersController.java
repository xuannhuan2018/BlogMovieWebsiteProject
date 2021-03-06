package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsersController
{
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public String signUpUser(@RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "email", required = false) String email,
                             RedirectAttributes ra, HttpServletRequest request) {
        String url = request.getHeader("referer");
        if(usersService.existByUsername(username)){
            ra.addFlashAttribute("alertRegister", "Tên tài khoản đã tồn tại");
        }
        else {
            UsersDto usersDto = new UsersDto();
            usersService.createAccount(usersDto, username, password, email);
            System.out.println("Đăng kí thành công " + usersDto.getUsername());
            ra.addFlashAttribute("message", "Đăng kí thành công, tiến hành đăng nhập!" );
        }
        return "redirect:" +url;
    }

    @PostMapping("/check-login")
    public String checkLogin(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session, RedirectAttributes ra, HttpServletRequest request) {
        String url = request.getHeader("referer");
        if(usersService.existByUsernameAndPassword(username, password)){
            session.setAttribute("user", usersService.findUserByUsername(username));
            System.out.println("Đăng nhập user " + username + " thành công");
        }
        else {
            ra.addFlashAttribute("message", "Sai tài khoản hoặc mật khẩu!" );
        }
        return "redirect:" + url;
    }

    @GetMapping("/{username}/information")
    public String showUserInformationForm(@PathVariable(name = "username")String username,
                                          Model model){
        model.addAttribute("user", usersService.findUserByUsername(username));
        return "views/user/information";
    }

    @GetMapping("/{username}/edit")
    public String showEditInformationForm(@PathVariable(name = "username")String username,
                                          Model model){
        model.addAttribute("user", usersService.findUserByUsername(username));
        return "views/user/edit-information";
    }

    @PostMapping("/{username}/save")
    public String saveInformationOfUser(@ModelAttribute(name = "user")UsersDto usersDto,
                                        @PathVariable(name = "username")String username,
                                        RedirectAttributes ra){
        usersService.saveUser(usersDto, username);
        ra.addFlashAttribute("alert", "Chỉnh sửa tài khoản thành công");
        return "redirect:/user/" + usersDto.getUsername() + "/information";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request){
        String url = request.getHeader("referer");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:" + url;
    }

}
