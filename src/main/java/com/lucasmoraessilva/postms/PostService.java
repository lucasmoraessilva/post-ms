package com.lucasmoraessilva.postms;

import java.util.List;

public interface PostService {
    Post create(Post newPost);
    List<Post> readAll();
    Post readById(Integer id);
    Post update(Integer id, Post postForUpdate);
    void delete(Integer id);
}
