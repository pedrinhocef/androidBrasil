package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidaTelefoneComDdd {

    private static final String TELEFONE_DEVE_TER_10_OU_11_DIGITOS = "Telefone deve ter 10 ou 11 digítos";
    private final TextInputLayout textInputTelefone;
    private final EditText campoCpf;
    private ValidadorPadrao validadorPadrao;

    public ValidaTelefoneComDdd(TextInputLayout textInputTelefone) {
        this.textInputTelefone = textInputTelefone;
        this.campoCpf = textInputTelefone.getEditText();
        this.validadorPadrao =  new ValidadorPadrao(textInputTelefone);
    }


    public boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd) {
        int digitos = telefoneComDdd.length();
        if (digitos < 10 || digitos > 11) {
            textInputTelefone.setError(TELEFONE_DEVE_TER_10_OU_11_DIGITOS);
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        String telefone = campoCpf.getText().toString();
        if (!validaEntreDezOuOnzeDigitos(telefone)) return false;
        adicionaFormatacao(telefone);
        return true;
    }

    private void adicionaFormatacao(String telefone) {
        StringBuilder sb = new StringBuilder();
        int digitos = telefone.length();
        for (int i = 0; i < digitos; i++) {
            if(i == 0) {
                sb.append("(");
            }
            char digito = telefone.charAt(i);
            sb.append(digito);
            if (i == 1) {
                sb.append(") ");
            }
            if (digitos == 10 && i == 5 || digitos == 11 && i == 6 ) {
                sb.append("-");
            }

        }
        String telefoneFormatado = sb.toString();
        campoCpf.setText(telefoneFormatado);
    }
}
