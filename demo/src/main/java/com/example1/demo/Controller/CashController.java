package com.example1.demo.Controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example1.demo.CashForm.CashForm;
import com.example1.demo.Repository.CashRepository;
import com.example1.demo.entity.Cash;

import jakarta.validation.Valid;
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

    @GetMapping("/cash/new")
    public ModelAndView showNewCashForm(ModelAndView mv) {
        mv.setViewName("cashForm");
        mv.addObject("cashData", new CashForm());
        return mv;
    }

   @PostMapping("/cash/new")
public String addNewCash(@ModelAttribute @Valid CashForm cashData, BindingResult result) {
    if (result.hasErrors()) {
        return "cashForm"; 
    }
    Cash cash = new Cash();
    cash.setIncome(cashData.getIncome());
    cash.setCategory(cashData.getCategory());
    cash.setDate(cashData.getDate());
    cashRepository.save(cash);
    return "redirect:/";
}

}
