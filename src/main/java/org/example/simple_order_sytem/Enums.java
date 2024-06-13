package org.example.simple_order_sytem;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Enums {
    private Integer id;
    private String name;
    private String description;


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Inner {
        private Integer id;
        private String inner_name;
    }

    public static void main(String[] args) {
        Inner build = Inner.builder()
                .id(1)
                .inner_name("INNER_NAME")
                .build();

        System.out.println(build.getInner_name());
    }
}
