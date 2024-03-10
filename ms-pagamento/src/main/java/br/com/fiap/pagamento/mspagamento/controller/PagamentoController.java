package br.com.fiap.pagamento.mspagamento.controller;

import br.com.fiap.pagamento.mspagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =  "/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<?> pagarCarrinho(@PathVariable String idUsuario){
        pagamentoService.pagar(idUsuario);
        return ResponseEntity.ok().build();
    }

}
