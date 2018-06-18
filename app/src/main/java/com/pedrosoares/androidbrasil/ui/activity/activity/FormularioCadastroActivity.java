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
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidaTelefoneComDdd;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidadorPadrao;

import br.com.caelum.stella.format.CPFFormatter;

public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";

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
        final EditText campoTelefone = inputTextTelefone.getEditText();
        final ValidaTelefoneComDdd validaTelefone = new ValidaTelefoneComDdd(inputTextTelefone);
        campoTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefone.getText().toString();
                if (hasFocus) {
                    String telefoneSemFormatacao = telefoneComDdd
                            .replace("(", "")
                            .replace(")", "")
                            .replace(" ", "")
                            .replace("-", "");
                    campoTelefone.setText(telefoneSemFormatacao);
                } else {
                    validaTelefone.estaValido();
                }
            }
        });
    }

    private void configuraCampoCpf() {
        TextInputLayout inputTextCpf = findViewById(R.id.formulario_cadastro_cpf);
        final EditText campoCpf = inputTextCpf.getEditText();
        final CPFFormatter cpfFormatter = new CPFFormatter();
        final ValidaCpf validaCpf = new ValidaCpf(inputTextCpf);
        assert campoCpf != null;
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    removeFormatacao(campoCpf, cpfFormatter);
                } else {
                    validaCpf.estaValido();
                }
            }
        });
    }

    private void removeFormatacao(EditText campoCpf, CPFFormatter cpfFormatter) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpdfSemFormato = cpfFormatter.unformat(cpf);
            campoCpf.setText(cpdfSemFormato);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
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
                    validador.estaValido();
                }
            }
        });
    }

}
