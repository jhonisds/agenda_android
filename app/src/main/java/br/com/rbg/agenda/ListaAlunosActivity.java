package br.com.rbg.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.rbg.agenda.dao.AlunoDAO;
import br.com.rbg.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lstAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        lstAlunos = findViewById(R.id.lstAlunos);

        lstAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) lstAlunos.getItemAtPosition(position);

                Intent intentGoToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentGoToForm.putExtra("aluno", aluno);
                startActivity(intentGoToForm);
            }
        });

        Button btnNovo = findViewById(R.id.btn_novo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentGoToForm);
            }
        });

        registerForContextMenu(lstAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) lstAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(aluno);
                dao.close();

                Toast.makeText(ListaAlunosActivity.this, "Remover o Aluno: " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                carregaLista();

                return false;
            }
        });
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        lstAlunos.setAdapter(adapter);
    }
}
