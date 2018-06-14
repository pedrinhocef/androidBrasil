package com.pedrosoares.androidbrasil.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.pedrosoares.androidbrasil.R;

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
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = campoCpf.getText().toString();
                if (!hasFocus) {
                    if (!validaCampoObrigatorio(cpf, inputTextCpf))return;
                    if (!validaCampoComOnzeDigitos(cpf,inputTextCpf))return;
                    removeErro(inputTextCpf);
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
        assert campo != null;
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if (!hasFocus) {
                    if (!validaCampoObrigatorio(texto, textInputCampo))return;
                    removeErro(textInputCampo);
                }
            }
        });
    }

    private boolean validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if (texto.isEmpty()) {
            textInputCampo.setError("Campo Obrigatório");
            return false;
        }
        return true;
    }

    private void removeErro(TextInputLayout textInputCampo) {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }

    public boolean validaCampoComOnzeDigitos(String cpf, TextInputLayout textInputCpf) {
        if (cpf.length() != 11) {
            textInputCpf.setError("O CPF precisa ter 11 dígitos");
            return false;
        }
        return true;
    }
}
