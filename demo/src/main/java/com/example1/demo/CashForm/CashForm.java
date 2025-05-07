package com.example1.demo.CashForm;



import java.sql.Date;

import com.example1.demo.entity.Cash;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CashForm {
    private Integer id;

    @NotNull
    private Integer income;

    @NotBlank(message="入力してください")
    private String category;

    private Date date;

    public Cash toEntity() {
        Cash cash = new Cash();
        cash.setId(id);
        cash.setIncome(income);
        cash.setCategory(category);
        cash.setDate(date);
        return cash;
    }
}
