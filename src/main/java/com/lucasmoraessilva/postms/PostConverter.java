package com.lucasmoraessilva.postms;

import java.util.List;

public class PostConverter {
    public static Post fromDtoInToEntity(PostDtoIn postDto) {
        return new Post(null, postDto.getTitle(), postDto.getSummary(), postDto.getContent(), postDto.getOwnerId());
    }

    public static PostDtoOut fromEntityToDtoOut(Post post) {
        return new PostDtoOut(post.getId(), post.getTitle(), post.getSummary(), post.getContent(), post.getOwnerId());
    }

    public static List<PostDtoOut> fromEntityListToDtoOutList(List<Post> posts) {
        return posts
                .stream()
                .map(post -> new PostDtoOut(post.getId(), post.getTitle(), post.getSummary(), post.getContent(), post.getOwnerId()))
                .toList();
    }
}
