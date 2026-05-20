package com.vega.post2u11.service;

import com.vega.post2u11.model.Cliente;
import com.vega.post2u11.model.Pedido;
import com.vega.post2u11.strategy.EnvioEstandar;
import com.vega.post2u11.strategy.EnvioExpress;
import com.vega.post2u11.strategy.EnvioGratis;
import com.vega.post2u11.strategy.EnvioMismoDia;
import com.vega.post2u11.strategy.EstrategiaEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class EnvioService {

    private final Map<String, EstrategiaEnvio> estrategias;

    @Autowired
    public EnvioService(Map<String, EstrategiaEnvio> estrategias) {
        this.estrategias = estrategias;
    }

    EnvioService() {
        this(Map.of(
                "ESTANDAR", new EnvioEstandar(),
                "EXPRESS", new EnvioExpress(),
                "MISMO_DIA", new EnvioMismoDia(),
                "GRATIS", new EnvioGratis()
        ));
    }

    public double calcularEnvio(Pedido pedido, String tipoEnvio) {
        return Optional.ofNullable(estrategias.get(tipoEnvio))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de envio desconocido: " + tipoEnvio))
                .calcularCosto(pedido);
    }

    public String aprobarCredito(Cliente c, double monto) {
        if (c != null) {
            if (c.isActivo()) {
                if (c.getScore() >= 600) {
                    if (monto > 0) {
                        if (monto <= c.getLimiteCredito()) {
                            return "APROBADO";
                        }
                    }
                }
            }
        }
        return "RECHAZADO";
    }
}
