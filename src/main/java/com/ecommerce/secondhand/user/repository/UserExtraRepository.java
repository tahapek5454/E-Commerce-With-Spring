package com.ecommerce.secondhand.user.repository;

import com.ecommerce.secondhand.user.model.entity.UserExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {

}
