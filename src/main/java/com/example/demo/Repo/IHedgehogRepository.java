package com.example.demo.Repo;

import com.example.demo.Models.HamsterModel;
import com.example.demo.Models.HedgehogModel;
import org.springframework.data.repository.CrudRepository;

public interface IHedgehogRepository extends CrudRepository<HedgehogModel, Long> {
}
