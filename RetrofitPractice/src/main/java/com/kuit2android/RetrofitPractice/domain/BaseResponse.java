package com.kuit2android.RetrofitPractice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@ResponseBody
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T result;
}
