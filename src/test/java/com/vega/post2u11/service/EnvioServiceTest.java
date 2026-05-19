package com.vega.post2u11.service;

import com.vega.post2u11.model.Cliente;
import com.vega.post2u11.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnvioServiceTest {

    private EnvioService service;

    @BeforeEach
    void setUp() {
        service = new EnvioService();
    }

    @Test
    void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {
        Pedido p = new Pedido();
        p.setTotal(60.0);

        assertEquals(0.0, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_estandar_conTotalBajo_debeCobrarTarifaBase() {
        Pedido p = new Pedido();
        p.setTotal(40.0);

        assertEquals(5.99, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_express_debeCobrarTarifaExpress() {
        Pedido p = new Pedido();
        p.setTotal(100.0);

        assertEquals(12.99, service.calcularEnvio(p, "EXPRESS"), 0.001);
    }

    @Test
    void calcularEnvio_mismoDia_debeCobrarTarifaMismoDia() {
        Pedido p = new Pedido();
        p.setTotal(100.0);

        assertEquals(24.99, service.calcularEnvio(p, "MISMO_DIA"), 0.001);
    }

    @Test
    void calcularEnvio_gratis_debeSerCero() {
        Pedido p = new Pedido();
        p.setTotal(10.0);

        assertEquals(0.0, service.calcularEnvio(p, "GRATIS"), 0.001);
    }

    @Test
    void calcularEnvio_tipoDesconocido_debeLanzarExcepcion() {
        Pedido p = new Pedido();
        p.setTotal(10.0);

        assertThrows(IllegalArgumentException.class, () -> service.calcularEnvio(p, "DRON"));
    }

    @Test
    void aprobarCredito_clienteNulo_debeRechazar() {
        assertEquals("RECHAZADO", service.aprobarCredito(null, 1000));
    }

    @Test
    void aprobarCredito_clienteInactivo_debeRechazar() {
        Cliente cliente = cliente(true, 700, 2000);
        cliente.setActivo(false);

        assertEquals("RECHAZADO", service.aprobarCredito(cliente, 1000));
    }

    @Test
    void aprobarCredito_scoreBajo_debeRechazar() {
        Cliente cliente = cliente(true, 599, 2000);

        assertEquals("RECHAZADO", service.aprobarCredito(cliente, 1000));
    }

    @Test
    void aprobarCredito_montoNoPositivo_debeRechazar() {
        Cliente cliente = cliente(true, 700, 2000);

        assertEquals("RECHAZADO", service.aprobarCredito(cliente, 0));
    }

    @Test
    void aprobarCredito_montoMayorAlLimite_debeRechazar() {
        Cliente cliente = cliente(true, 700, 2000);

        assertEquals("RECHAZADO", service.aprobarCredito(cliente, 2500));
    }

    @Test
    void aprobarCredito_datosValidos_debeAprobar() {
        Cliente cliente = cliente(true, 700, 2000);

        assertEquals("APROBADO", service.aprobarCredito(cliente, 1000));
    }

    private Cliente cliente(boolean activo, int score, double limiteCredito) {
        Cliente cliente = new Cliente();
        cliente.setActivo(activo);
        cliente.setScore(score);
        cliente.setLimiteCredito(limiteCredito);
        return cliente;
    }
}
