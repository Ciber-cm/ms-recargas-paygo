package com.paygo.ms_recargas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRecargasPaygoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsRecargasPaygoApplication.class, args);
	}
}