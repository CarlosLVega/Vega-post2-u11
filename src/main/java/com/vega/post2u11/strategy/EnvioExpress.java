package com.vega.post2u11.strategy;

import com.vega.post2u11.model.Pedido;
import org.springframework.stereotype.Component;

@Component("EXPRESS")
public class EnvioExpress implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {
        return 12.99;
    }
}
