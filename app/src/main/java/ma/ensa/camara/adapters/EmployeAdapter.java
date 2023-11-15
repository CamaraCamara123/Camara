package ma.ensa.camara.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.ensa.camara.ListEmployeActivity;
import ma.ensa.camara.R;
import ma.ensa.camara.entities.Employe;

public class EmployeAdapter extends RecyclerView.Adapter<EmployeAdapter.EmployeHolderView> {

    private Context myContext;
    private List<Employe> employes;

    public EmployeAdapter(Context myContext, List<Employe> employes) {
        this.myContext = myContext;
        this.employes = employes;
    }

    public Context getMyContext() {
        return myContext;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    @NonNull
    @Override
    public EmployeHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(myContext);
        view = inflater.inflate(R.layout.employe_item,parent,false);
        EmployeHolderView holderView = new EmployeHolderView(view);

        holderView.view_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(myContext, ListEmployeActivity.class);
                intent.putExtra("nom",employes.get(holderView.getAdapterPosition()).getNom());
                intent.putExtra("prenom",employes.get(holderView.getAdapterPosition()).getPrenom());
                //intent.putExtra("dateNaissance",employes.get(holderView.getAdapterPosition()).getDateNaissance());

                myContext.startActivity(intent);
            }
        });
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeHolderView holder, int position) {
        //holder.nom.setText(employes.get(position).getId().toString());
        holder.nom.setText(employes.get(position).getNom());
        holder.prenom.setText(employes.get(position).getPrenom());
        //holder.naissance.setText(employes.get(position).getDateNaissance().toString());


    }

    @Override
    public int getItemCount() {
        return employes.size();
    }

    public static class EmployeHolderView extends RecyclerView.ViewHolder{
        private TextView nom;
        private TextView prenom;
        private TextView naissance;
        LinearLayout view_container;
        public EmployeHolderView(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            naissance = itemView.findViewById(R.id.naissance);
        }


    }
}
