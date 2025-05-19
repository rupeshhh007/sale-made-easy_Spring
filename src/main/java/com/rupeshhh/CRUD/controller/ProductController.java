package com.rupeshhh.CRUD.controller;

import com.rupeshhh.CRUD.models.Product;
import com.rupeshhh.CRUD.models.ProductDTO;
import com.rupeshhh.CRUD.repository.ProductRepository;
import java.nio.file.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repo;
    @GetMapping({"", "/"})
    public String showProductList(Model model){
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("products",products);
        return "products/index";


    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "products/CreateProduct";

    }

    @PostMapping("/create")
    public String createproduct(
            @Valid @ModelAttribute("productDTO") ProductDTO productDTO,
            BindingResult result
    ) {
        if (productDTO.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDTO", "imageFile", "The image file is empty."));
        }

        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        // Save uploaded image to static/images/
        MultipartFile image = productDTO.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            // Adjust path for Spring Boot static content
            String uploadDir = new File("src/main/resources/static/images").getAbsolutePath();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(
                        inputStream,
                        uploadPath.resolve(storageFileName),
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

            // OPTIONAL: Set file name in entity if saving to DB
            // product.setImageFileName(storageFileName);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);

        repo.save(product);


        return "redirect:/products";
    }

}


