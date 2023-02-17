package nearyouconsuting.invoice.invoicemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {
    @RequestMapping("/clients")
    public String displayClients(){
        return "clients";
    }
}
