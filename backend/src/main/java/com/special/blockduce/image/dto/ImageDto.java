package com.special.blockduce.image.dto;

import lombok.*;


@Data
@NoArgsConstructor
public class ImageDto {

    private Long id;
    private String imgPath;
    private String imgFullPath;

    @Builder
    public ImageDto(Long id, String imgPath, String imgFullPath) {
        this.id = id;
        this.imgPath = imgPath;
        this.imgFullPath = imgFullPath;
    }
}
