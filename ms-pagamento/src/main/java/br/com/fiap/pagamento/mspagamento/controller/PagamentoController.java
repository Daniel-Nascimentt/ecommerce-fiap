package br.com.fiap.pagamento.mspagamento.controller;

import br.com.fiap.pagamento.mspagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value =  "/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<?> pagarCarrinho(@RequestHeader("Authorization") String token, @PathVariable String idUsuario){
        pagamentoService.pagar(idUsuario, token);
        return ResponseEntity.ok().build();
    }

}
