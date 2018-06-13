package com.pedrosoares.androidbrasil.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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

        inicializandoCampos();

    }

    private void inicializandoCampos() {
        TextInputLayout inputTextNome = findViewById(R.id.formulario_cadastro_nome);
        addValidacaoPadrao(inputTextNome);

        TextInputLayout inputTextCpf = findViewById(R.id.formulario_cadastro_cpf);
        addValidacaoPadrao(inputTextCpf);

        TextInputLayout inputTextTelefone = findViewById(R.id.formulario_cadastro_telefone);
        addValidacaoPadrao(inputTextTelefone);

        TextInputLayout inputTextEmail = findViewById(R.id.formulario_cadastro_email);
        addValidacaoPadrao(inputTextEmail);

        TextInputLayout inputTextSenha = findViewById(R.id.formulario_cadastro_senha);
        addValidacaoPadrao(inputTextSenha);
    }


    private void addValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        assert campo != null;
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if (!hasFocus){
                    if (texto.isEmpty()){
                        textInputCampo.setError("Campo Obrigatório");
                    } else {
                        textInputCampo.setError(null);
                        textInputCampo.setErrorEnabled(false);
                    }
                }
            }
        });
    }
}
