package ua.com.alevel.web.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoRestController {

    public String handleFileUpload(){
        return "done";
    }
}
