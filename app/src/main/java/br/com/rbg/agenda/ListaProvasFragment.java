package br.com.rbg.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.rbg.agenda.model.Prova;

/**
 * Created by jhoni on 04/02/2018.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPort = Arrays.asList("Sujeito", "Objeto Direto", "Objeto Indireto");
        Prova provaPortugues = new Prova("Português", "02/02/2018", topicosPort);

        List<String> topicosMat = Arrays.asList("Equações de 2 Grau", "Trigonometria");
        Prova provaMatematica = new Prova("Matemática", "03/02/2018", topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prova prova = (Prova) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), "Clicou na prova de: " + prova, Toast.LENGTH_SHORT).show();

                Intent vaiParaDetalhes = new Intent(getContext(), DetalhesProvasActivity.class);
                vaiParaDetalhes.putExtra("prova", prova);
                startActivity(vaiParaDetalhes);


            }
        });

        return view;
    }
}
