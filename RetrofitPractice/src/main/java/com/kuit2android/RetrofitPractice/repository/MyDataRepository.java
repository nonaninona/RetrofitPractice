package com.kuit2android.RetrofitPractice.repository;

import com.kuit2android.RetrofitPractice.domain.MyData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyDataRepository extends JpaRepository<MyData, Long> {
}
