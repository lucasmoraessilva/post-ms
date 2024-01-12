package com.lucasmoraessilva.postms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post create(Post newPost) {
        try {
            return this.repository.save(newPost);
        }
        catch (Exception e) {
            throw  e;
        }
    }

    @Override
    public List<Post> readAll() {
        try {
            return this.repository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Post readById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new NullPointerException("Post not found"));
    }

    @Override
    public Post update(Integer id, Post postForUpdate) {
        if(this.repository.existsById(id)) {
            postForUpdate.setId(id);
            return this.repository.save(postForUpdate);
        }

        throw new NullPointerException("Post not found");
    }

    @Override
    public void delete(Integer id) {
        this.repository.deleteById(id);
    }
}
