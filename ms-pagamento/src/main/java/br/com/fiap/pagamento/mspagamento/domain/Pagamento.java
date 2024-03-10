package br.com.fiap.pagamento.mspagamento.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "PAGAMENTO")
@NoArgsConstructor
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoPagamento;

    private String idCarrinho;

    private BigDecimal totalPago;

    private String idUsuario;

    public Pagamento(String idCarrinho, BigDecimal valorTotal, String idUsuario) {
        this.idCarrinho = idCarrinho;
        this.totalPago = valorTotal;
        this.idUsuario = idUsuario;
    }
}
