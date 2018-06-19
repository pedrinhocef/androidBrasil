package com.pedrosoares.androidbrasil.ui.activity.formatter;

public class FormatadorTelefoneComDdd {

    public String formata(String telefone) {
        return telefone
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    public String remove(String telefoneComDdd) {
        return telefoneComDdd
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
