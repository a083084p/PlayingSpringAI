package com.ai.PlayingSpringAI;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;

    private final RecipeService recipeService;


    public GenAIController (ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }


    @GetMapping("ask-ai")
    public String getResponse (@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }


//    @GetMapping("generate-image")
//    public void generateImage (HttpServletResponse response, @RequestParam String prompt)
//            throws IOException {
//        ImageResponse imageResponse = imageService.generateImage(prompt);
//        String imageUrl = imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(imageUrl);
//    }

    @GetMapping("generate-image")
    public void generateImage (HttpServletResponse response,
                               @RequestParam String prompt,
                               @RequestParam (defaultValue = "hd") String quality,
                               @RequestParam (defaultValue = "1") Integer n,
                               @RequestParam (defaultValue = "1024") Integer width,
                               @RequestParam (defaultValue = "1024") Integer height)
            throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }


    @GetMapping("generate-images")
    public List<String> generateImages (HttpServletResponse response,
                               @RequestParam String prompt,
                               @RequestParam (defaultValue = "hd") String quality,
                               @RequestParam (defaultValue = "1") Integer n,
                               @RequestParam (defaultValue = "1024") Integer width,
                               @RequestParam (defaultValue = "1024") Integer height)
            throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);
        return imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
    }


    @GetMapping("create-recipe")
    public String createRecipe (@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestrictions,
                                      @RequestParam(defaultValue = "en") String language) {

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions, language);

    }



}
