package br.com.fiap.msGtItens.gtItens.controller;

import br.com.fiap.msGtItens.gtItens.exception.ItemNotFoundException;
import br.com.fiap.msGtItens.gtItens.request.ItemRequest;
import br.com.fiap.msGtItens.gtItens.request.ItemUpdateRequest;
import br.com.fiap.msGtItens.gtItens.request.ItenParaCarrinhoRequest;
import br.com.fiap.msGtItens.gtItens.response.ItemResponse;
import br.com.fiap.msGtItens.gtItens.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> criarItem(@Valid @RequestBody ItemRequest request){
        return ResponseEntity.ok(itemService.criarItem(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> atualizarItem(@Valid @RequestBody ItemUpdateRequest request, @PathVariable String id) throws ItemNotFoundException {
        return ResponseEntity.ok(itemService.atualizarItem(request, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> buscarItemPorId(@PathVariable String id) throws ItemNotFoundException {
        return ResponseEntity.ok(itemService.buscarItemPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<ItemResponse>> buscarTodosItens(Pageable pageable){
        return ResponseEntity.ok(itemService.buscarTodosItens(pageable));
    }

    @PostMapping("/addCarrinho")
    public ResponseEntity<?> enviarItemAoCarrinho(@Valid @RequestBody ItenParaCarrinhoRequest request) throws ItemNotFoundException {
        itemService.enviarItemAoCarrinho(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResponse> deletarItemPorId(@PathVariable String id) throws ItemNotFoundException {
        itemService.deletarItemPorId(id);
        return ResponseEntity.ok().build();
    }

}
