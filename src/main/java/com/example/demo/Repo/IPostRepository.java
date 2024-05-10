package com.example.demo.Repo;

import com.example.demo.Models.PostModel;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepository extends CrudRepository<PostModel, Long> {
}
