package br.com.rbg.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Aluno 1", "Aluno 2 ", "Aluno 3", "Aluno 1", "Aluno 2 ", "Aluno 3", "Aluno 1", "Aluno 2 ", "Aluno 3"};
        ListView lstAlunos = findViewById(R.id.lstAlunos);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);

        lstAlunos.setAdapter(adapter);

        Button btnNovo = findViewById(R.id.btn_novo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentGoToForm);
            }
        });
    }
}
