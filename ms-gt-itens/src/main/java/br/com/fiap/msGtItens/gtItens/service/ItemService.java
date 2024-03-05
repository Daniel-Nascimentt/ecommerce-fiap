package br.com.fiap.msGtItens.gtItens.service;

import br.com.fiap.msGtItens.gtItens.domain.Item;
import br.com.fiap.msGtItens.gtItens.exception.ItemNotFoundException;
import br.com.fiap.msGtItens.gtItens.repository.ItemRepository;
import br.com.fiap.msGtItens.gtItens.request.ItemRequest;
import br.com.fiap.msGtItens.gtItens.request.ItemUpdateRequest;
import br.com.fiap.msGtItens.gtItens.request.ItenParaCarrinhoRequest;
import br.com.fiap.msGtItens.gtItens.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse criarItem(ItemRequest request) {
        Item itemSalvo = itemRepository.save(request.toDomain());
        return new ItemResponse(itemSalvo);
    }

    public ItemResponse atualizarItem(ItemUpdateRequest request, String id) throws ItemNotFoundException {
        Item item = itemRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ItemNotFoundException("Item não encontrado!!"));

        item.atualizar(request);
        itemRepository.save(item);

        return new ItemResponse(item);
    }

    public ItemResponse buscarItemPorId(String id) throws ItemNotFoundException {
        return new ItemResponse(itemRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ItemNotFoundException("Item não encontrado!!")));
    }

    public Page<ItemResponse> buscarTodosItens(Pageable pageable) {
        Page<Item> itensDomain = itemRepository.findAll(pageable);
        return itensDomain.map(this::toResponse);
    }

    private ItemResponse toResponse(Item item) {
        return new ItemResponse(item);
    }

    public void enviarItemAoCarrinho(ItenParaCarrinhoRequest request) throws ItemNotFoundException {
        Item item = itemRepository.findById(UUID.fromString(request.getIdItem())).orElseThrow(() -> new ItemNotFoundException("Item não encontrado!!"));

        BigDecimal valor = item.getPreco().multiply(new BigDecimal(request.getQuantidade()));

        // enviar para ms de carrinho
    }

    public void deletarItemPorId(String id) throws ItemNotFoundException {
        Item item = itemRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ItemNotFoundException("Item não encontrado!!"));
        itemRepository.delete(item);
    }
}
