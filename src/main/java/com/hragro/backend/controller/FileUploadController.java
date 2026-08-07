package com.hragro.backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hragro.backend.dto.ResponseMessage;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Value("${app.file.upload-dir:uploads/}")
    private String uploadDir;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessage("Please select a file", false));
            }

            // Validate file type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessage("Only image files are allowed", false));
            }

            // Validate file size (max 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessage("File size exceeds 5MB limit", false));
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + extension;

            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save file
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return file URL
            String fileUrl = "/api/uploads/files/" + fileName;
            
            return ResponseEntity.ok(new FileUploadResponse(fileName, fileUrl, file.getSize()));
            
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseMessage("Failed to upload file: " + e.getMessage(), false));
        }
    }

    @PostMapping("/images")
    public ResponseEntity<?> uploadMultipleImages(@RequestParam("files") List<MultipartFile> files) {
        try {
            List<FileUploadResponse> uploadedFiles = new ArrayList<>();
            
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                
                // Validate file type
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    continue;
                }
                
                String originalFilename = file.getOriginalFilename();
                String extension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String fileName = UUID.randomUUID().toString() + extension;
                
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                String fileUrl = "/api/uploads/files/" + fileName;
                uploadedFiles.add(new FileUploadResponse(fileName, fileUrl, file.getSize()));
            }
            
            return ResponseEntity.ok(uploadedFiles);
            
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseMessage("Failed to upload files: " + e.getMessage(), false));
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/files/{fileName:.+}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return ResponseEntity.ok(new ResponseMessage("File deleted successfully", true));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseMessage("Failed to delete file: " + e.getMessage(), false));
        }
    }
}

// DTO for file upload response
class FileUploadResponse {
    private String fileName;
    private String fileUrl;
    private long size;

    public FileUploadResponse(String fileName, String fileUrl, long size) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.size = size;
    }

    public String getFileName() { return fileName; }
    public String getFileUrl() { return fileUrl; }
    public long getSize() { return size; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public void setSize(long size) { this.size = size; }
}