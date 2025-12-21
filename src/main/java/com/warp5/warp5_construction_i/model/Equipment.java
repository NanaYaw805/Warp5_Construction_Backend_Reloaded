package com.warp5.warp5_construction_i.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_seq")
    @SequenceGenerator(name = "equipment_seq", sequenceName = "equipment_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "location")
    private String location;

    @Column(name = "image_one")
    private String imageOne;

    @Column(name = "image_two")
    private String imageTwo;

    @Column(name = "image_three")
    private String imageThree;

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "rating")
    private double rating;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getImageOne() { return imageOne; }
    public void setImageOne(String imageOne) { this.imageOne = imageOne; }

    public String getImageTwo() { return imageTwo; }
    public void setImageTwo(String imageTwo) { this.imageTwo = imageTwo; }

    public String getImageThree() { return imageThree; }
    public void setImageThree(String imageThree) { this.imageThree = imageThree; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
