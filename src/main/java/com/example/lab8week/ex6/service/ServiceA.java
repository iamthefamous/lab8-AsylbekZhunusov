package com.example.lab8week.ex6.service;

import com.example.lab8week.ex6.component.IdBean;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    private final IdBean idBean;

    public ServiceA(IdBean idBean) {
        this.idBean = idBean;
    }

    public String getId() {
        return "ServiceA: " + idBean.getId();
    }
}
