package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidadorPadrao validadorPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = this.textInputCpf.getEditText();
        this.validadorPadrao = new ValidadorPadrao(textInputCpf);
    }


    private boolean validaCalculoCpf() {
        String cpf = getCpf();
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException io) {
            textInputCpf.setError("CPF inválido");
            return false;
        }
        return true;
    }

    @NonNull
    private String getCpf() {
        return campoCpf.getText().toString();
    }


    private boolean validaCampoComOnzeDigitos() {
        String cpf = getCpf();
        if (cpf.length() != 11) {
            textInputCpf.setError("O CPF precisa ter 11 dígitos");
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        if (!validaCampoComOnzeDigitos()) return false;
        if (!validaCalculoCpf()) return false;
        adicionaFormatacao();
        return true;
    }

    private void adicionaFormatacao() {
        String cpf = getCpf();
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }


}
