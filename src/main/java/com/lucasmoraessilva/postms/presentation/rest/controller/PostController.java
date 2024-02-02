package com.lucasmoraessilva.postms.presentation.rest.controller;

import com.lucasmoraessilva.postms.util.converter.PostConverter;
import com.lucasmoraessilva.postms.business.entity.Post;
import com.lucasmoraessilva.postms.business.exception.PostNotFoundException;
import com.lucasmoraessilva.postms.business.exception.RedactorNotFoundException;
import com.lucasmoraessilva.postms.business.service.PostService;
import com.lucasmoraessilva.postms.presentation.dto.PostDtoIn;
import com.lucasmoraessilva.postms.presentation.dto.PostDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PostDtoOut> create(@RequestBody PostDtoIn newPost) {
        try {
            Post post = this.service.create(PostConverter.fromDtoInToEntity(newPost));
            return ResponseEntity
                    .created(new URI("/api/posts/" + post.getId()))
                    .body(PostConverter.fromEntityToDtoOut(post));
        }
        catch (RedactorNotFoundException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
        catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping
    public ResponseEntity<List<PostDtoOut>> readAll() {
        return ResponseEntity
                .ok(PostConverter.fromEntityListToDtoOutList(this.service.readAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDtoOut> readById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(PostConverter.fromEntityToDtoOut(this.service.readById(id)));
        }
        catch (PostNotFoundException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDtoOut> update(@PathVariable("id") Integer id, @RequestBody PostDtoIn postForUpdate) {
        try {
            return ResponseEntity
                    .accepted()
                    .body(PostConverter.fromEntityToDtoOut(this.service.update(id,PostConverter.fromDtoInToEntity(postForUpdate))));
        }
        catch (PostNotFoundException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
        catch (RedactorNotFoundException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok(null);
        }
        catch (PostNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
