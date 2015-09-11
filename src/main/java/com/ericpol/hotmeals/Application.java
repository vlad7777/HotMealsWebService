package com.ericpol.hotmeals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ericpol.hotmeals.Model.Category;
import com.ericpol.hotmeals.Model.Receipt;
import com.ericpol.hotmeals.Model.ReceiptLine;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}