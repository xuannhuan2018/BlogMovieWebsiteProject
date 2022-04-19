package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.service.AuthenticationService;
import com.example.BlogMovieWebsiteProject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsersController
{
    @Autowired
    public UsersService usersService;

    @Autowired
    public AuthenticationService authenticationService;

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

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request){
        String url = request.getHeader("referer");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:" + url;
    }
}
