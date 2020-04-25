package com.analuciabolico.assembly.v1.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericMessagesValidationEnum {
    CAMPO_OBRIGATORIO("javax.validation.constraints.NotNull.message"),
    TAMANHO_MAXIMO("javax.validation.constraints.Size.message"),
    TAMANHO_IGUAL("javax.validation.constraints.Size.equal.message"),
    EDITAR_PRODUTO_INATIVO("editarProdutoInativo.message"),
    EMAIL_INVALIDO("emailInvalido.message"),
    CPF_INVALIDO("cpfInvalido.message"),
    CNPJ_INVALIDO("cnpjInvalido.message"),
    CPF_CNPJ_INVALIDO("cpfCnpjInvalido.message"),
    VALOR_NAO_NEGATIVO("valorNaoNegativo.message"),
    QUANTIDADE_NAO_POSITIVA("quantidadeNaoPositiva.message"),
    ERRO_GENERICO("erroGenerico.message"),
    USUARIO_NAO_AUTORIZADO("usuarioNaoAutorizado.message");

    private String key;
}
