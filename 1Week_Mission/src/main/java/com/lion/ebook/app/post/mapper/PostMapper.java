package com.lion.ebook.app.post.mapper;

import com.lion.ebook.app.post.dto.PostDetailDto;
import com.lion.ebook.app.post.dto.PostListDto;
import com.lion.ebook.app.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper instance = Mappers.getMapper(PostMapper.class);

    PostListDto map(Post value);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy.MM.dd HH:mm")
    @Mapping(source = "modifiedAt", target = "modifiedAt", dateFormat = "yyyy.MM.dd HH:mm")
    List<PostListDto> toListDto(List<Post> post);

    @Mapping(target = "author", expression = "java(post.getAuthor().getNickname())")
    @Mapping(target = "authorId", expression = "java(post.getAuthor().getId())")
    PostDetailDto toDetailDto(Post post);

}
