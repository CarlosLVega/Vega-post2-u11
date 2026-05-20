package com.vega.post2u11.strategy;

import com.vega.post2u11.model.Pedido;
import org.springframework.stereotype.Component;

@Component("MISMO_DIA")
public class EnvioMismoDia implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {
        return 24.99;
    }
}
