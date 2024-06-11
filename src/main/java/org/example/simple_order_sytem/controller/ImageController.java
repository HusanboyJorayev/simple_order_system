package org.example.simple_order_sytem.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.entity.Image;
import org.example.simple_order_sytem.service.impl.ImageServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/image/")
public class ImageController {
    private final ImageServiceImpl imageServiceImpl;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@Parameter(description = "The image file to upload", required = true)
                                              @RequestParam(value = "file") MultipartFile file) {
        String imageId = this.imageServiceImpl.save(file);
        return new ResponseEntity<>(imageId, HttpStatus.OK);
    }

    @GetMapping("/get_image")
    public ResponseEntity<ByteArrayResource> getImage(@RequestParam("imageId") Integer imageId) {
        byte[] image = this.imageServiceImpl.getImage(imageId);
        ByteArrayResource byteArrayResource = new ByteArrayResource(image);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return new ResponseEntity<>(byteArrayResource, headers, HttpStatus.OK);
    }

    @GetMapping("/getAllImages")
    public ResponseEntity<List<ByteArrayResource>> getAllImages() {
        List<byte[]> allImages = this.imageServiceImpl.getAllImages();
        List<ByteArrayResource> resourceList = new ArrayList<>();
        for (byte[] image : allImages) {
            ByteArrayResource e = new ByteArrayResource(image);
            resourceList.add(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return new ResponseEntity<>(resourceList, headers, HttpStatus.OK);
    }

    @GetMapping("/fetch_all_images")
    public void fetchAllImage(HttpServletResponse response) {
        byte[] allImage = this.imageServiceImpl.getAllImage();
        response.setContentType("image/jpeg");
        response.setContentLength(allImage.length);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        try {
            response.getOutputStream().write(allImage);
            response.flushBuffer();
        } catch (Exception e) {
            return;
        }
    }
}
