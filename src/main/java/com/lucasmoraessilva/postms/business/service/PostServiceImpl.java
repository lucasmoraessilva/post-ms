package com.lucasmoraessilva.postms.business.service;

import com.lucasmoraessilva.postms.business.entity.Post;
import com.lucasmoraessilva.postms.data.repository.PostRepository;
import com.lucasmoraessilva.postms.business.client.UserClient;
import com.lucasmoraessilva.postms.business.exception.PostNotFoundException;
import com.lucasmoraessilva.postms.business.exception.RedactorNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository repository;
    private UserClient userClient;

    @Autowired
    public PostServiceImpl(PostRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    @Override
    public Post create(Post newPost) throws RedactorNotFoundException {
        checkRedactor(newPost.getOwnerId());

        Post postSaved = this.repository.save(newPost);

        // TODO: notify new post

        return postSaved;
    }

    @Override
    public List<Post> readAll() {
        return this.repository.findAll();
    }

    @Override
    public Post readById(Integer id) throws PostNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with provided ID"));
    }

    @Override
    public Post update(Integer id, Post postForUpdate) throws RedactorNotFoundException, PostNotFoundException {
        checkRedactor(postForUpdate.getOwnerId());

        if(!this.repository.existsById(id)) {
            throw new PostNotFoundException("Post not found with provided ID");
        }

        postForUpdate.setId(id);

        Post updatedPost = this.repository.save(postForUpdate);

        // TODO: notify post update

        return updatedPost;
    }

    @Override
    public void delete(Integer id) throws PostNotFoundException {
        if(!this.repository.existsById(id)) {
            throw new PostNotFoundException("Post not found with provided ID");
        }

        this.repository.deleteById(id);
    }

    private void checkRedactor(Integer redactorId) throws RedactorNotFoundException {
        try {
            this.userClient.getRedactor(redactorId);
        }
        catch (FeignException.BadRequest e) {
            throw new RedactorNotFoundException("Cannot find the Redactor with the provided ID");
        }
    }
}
