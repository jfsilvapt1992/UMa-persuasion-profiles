package com.uma.mestrado.persuasive_profiles.database.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.Predicate;



@NoRepositoryBean
public interface ICommonRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>
{

  @Override
  List<T> findAll(Predicate predicate);

  @Override
  Optional<T> findOne(Predicate predicate);
}
