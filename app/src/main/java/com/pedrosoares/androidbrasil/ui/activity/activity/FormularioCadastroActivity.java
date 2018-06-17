package com.pedrosoares.androidbrasil.ui.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.pedrosoares.androidbrasil.R;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidaCpf;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidadorPadrao;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraCampoNome();
        configuraCampoCpf();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraCampoSenha();
    }

    private void configuraCampoSenha() {
        TextInputLayout inputTextSenha = findViewById(R.id.formulario_cadastro_senha);
        addValidacaoPadrao(inputTextSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout inputTextEmail = findViewById(R.id.formulario_cadastro_email);
        addValidacaoPadrao(inputTextEmail);
    }

    private void configuraCampoTelefone() {
        TextInputLayout inputTextTelefone = findViewById(R.id.formulario_cadastro_telefone);
        addValidacaoPadrao(inputTextTelefone);
    }

    private void configuraCampoCpf() {
        final TextInputLayout inputTextCpf = findViewById(R.id.formulario_cadastro_cpf);
        final EditText campoCpf = inputTextCpf.getEditText();
        final CPFFormatter cpfFormatter = new CPFFormatter();
        final ValidaCpf validaCpf = new ValidaCpf(inputTextCpf);
        assert campoCpf != null;
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = campoCpf.getText().toString();
                if (!hasFocus) {
                    validaCpf.estaValido();

                } else {
                    try {
                        String cpdfSemFormato = cpfFormatter.unformat(cpf);
                        campoCpf.setText(cpdfSemFormato);
                    } catch (IllegalArgumentException e) {
                        Log.e("erro formatação cpf", e.getMessage());
                    }
                }
            }
        });
    }



    private void configuraCampoNome() {
        TextInputLayout inputTextNome = findViewById(R.id.formulario_cadastro_nome);
        addValidacaoPadrao(inputTextNome);
    }

    private void addValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        final ValidadorPadrao validador = new ValidadorPadrao(textInputCampo);
        assert campo != null;
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!validador.estaValido()) return;
                }
            }
        });
    }

}
