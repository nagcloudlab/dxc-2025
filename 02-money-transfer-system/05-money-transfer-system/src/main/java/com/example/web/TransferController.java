package com.example.web;

import com.example.exception.AccountNotFoundException;
import com.example.service.TransferService;
import com.example.web.dto.TransferRequest;
import com.example.web.dto.TransferResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//@Component
@Controller
public class TransferController {

    // Map incoming request to this method(s)
    // - by URL
    // - by HTTP method
    // - by request presence|absence parameters
    // - by request presence|absence headers
    // - by request body

    // possible arguments to handler method:

    // servlet API objects
    //-------------------
    // - HttpServletRequest
    // - HttpServletResponse
    // - HttpSession

    // request parameters
    //-------------------

    // - @RequestParam
    // - @PathVariable
    // - @RequestHeader
    // - @CookieValue
    // - @RequestBody
    // - @RequestAttribute
    //....

    // possible return values:

    // - void
    // - String
    // - ModelAndView
    // - Model
    //..

    @RequestMapping(
            value = "/url1",
            method = RequestMethod.GET,
            headers = "foo=bar,!baz",
            params = "a=b,!c"
    )
    public void handleMethod() {
        //...
    }


    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @RequestMapping(
            value = "/transfer",
            method = RequestMethod.GET
    )
    public String transfer_form() {
        //..
        return "redirect:transfer-form.html";
    }

    @RequestMapping(
            value = "/transfer",
            method = RequestMethod.POST
    )
    public /*String*/ ModelAndView transfer(
//        @RequestParam String fromAccountNum,
//        @RequestParam String toAccountNum,
//        @RequestParam double amount
            @ModelAttribute TransferRequest transferRequest, // Maps Req Params to Model Object
            Model model
    ) {
        // validation...
        // business logic...
//        transferService.transfer(fromAccountNum, toAccountNum, amount);
//        return "redirect:transfer-success.html";

        transferService.transfer(transferRequest.getFromAccountNum(), transferRequest.getToAccountNum(), transferRequest.getAmount());

        TransferResponse transferResponse = new TransferResponse(); // Model
        transferResponse.setMessage("Transfer successful");

//        model.addAttribute("transferResponse", transferResponse);
//        return "transfer-status"; // View

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("transferResponse", transferResponse);
        modelAndView.setViewName("transfer-status");

        return modelAndView;


    }


}
