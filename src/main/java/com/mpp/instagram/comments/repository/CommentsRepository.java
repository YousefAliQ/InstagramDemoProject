package com.mpp.instagram.comments.repository;

import com.mpp.instagram.comments.entity.CommentsEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentsRepository extends CrudRepository<CommentsEntity, Long> {
	
	public List<CommentsEntity> findAll();
	
	public List<CommentsEntity> findByUserUsername(String username);
	
}
