package com.lucasmoraessilva.postms.business.service;

import com.lucasmoraessilva.postms.business.entity.Post;
import com.lucasmoraessilva.postms.business.exception.PostNotFoundException;
import com.lucasmoraessilva.postms.business.exception.RedactorNotFoundException;

import java.util.List;

public interface PostService {
    Post create(Post newPost) throws RedactorNotFoundException;
    List<Post> readAll();
    Post readById(Integer id) throws PostNotFoundException;
    Post update(Integer id, Post postForUpdate) throws RedactorNotFoundException, PostNotFoundException;
    void delete(Integer id) throws PostNotFoundException;
}
