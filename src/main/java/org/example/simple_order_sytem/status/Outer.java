package org.example.simple_order_sytem.status;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Outer {
    private Integer id;
    private String name;
    private String description;


    @Getter
    @Setter
    //@Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Inner {
        private Integer id;
        private String inner_name;
    }

    public static void main(String[] args) {
        /*Inner build = Inner.builder()
                .id(1)
                .inner_name("INNER_NAME")
                .build();

        System.out.println(build.getInner_name());*/


        Outer.Inner inner = new Outer().new Inner();
        inner.setId(12);
        inner.setInner_name("inner_name");

        System.out.println(inner.id);
        System.out.println(inner.inner_name);

        System.out.println("------------------");

        System.out.println(inner.getId());
        System.out.println(inner.getInner_name());
    }
}
