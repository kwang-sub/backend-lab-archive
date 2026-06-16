package com.example.ch08.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.Parent;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Comparable<Image> {

    @Column(nullable = false)
    private String filename;
    private int width;
    private int height;
    @Parent
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return width == image.width && height == image.height && Objects.equals(filename, image.filename) && Objects.equals(item, image.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, width, height, item);
    }

    @Override
    public int compareTo(Image o) {
        return this.filename.compareTo(o.filename);
    }
}
