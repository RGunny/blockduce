//package com.special.blockduce.image.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.special.blockduce.image.dto.ImageDto;
//import com.special.blockduce.member.domain.Member;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(name = "images")
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "image_id")
//    private Long id;
//
//    @Column(name = "image_path")
//    private String imagePath;
//
//    @Column(name = "img_full_path")
//    private String imgFullPath;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
//    private Member member;
//
//
//    /**
//     * Image 생성 메서드
//     */
//    public static Image createImage(ImageDto imageDto) {
//        Image image = new Image();
//        image.setImagePath(imageDto.getImgPath());
//        image.setImgFullPath(imageDto.getImgFullPath());
//        return image;
//    }
//}