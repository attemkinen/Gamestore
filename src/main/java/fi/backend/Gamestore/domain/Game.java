package fi.backend.Gamestore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 50, message = "Console name must be less than 50 characters")
    private String console;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Min(value = 3, message = "Age limit must be at least 3")
    private int ageLimit;

    @Min(value = 1970, message = "Published year must be no earlier than 1970")
    private int published;

    @NotNull(message = "Game must have a price")
    @Min(value = 0, message = "Price must be at least 0")
    private double price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    // Constructors
    public Game() {
        this.id = null;
        this.name = null;
        this.console = null;
        this.description = null;
        this.ageLimit = 0;
        this.published = 0;
        this.price = 0.00;
    }

    public Game(String name, String console, String description, int ageLimit, int published, double price, Category category) {
        super();
        this.name = name;
        this.console = console;
        this.description = description;
        this.ageLimit = ageLimit;
        this.published = published;
        this.price = price;
        this.category = category;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // toString method
    @Override
    public String toString() {
        if (this.category != null)
            return "Game [id=" + id + ", name=" + name + ", console=" + console + ", description=" + description
                    + ", ageLimit=" + ageLimit + ", published=" + published + ", price=" + price + ", category=" + this.getCategory() + "]";
        else
            return "Game [id=" + id + ", name=" + name + ", console=" + console + ", description=" + description
                    + ", ageLimit=" + ageLimit + ", published=" + published + ", price=" + price + "]";
    }
}
