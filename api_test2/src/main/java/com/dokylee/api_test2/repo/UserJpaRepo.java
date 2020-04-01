package com.dokylee.api_test2.repo;

import com.dokylee.api_test2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* 생성한 entity를 이용해서 Table에 질의를 요청하기 위한 Repository를 생성
* */
public interface UserJpaRepo extends JpaRepository<User, Long> {

}
