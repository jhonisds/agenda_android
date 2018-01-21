package br.com.rbg.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.rbg.agenda.model.Aluno;

/**
 * Created by jhoni on 21/01/2018.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {

        campoNome = activity.findViewById(R.id.frm_nome);
        campoEndereco = activity.findViewById(R.id.frm_endereco);
        campoTelefone = activity.findViewById(R.id.frm_telefone);
        campoSite = activity.findViewById(R.id.frm_site);
        campoNota = activity.findViewById(R.id.frm_nota);
    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
