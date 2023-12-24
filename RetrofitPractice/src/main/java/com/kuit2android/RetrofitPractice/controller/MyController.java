package com.kuit2android.RetrofitPractice.controller;

import com.kuit2android.RetrofitPractice.domain.BaseResponse;
import com.kuit2android.RetrofitPractice.domain.MyData;
import com.kuit2android.RetrofitPractice.domain.MyDataRequest;
import com.kuit2android.RetrofitPractice.service.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MyDataService myDataService;

    @GetMapping("/mydata/get")
    public ResponseEntity<BaseResponse<List<MyData>>> getAllMyData() {
        return ResponseEntity.ok(new BaseResponse<List<MyData>> (true, 200, "OK", myDataService.findAll()));
    }

    @GetMapping("/mydata/get/{id}")
    public  ResponseEntity<BaseResponse<MyData>> getMyDataByGet(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new BaseResponse<MyData>(true, 200, "OK", myDataService.findById(id).get()));
    }

    @PostMapping("/mydata/post")
    public  ResponseEntity<BaseResponse<MyData>> getMyDataByPost(@RequestBody MyDataRequest myDataRequest) {
        return ResponseEntity.ok(new BaseResponse<MyData>(true, 200, "OK", myDataService.findById(myDataRequest.getId()).get()));
    }
}
