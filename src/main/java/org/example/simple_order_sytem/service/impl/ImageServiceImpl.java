package org.example.simple_order_sytem.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.simple_order_sytem.entity.Image;
import org.example.simple_order_sytem.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl {
    private final ImageRepository imageRepository;

    public String save(MultipartFile file) {
        Image image = new Image();
        try {
            image.setData(file.getBytes());
            image.setFilename(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            imageRepository.save(image);
            return image.getFilename() + " => image saved successfully";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getImage(Integer id) {
        Optional<Image> optional = this.imageRepository.findById(id);
        return optional.map(Image::getData).orElse(null);
    }

    public List<byte[]> getAllImages() {
        return this.imageRepository.findAll()
                .stream()
                .map(Image::getData)
                .toList();
    }

    public void fetchAllImages(HttpServletResponse response) {
        int i = 1;
        List<byte[]> allImages = getAllImages();
        for (byte[] allImage : allImages) {
            saveImageToFile(allImage, "image" + i + ".jpg", response);
            i++;
        }
    }

    public void saveImageToFile(byte[] data, String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(String.valueOf(response.getOutputStream()))) {
            fos.write(data);
        } catch (Exception e) {
            return;
        }
    }

    public byte[] getAllImage() {
        List<Image> images = this.imageRepository.findAll();
        List<byte[]> imageData = new ArrayList<>();
        for (Image image : images) {
            imageData.add(image.getData());
        }
        int sumLength = imageData.stream().mapToInt(value -> value.length).sum();
        byte[] imagDataLength = new byte[sumLength];
        int desTops = 0;

        for (byte[] image : imageData) {
            System.arraycopy(image, 0, imagDataLength, desTops, image.length);
            desTops += image.length;
        }

        return imagDataLength;
    }
}
