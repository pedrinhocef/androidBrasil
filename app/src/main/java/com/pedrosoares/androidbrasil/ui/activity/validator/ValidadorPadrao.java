package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidadorPadrao {

    private TextInputLayout textInputCampo;
    private EditText campo;

    public ValidadorPadrao(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    private boolean validaCampoObrigatorio() {
        String texto = campo.getText().toString();
        if (texto.isEmpty()) {
            textInputCampo.setError("Campo Obrigatório");
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if (!validaCampoObrigatorio()) return false;
        removeErro();
        return true;
    }

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}
