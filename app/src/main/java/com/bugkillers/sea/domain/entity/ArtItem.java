package com.bugkillers.sea.domain.entity;


import java.time.LocalDate;

public class ArtItem {
    private long id;
    private String artist;
    private String title;
    private String description;
    private LocalDate create_at;
    private LocalDate modify_at;
    private LocalDate rental_date;
    private LocalDate rental_start_date;
    private String rental_status;
    private int price;
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public LocalDate getModify_at() {
        return modify_at;
    }

    public void setModify_at(LocalDate modify_at) {
        this.modify_at = modify_at;
    }

    public LocalDate getRental_date() {
        return rental_date;
    }

    public void setRental_date(LocalDate rental_date) {
        this.rental_date = rental_date;
    }

    public LocalDate getRental_start_date() {
        return rental_start_date;
    }

    public void setRental_start_date(LocalDate rental_start_date) {
        this.rental_start_date = rental_start_date;
    }

    public String getRental_status() {
        return rental_status;
    }

    public void setRental_status(String rental_status) {
        this.rental_status = rental_status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


