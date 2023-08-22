package com.preproject.seb_pre_15.image.repository;

import com.preproject.seb_pre_15.image.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
  Optional<ProfileImage> findProfileImageByMember_MemberId(long memberId);
}