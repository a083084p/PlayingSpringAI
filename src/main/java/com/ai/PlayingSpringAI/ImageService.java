package com.ai.PlayingSpringAI;


import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService (OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

//    public ImageResponse generateImage (String prompt) {
//        return openAiImageModel.call(
//                new ImagePrompt(prompt,
//                        OpenAiImageOptions.builder()
//                                .withQuality("hd")
//                                .withHeight(1024)
//                                .withWidth(1024)
//                                .build()
//                ));
//    }


    public ImageResponse generateImage (String prompt, String quality, Integer n, Integer width, Integer height) {
        return openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withQuality(quality)
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build()
                ));
    }



}
