package com.example1.demo.CashForm;



import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CashForm {
    private Integer id;

    @NotBlank(message="入力してください入力してください")
    private String income;

    @NotBlank(message="入力してください")
    private String category;

    private Date date;
}
