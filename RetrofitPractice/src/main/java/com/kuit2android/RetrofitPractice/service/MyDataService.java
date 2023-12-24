package com.kuit2android.RetrofitPractice.service;

import com.kuit2android.RetrofitPractice.domain.MyData;
import com.kuit2android.RetrofitPractice.repository.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyDataService {

    @Autowired
    private MyDataRepository myDataRepository;

    public List<MyData> findAll() {
        return myDataRepository.findAll();
    }

    public Optional<MyData> findById(Long id) {
        return myDataRepository.findById(id);
    }

}
