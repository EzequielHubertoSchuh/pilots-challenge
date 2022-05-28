package com.br.empresa.pilots;

import com.br.empresa.pilots.product.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PilotsApplication {

    public PilotsApplication() throws IOException {
    }

    public static void main(String[] args) {
        SpringApplication.run(PilotsApplication.class, args);

        String path = "/home/ezequiel/intellij-workspace/workspace-governarTI/pilots/src/main/resources/static/in.txt";

        List<Product> productList = new ArrayList<Product>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {

                String[] vect = line.split(",");

                String name = vect[0];
                String price = vect[1];
                String quantity = vect[2];

                Product prod = new Product(name);
                productList.add(prod);

                line = br.readLine();
            }

            System.out.println("PRODUCTS: ");
            for (Product p : productList
            ) {
                p.toString();
            }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


}


