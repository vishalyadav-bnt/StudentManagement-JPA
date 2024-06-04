package com.example.studentmanagament.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponnse {
    private String message;
    private int status_code;
    private Object object;
}
