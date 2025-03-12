package com.example.web;

import com.example.service.TransferService;
import com.example.web.dto.TransferRequest;
import com.example.web.dto.TransferResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller("transferController")
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @RequestMapping(
            value = "/transfer",
            method = RequestMethod.GET
    )
    public String transfer_form(Principal principal) {
        return "redirect:transfer-form.html";
    }

    @RequestMapping(
            value = "/transfer",
            method = RequestMethod.POST
    )
    public /*String*/ ModelAndView transfer(
            @ModelAttribute TransferRequest transferRequest, // Maps Req Params to Model Object
            Model model
    ) {
        transferService.transfer(transferRequest.getFromAccountNum(), transferRequest.getToAccountNum(), transferRequest.getAmount());

        TransferResponse transferResponse = new TransferResponse(); // Model
        transferResponse.setMessage("Transfer successful");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("transferResponse", transferResponse);
        modelAndView.setViewName("transfer-status");
        return modelAndView;
    }


}
