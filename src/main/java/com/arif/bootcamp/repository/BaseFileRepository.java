package com.arif.bootcamp.repository;

import com.arif.bootcamp.entity.BaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseFileRepository extends JpaRepository<BaseFile, Long> {
}
