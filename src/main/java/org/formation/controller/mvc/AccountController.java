//package org.formation.controller.mvc;
//
//import org.formation.config.LiftUserDetails;
//import org.formation.model.Member;
//import org.formation.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AccountController {
// 
//    @Autowired
//    private CustomUserDetailsService service;
//     
//    @GetMapping("/account")
//    public String viewUserAccountForm(
//            @AuthenticationPrincipal LiftUserDetails userDetails,
//            Model model) {
//        String username = userDetails.getUsername();
//        Member user = service.loadUserByUsername(username);
//         
//        model.addAttribute("user", user);
//        model.addAttribute("pageTitle", "Account Details");
//         
//        return "users/account_form";
//    }
//}