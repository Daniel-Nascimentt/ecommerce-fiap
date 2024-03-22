package br.com.fiap.pagamento.mspagamento.service;

import br.com.fiap.pagamento.mspagamento.client.MsCarrinhoClient;
import br.com.fiap.pagamento.mspagamento.domain.Pagamento;
import br.com.fiap.pagamento.mspagamento.repository.PagamentoRepository;
import br.com.fiap.pagamento.mspagamento.request.FecharCarrinhoRequest;
import br.com.fiap.pagamento.mspagamento.response.CarrinhoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {


    @Autowired
    private MsCarrinhoClient carrinhoClient;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public void pagar(String idUsuario, String token) {
        CarrinhoResponse carrinho = carrinhoClient.obterCarrinho(token, idUsuario);
        Pagamento pagamento = efetuarPagamento(carrinho, idUsuario);
        carrinhoClient.fecharCarrinho(token, new FecharCarrinhoRequest(pagamento.getCodigoPagamento().toString(), idUsuario));
    }

    private Pagamento efetuarPagamento(CarrinhoResponse carrinho, String idUsuario) {
        return pagamentoRepository.save(new Pagamento(carrinho.getCodigoCarrinho(), carrinho.getValorTotal(), idUsuario));
    }
}
