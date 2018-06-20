package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidaEmail implements Validador{

    private final TextInputLayout textInputEmail;
    private final EditText campoEmail;
    private final ValidadorPadrao validadorPadrao;

    public ValidaEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
        this.campoEmail = this.textInputEmail.getEditText();
        this.validadorPadrao = new ValidadorPadrao(textInputEmail);
    }

    private boolean validaPadraoEmail(String email) {

        if (email.matches(".+@.+\\..+")) {
            return true;
        } else {
            textInputEmail.setError("E-mail inválido");
            return false;
        }
    }

    @Override
    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        String email = campoEmail.getText().toString();
        if (!validaPadraoEmail(email)) return false;
        return true;
    }
}
