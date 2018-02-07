package br.com.rbg.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.rbg.agenda.dao.AlunoDAO;
import br.com.rbg.agenda.model.Aluno;

/**
 * Created by jhoni on 06/02/2018.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicaoDaEscola = pegaCoordenadada("Rua Vergueiro 3185, Vila Mariana, São Paulo ");
        if (posicaoDaEscola != null) {
            CameraUpdate unpdate = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 17);
            googleMap.moveCamera(unpdate);
        }

        AlunoDAO alunoDAO = new AlunoDAO(getContext());
        for (Aluno aluno : alunoDAO.buscaAlunos()) {
            LatLng coordenada = pegaCoordenadada(aluno.getEndereco());
            if (coordenada != null) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(coordenada);
                markerOptions.title(aluno.getNome());
                markerOptions.snippet(aluno.getNota().toString());
                googleMap.addMarker(markerOptions);
            }
        }
        alunoDAO.close();

        new Localizador(getContext(), googleMap);
    }

    private LatLng pegaCoordenadada(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);

            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
