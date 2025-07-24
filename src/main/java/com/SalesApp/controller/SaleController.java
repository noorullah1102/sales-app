package com.SalesApp.controller;


import com.SalesApp.model.Customer;
import com.SalesApp.model.Product;
import com.SalesApp.model.Sale;
import com.SalesApp.model.SaleItem;
import com.SalesApp.repository.CustomerRepository;
import com.SalesApp.repository.ProductRepository;
import com.SalesApp.repository.SaleItemRepository;
import com.SalesApp.repository.SaleRepository;
import com.SalesApp.viewmodel.SaleRequest;
import com.SalesApp.viewmodel.SaleItemRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SaleController {


    @Autowired
    private CustomerRepository customerRepo;


    @Autowired
    private ProductRepository productRepo;


    @Autowired
    private SaleRepository saleRepo;


    @Autowired
    private SaleItemRepository saleItemRepo;


    // GET /sales/create
    @GetMapping("/sales/create")
    public String showSaleForm(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        return "views/sale/create";
    }


    // GET /products/{id}/price
    @GetMapping("/products/{id}/price")
    @ResponseBody
    public ResponseEntity<Double> getProductPrice(@PathVariable("id") int productId) {
        return productRepo.findById(productId)
                .map(p -> ResponseEntity.ok(p.getPrice()))
                .orElse(ResponseEntity.notFound().build());
    }


    // POST /sales
    @PostMapping("/sales")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> saveSale(@RequestBody SaleRequest request) {


        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Invalid customer"));


        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setSaleDate(LocalDate.parse(request.getSaleDate()));
        saleRepo.save(sale);


        List<SaleItem> items = new ArrayList<>();
        for (SaleItemRequest itemReq : request.getItems()) {
            Product product = productRepo.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Invalid product"));


            SaleItem item = new SaleItem();
            item.setSale(sale);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(product.getPrice());
            items.add(item);
        }


        saleItemRepo.saveAll(items);
        return ResponseEntity.ok("Sale saved");
    }


    @GetMapping("/sales")
    public String listSales(Model model)
    {
        List<Sale> sales = saleRepo.findAll();
        model.addAttribute("sales", sales);
        return "views/sale/index";
    }
    @GetMapping("/sales/{id}/items")
    @ResponseBody
    public List<SaleItem> getSaleItems(@PathVariable("id") int saleId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return sale.getItems(); // Make sure fetch = FetchType.EAGER or session open
    }

}
