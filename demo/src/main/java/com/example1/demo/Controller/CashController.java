package com.example1.demo.Controller;


import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example1.demo.CashForm.CashForm;
import com.example1.demo.Repository.CashRepository;
import com.example1.demo.entity.Cash;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;




@Controller
@AllArgsConstructor
public class CashController {
    HttpSession session;
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
        session.setAttribute("mode", "create");
        return mv;
        
    }

   @PostMapping("/cash/new")
public String addNewCash(@ModelAttribute("cashData") @Validated CashForm cashData, BindingResult result, Model mv) {
    if(!result.hasErrors()) {
        Cash cash = cashData.toEntity();
        cashRepository.save(cash);
        return "redirect:/";
    } else {
        mv.addAttribute("cashData", cashData);
        return "cashForm";
    }
}

@PostMapping("/cash/cancel")
public String deleteCash() {
   return "redirect:/";
}
//id で検索
@PostMapping("/cash/{id}")
public ModelAndView showCashEdit(@PathVariable int id, ModelAndView mv) {
    Cash cash = cashRepository.findById(id).get();
    mv.setViewName("cashForm");
    mv.addObject("cashData", cash);
    session.setAttribute("mode", "update");
    return mv;
   
} 
//更新処理
@PostMapping("/cash/update")
public String updateCash(@ModelAttribute("cashData") @Validated CashForm cashData, BindingResult result, Model model) {
    if(!result.hasErrors()) {
        Cash cash = cashData.toEntity();
        cashRepository.save(cash);
        return "redirect:/";
    } else {
        model.addAttribute("cashData", cashData);
        return "cashForm";
    }
   
}
//削除
@PostMapping("/cash/delete")
public String deleteCash(@ModelAttribute CashForm cashData) {
    cashRepository.deleteById(cashData.getId());
    return "redirect:/";
}

@GetMapping("/cash/result")
public String showCashResult(Model model) {
    model.addAttribute("cashSum", cashRepository.getTotalIncome());
    return "cashSum";
}
}