package com.example.demo.Repo;

import com.example.demo.Models.HamsterModel;
import com.example.demo.Models.TaleModel;
import org.springframework.data.repository.CrudRepository;

public interface IHamsterRepository  extends CrudRepository<HamsterModel, Long> {
}
