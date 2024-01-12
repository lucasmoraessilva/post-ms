package com.lucasmoraessilva.postms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
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
                    .created(new URI("http://localhost/api/post/" + post.getId()))
                    .body(PostConverter.fromEntityToDtoOut(post));
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
        return ResponseEntity.ok(PostConverter.fromEntityToDtoOut(this.service.readById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDtoOut> update(@PathVariable("id") Integer id, @RequestBody PostDtoIn postForUpdate) {
        return ResponseEntity
                .accepted()
                .body(PostConverter.fromEntityToDtoOut(this.service.update(id,PostConverter.fromDtoInToEntity(postForUpdate))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        this.service.delete(id);
        return ResponseEntity.ok(null);
    }
}
