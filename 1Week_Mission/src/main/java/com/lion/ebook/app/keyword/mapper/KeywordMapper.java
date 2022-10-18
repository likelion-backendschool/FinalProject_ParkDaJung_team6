package com.lion.ebook.app.keyword.mapper;

import com.lion.ebook.app.keyword.dto.KeywordDto;
import com.lion.ebook.app.keyword.entity.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface KeywordMapper {
    KeywordMapper instance = Mappers.getMapper(KeywordMapper.class);

    KeywordDto toDto(Keyword keyword);


    List<KeywordDto> toDtoList(List<Keyword> keyword);
}
