package com.example.demo.Repo;

import com.example.demo.Models.MyUserModel;
import org.springframework.data.repository.CrudRepository;

public interface IMyUserRepository extends CrudRepository<MyUserModel, Long> {
}
