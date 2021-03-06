package com.pedrosoares.androidbrasil.ui.activity.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.pedrosoares.androidbrasil.ui.activity.formatter.FormatadorTelefoneComDdd;

public class ValidaTelefoneComDdd implements Validador{

    private static final String TELEFONE_DEVE_TER_10_OU_11_DIGITOS = "Telefone deve ter 10 ou 11 digítos";
    private final TextInputLayout textInputTelefone;
    private final EditText campoCpf;
    private ValidadorPadrao validadorPadrao;
    private final FormatadorTelefoneComDdd formatador = new FormatadorTelefoneComDdd();

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

    @Override
    public boolean estaValido() {
        if (!validadorPadrao.estaValido()) return false;
        String telefone = campoCpf.getText().toString();
        String telefoneSemFormatacao = formatador.remove(telefone);
        if (!validaEntreDezOuOnzeDigitos(telefoneSemFormatacao)) return false;
        adicionaFormatacao(telefoneSemFormatacao);
        return true;
    }

    private void adicionaFormatacao(String telefone) {
//        StringBuilder sb = new StringBuilder();
//        int digitos = telefone.length();
//        for (int i = 0; i < digitos; i++) {
//            if(i == 0) {
//                sb.append("(");
//            }
//            char digito = telefone.charAt(i);
//            sb.append(digito);
//            if (i == 1) {
//                sb.append(") ");
//            }
//            if (digitos == 10 && i == 5 || digitos == 11 && i == 6 ) {
//                sb.append("-");
//            }
//
//        }
//        String telefoneFormatado = sb.toString();
        String telefoneFormatado = formatador.formata(telefone);
        campoCpf.setText(telefoneFormatado);
    }

}
