package com.arif.bootcamp.service.impl;

import com.arif.bootcamp.entity.Product;
import com.arif.bootcamp.repository.ProductRepository;
import com.arif.bootcamp.service.GenerateCsvService;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.List;

@Service
public class GenerateCsvServiceImpl implements GenerateCsvService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public String generateReportCsv() throws IOException {
        try {
            List<Product> products = productRepository.findAll();

            StringWriter sWriter = new StringWriter();
            CSVWriter writer = new CSVWriter(sWriter);
            if (!products.isEmpty()) {
                String[] header = { "ID", "Product Name", "Product Price", "Product Quantity" };
                writer.writeNext(header);

                for (Product product : products) {
                    String[] data = {
                            product.getId().toString(),
                            product.getProductName(),
                            String.valueOf(product.getProductPrice()),
                            String.valueOf(product.getProductQuantity()),
                    };
                    writer.writeNext(data);
                }

                writer.close();

                // Encode Base64
                return Base64.getEncoder().encodeToString(sWriter.toString().getBytes());
            }
            throw new IOException("Invalid Input");
        } catch (IOException e) {
            return "";
        }
    }
}
