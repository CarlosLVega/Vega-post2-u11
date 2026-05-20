package com.vega.post2u11.strategy;

import com.vega.post2u11.model.Pedido;

public interface EstrategiaEnvio {

    double calcularCosto(Pedido pedido);
}
