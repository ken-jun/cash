package com.example1.demo.Controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example1.demo.Repository.CashRepository;
import com.example1.demo.entity.Cash;


import lombok.AllArgsConstructor;




@Controller
@AllArgsConstructor
public class CashController {
    private final CashRepository cashRepository;
    @GetMapping("/")
    public ModelAndView showCashList (ModelAndView mv) {
        mv.setViewName("cashList");
        List<Cash> cashList = cashRepository.findAll();
        mv.addObject("cashList", cashList);
        return mv;
    }
}
