package com.paygo.ms_recargas.client;
import com.paygo.ms_recargas.dto.TarjetaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tarjetas-service", url = "${tarjetas.service.url}")
public interface TarjetaFeignClient {

    @GetMapping("/tarjetas/{id}")
    TarjetaDTO obtenerTarjeta(@PathVariable("id") String id);
}
