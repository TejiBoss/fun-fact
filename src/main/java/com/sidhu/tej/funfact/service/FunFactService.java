package com.sidhu.tej.funfact.service;

import com.sidhu.tej.funfact.repository.FunFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunFactService {

    @Autowired
    private FunFactRepository funFactRepository;

    public String getFact (String category) {
        int factCount = funFactRepository.getCategoryFactCount(category);
        // TODO generate random number between 1 and factCOunt
        int seqNbr = (int)(Math.random() * factCount) + 1;
        String fact = funFactRepository.getFact(category, seqNbr);
        return fact;
    }
}
