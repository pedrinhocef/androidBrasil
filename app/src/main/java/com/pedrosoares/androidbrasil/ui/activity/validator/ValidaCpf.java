package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf implements Validador{

    private static final String CPF_INVALIDO = "CPF inválido";
    private static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 dígitos";
    private static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidadorPadrao validadorPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = this.textInputCpf.getEditText();
        this.validadorPadrao = new ValidadorPadrao(textInputCpf);
    }


    private boolean validaCalculoCpf(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException io) {
            textInputCpf.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    @NonNull
    private String getCpf() {
        return campoCpf.getText().toString();
    }


    private boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() != 11) {
            textInputCpf.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        String cpf = getCpf();
        String cpdfSemFormato = cpf;
        try {
            cpdfSemFormato = formatador.unformat(cpf);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
        if (!validaCampoComOnzeDigitos(cpdfSemFormato)) return false;
        if (!validaCalculoCpf(cpdfSemFormato)) return false;
        adicionaFormatacao(cpdfSemFormato);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }


}
