package com.hragro.backend.config;

import com.hragro.backend.model.*;
import com.hragro.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private FruitRepository fruitRepository;
    
    // Remove unused repositories to fix warnings
    // @Autowired
    // private GalleryRepository galleryRepository;
    
    // @Autowired
    // private FranchiseLocationRepository franchiseLocationRepository;
    
    // @Autowired
    // private FranchiseContentRepository franchiseContentRepository;
    
    @Autowired
    private AboutContentRepository aboutContentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Starting HR Agro Data Seeder...");
        System.out.println("📊 Database: hragro_farm");
        
        // ========================================
        // 1. Create Default Admin User
        // ========================================
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println("✅ Default admin created: admin/admin123");
        } else {
            System.out.println("ℹ️ Admin already exists");
        }

        // ========================================
        // 2. Seed Products (Vegetables)
        // ========================================
        if (productRepository.count() == 0) {
            Product[] products = {
                createProduct("Fresh Tomatoes", "Farm-picked, juicy & pesticide-free", 40.0, "kg", "tomato.jpg", "vegetable"),
                createProduct("Carrots", "Crisp, sweet & rich in vitamin A", 35.0, "kg", "carrot.jpg", "vegetable"),
                createProduct("Potatoes", "Everyday kitchen essential, farm fresh", 30.0, "kg", "potato.jpg", "vegetable"),
                createProduct("Capsicum", "Crunchy, colorful & vitamin-rich", 50.0, "kg", "capsicum.jpg", "vegetable"),
                createProduct("Onions", "Everyday essential, sorted & graded", 28.0, "kg", "onion.jpg", "vegetable"),
                createProduct("Green Chillies", "Fresh, spicy & harvested daily", 25.0, "kg", "chilli.jpg", "vegetable"),
            };
            productRepository.saveAll(Arrays.asList(products));
            System.out.println("✅ Seeded " + products.length + " products");
        } else {
            System.out.println("ℹ️ Products already exist");
        }

        // ========================================
        // 3. Seed Fruits
        // ========================================
        if (fruitRepository.count() == 0) {
            Fruit[] fruits = {
                createFruit("Mango", "Sweet, juicy & the king of fruits", 90.0, "kg", "mango.jpg"),
                createFruit("Banana", "Energy-packed, farm-fresh & naturally sweet", 40.0, "dozen", "banana.jpg"),
                createFruit("Papaya", "Soft, ripe & rich in vitamin C", 35.0, "kg", "papaya.jpg"),
                createFruit("Watermelon", "Refreshing, juicy & perfect for summer", 25.0, "kg", "watermelon.jpg"),
                createFruit("Orange", "Tangy, citrusy & vitamin C rich", 60.0, "kg", "orange.jpg"),
                createFruit("Grapes", "Sweet, seedless & bunch-fresh", 70.0, "kg", "grapes.jpg"),
            };
            fruitRepository.saveAll(Arrays.asList(fruits));
            System.out.println("✅ Seeded " + fruits.length + " fruits");
        } else {
            System.out.println("ℹ️ Fruits already exist");
        }

        // ========================================
        // 4. Seed About Content
        // ========================================
        if (aboutContentRepository.count() == 0) {
            AboutContent about = new AboutContent();
            about.setVision("To become India's most trusted farm-to-doorstep vegetable brand, making fresh, hygienic, and affordable produce accessible to every neighborhood through a nationwide network of mobile vegetable stores.");
            about.setMission("Our mission is to connect farmers directly with customers — cutting out unnecessary middlemen — while empowering entrepreneurs across the country to build sustainable businesses under the HR Agro brand.");
            aboutContentRepository.save(about);
            System.out.println("✅ Seeded about content");
        } else {
            System.out.println("ℹ️ About content already exists");
        }

        System.out.println("🎉 HR Agro Data Seeding Complete!");
    }

    private Product createProduct(String name, String desc, Double price, String unit, String image, String category) {
        Product product = new Product();
        product.setName(name);
        product.setDesc(desc);
        product.setPrice(price);
        product.setUnit(unit);
        product.setImage("/api/uploads/files/" + image);
        product.setCategory(category);
        return product;
    }

    private Fruit createFruit(String name, String desc, Double price, String unit, String image) {
        Fruit fruit = new Fruit();
        fruit.setName(name);
        fruit.setDesc(desc);
        fruit.setPrice(price);
        fruit.setUnit(unit);
        fruit.setImage("/api/uploads/files/" + image);
        return fruit;
    }
}