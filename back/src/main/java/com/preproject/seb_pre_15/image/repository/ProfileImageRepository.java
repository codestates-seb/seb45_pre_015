package com.preproject.seb_pre_15.image.repository;

import com.preproject.seb_pre_15.image.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}