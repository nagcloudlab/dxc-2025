package com.example.web;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Component
@Controller
public class TransferController {

    // Map incoming request to this method(s)
    // - by URL
    // - by HTTP method
    // - by request parameters
    // - by request headers
    // - by request body

    @RequestMapping(
            value = "/transfer",
            method = RequestMethod.GET
    )
    public String transfer_form() {
        //.. Auth....
        return "transfer-form"; // View Name
    }

}
