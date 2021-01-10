package com.mayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayer.domain.Data;

public interface ContactRepository extends JpaRepository<Data, Integer> {

}
