package click.pranjalonline.bakibohibackend.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionController {
    @GetMapping
    public String getTransactions(){
        return "All transactions";
    }

}
