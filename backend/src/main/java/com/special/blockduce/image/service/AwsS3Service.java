package com.special.blockduce.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.special.blockduce.image.config.AwsConfiguration;
import com.special.blockduce.image.property.AwsS3Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AwsConfiguration awsConfiguration;
    private final AwsS3Property awsS3Property;
    private AmazonS3 amazonS3;

    public static final String DOMAIN_NAME = "blockduce.member.image";

    @Transactional
    public String uploadImage(MultipartFile image) throws IOException {
        amazonS3 = awsConfiguration.setS3Client();

        UUID uuid = UUID.randomUUID();
        long time = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        String imageName = uuid + "-" + formatter.format(time) + image.getOriginalFilename();

        amazonS3.putObject(new PutObjectRequest(awsS3Property.getBucket(), imageName, image.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return imageName;
    }
}
