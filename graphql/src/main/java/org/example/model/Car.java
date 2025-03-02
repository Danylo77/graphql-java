package org.example.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String id;
    private String brand;
    private String model;
    private int year;
}
