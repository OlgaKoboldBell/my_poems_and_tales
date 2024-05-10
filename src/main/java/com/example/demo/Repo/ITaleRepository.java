package com.example.demo.Repo;
import com.example.demo.Models.TaleModel;
import org.springframework.data.repository.CrudRepository;

public interface ITaleRepository extends CrudRepository<TaleModel, Long> {
}