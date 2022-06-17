package tk.empee.myamazoon.model.dto.products;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

public class Product {

    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Category category;
    @Getter @Setter
    private double price;
    @Getter @Setter
    private LocalDate insertDate;

    @Getter @Setter
    private String imagePath;

    /**
     * @throws IllegalArgumentException if the {@link #title} is null or empty
     * @throws NullPointerException if the {@link #description} is null
     * @throws NullPointerException if the {@link #category} is null
     * @throws IllegalArgumentException if the {@link #price} is < 0
     * @throws IllegalArgumentException if the {@link #insertDate} is null, in the future or the year is != from the current year
     */
    public static Product create(String title, String description, Category category, double price, LocalDate insertDate) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(category);

        if(!isTitleValid(title)) {
            throw new IllegalArgumentException("Title can't be empty!");
        }

        if(!isPriceValid(price)) {
            throw new IllegalArgumentException("The price can't be belo 0");
        }

        if(!isInsertionDateValid(insertDate)) {
            throw new IllegalArgumentException("Invalid insertion date!");
        }

        return new Product(0, title, description, category, price, insertDate);
    }

    private static boolean isTitleValid(String title) {
        return title != null && !title.isEmpty();
    }
    private static boolean isPriceValid(double price) {
        return price >= 0;
    }
    private static boolean isInsertionDateValid(LocalDate date) {
        LocalDate now = LocalDate.now();
        return date != null && !date.isAfter(now) && date.getYear() == now.getYear();
    }

    public Product(long id, String title, String description, Category category, double price, LocalDate insertDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.insertDate = insertDate;
    }

    public Product(long id) {
        this.id = id;
    }

}
