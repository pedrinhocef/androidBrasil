package com.pedrosoares.androidbrasil.ui.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pedrosoares.androidbrasil.R;
import com.pedrosoares.androidbrasil.ui.activity.formatter.FormatadorTelefoneComDdd;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidaCpf;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidaEmail;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidaTelefoneComDdd;
import com.pedrosoares.androidbrasil.ui.activity.validator.Validador;
import com.pedrosoares.androidbrasil.ui.activity.validator.ValidadorPadrao;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;

public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

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
        Button cadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean formularioEstaValido = true;
                for (Validador validador : validadores) {
                    if (!validador.estaValido()) {
                        formularioEstaValido = false;
                    }
                }
                if (formularioEstaValido) {
                    Toast.makeText(
                            FormularioCadastroActivity.this,
                            "Cadastro realizado",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configuraCampoSenha() {
        TextInputLayout inputTextSenha = findViewById(R.id.formulario_cadastro_senha);
        addValidacaoPadrao(inputTextSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout inputTextEmail = findViewById(R.id.formulario_cadastro_email);
        EditText campoEmail = inputTextEmail.getEditText();
        final ValidaEmail validaEmail = new ValidaEmail(inputTextEmail);
        validadores.add(validaEmail);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validaEmail.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefone() {
        TextInputLayout inputTextTelefone = findViewById(R.id.formulario_cadastro_telefone);
        final EditText campoTelefone = inputTextTelefone.getEditText();
        final ValidaTelefoneComDdd validaTelefone = new ValidaTelefoneComDdd(inputTextTelefone);
        validadores.add(validaTelefone);
        final FormatadorTelefoneComDdd formatador = new FormatadorTelefoneComDdd();
        campoTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefone.getText().toString();
                if (hasFocus) {
                    String telefoneSemFormatacao = formatador.remove(telefoneComDdd);
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
        validadores.add(validaCpf);
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
        validadores.add(validador);
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
