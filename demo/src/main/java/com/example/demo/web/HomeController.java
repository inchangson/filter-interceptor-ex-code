package com.example.demo.web;

import com.example.demo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {
    @GetMapping("/")
    public String homeLoginSpring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        //login
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
    @GetMapping("/info")
    public String homeLoginSpring() {
        return "info";
    }

}
