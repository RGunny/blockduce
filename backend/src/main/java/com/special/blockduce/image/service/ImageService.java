package com.special.blockduce.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final AwsS3Service awsS3Service;

    public static final String DOMAIN_NAME = "blockduce-image.s3.ap-northeast-2.amazonaws.com";

    /**
     * 멤버 이미지 생성 후 이미지 주소 반환
     *  return : String으로 된 S3에 올라간 uri
     */
    public String createImage(MultipartFile image) throws IOException {
        // 이미지 정보 생성

        String imgName = awsS3Service.uploadImage(image);
        String imgPath = "https://" + DOMAIN_NAME + "/" + imgName;

        return imgPath;
    }
}
